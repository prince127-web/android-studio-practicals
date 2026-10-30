package com.example.okhttpdemo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class MainActivity extends AppCompatActivity {
    Button btnGetData;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect Java with XML layout
        setContentView(R.layout.activity_main);
        // Connect Java variables with XML controls
        btnGetData = findViewById(R.id.btnGetData);
        txtResult = findViewById(R.id.txtResult);
        // Execute code when button is clicked
        btnGetData.setOnClickListener(v -> {
            // Create OkHttpClient
            OkHttpClient client = new OkHttpClient();

            // Create API request
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/1")
                    .get()
                    .build();
            // Run network operation in background
            new Thread(() -> {
                try {
                    // Send request to server
                    try (Response response = client.newCall(request).execute()) {
                        // Get response code
                        int responseCode = response.code();
                        // Get response data
                        String responseData = response.body().string();
                        // Display result on screen
                        runOnUiThread(() -> {
                            txtResult.setText(
                                    "Response Code: "
                                            + responseCode
                                            + "\n\n"
                                            + "Response:\n"
                                            + responseData
                            );

                        });
                    }

                } catch (Exception e) {
                    // Display error
                    runOnUiThread(() -> {
                        txtResult.setText(
                                "Error: "
                                        + e.getMessage()
                        );

                    });
                }
            }).start();
        });
    }
}
