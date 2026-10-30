package com.example.volley127;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView txtResult;
    private Button btnGetUsers, btnPostUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResult);
        btnGetUsers = findViewById(R.id.btnGetUsers);
        btnPostUser = findViewById(R.id.btnPostUser);
        requestQueue = Volley.newRequestQueue(this);
        btnGetUsers.setOnClickListener(v -> getUsers());
        btnPostUser.setOnClickListener(v -> postUser());
    }
    private void getUsers() {
        txtResult.setText("Loading users...");
        String url =
                "https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    StringBuilder result = new StringBuilder();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject user =
                                    response.getJSONObject(i);
                            String name = user.getString("name");
                            String email = user.getString("email");
                            result.append("Name: ")
                                    .append(name)
                                    .append("\nEmail: ")
                                    .append(email)
                                    .append("\n\n");
                            Log.d("VOLLEY", name + " - " + email);
                        }
                        txtResult.setText(result.toString());
                    } catch (Exception e) {
                        Log.e("VOLLEY", "JSON parsing error", e);
                        txtResult.setText("Error reading user data.");
                    }
                },
                error -> {
                    Log.e("VOLLEY", "GET request failed", error);
                    txtResult.setText(
                            "Failed to retrieve users. Check Internet."
                    );
               }
        );
        requestQueue.add(request);
    }
    private void postUser() {
        String url =
                "https://jsonplaceholder.typicode.com/users";
        JSONObject body = new JSONObject();
        try {
            body.put("name", "Prince");
            body.put("email", "prince@gmail.com");

        } catch (org.json.JSONException e) {
            Log.e("VOLLEY", "JSON error", e);
            txtResult.setText("Could not create user data.");
            return;
        }
        txtResult.setText("Sending user data...");
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                response -> {
                    Log.d("VOLLEY", "POST response: " + response);
                    txtResult.setText(
                            "POST Successful\n\nResponse:\n"
                                   + response.toString()
                    );
                },
                error -> {
                    Log.e("VOLLEY", "POST request failed", error);
                    txtResult.setText(
                            "POST failed. Check Internet connection."
                    );
                }); requestQueue.add(request);
    }}
