package com.example.validatename;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    Button btnValidate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Connect EditText and Button with XML
        editTextName = findViewById(R.id.editTextName);
        btnValidate = findViewById(R.id.btnValidate);
        // Validate button click
        btnValidate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Get the name entered by the user
                String name = editTextName.getText().toString().trim();
                // Check if the name field is empty
                if (name.isEmpty()) {
                    // Show AlertBox if name is empty
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Please enter your name");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                } else {
                    // Show greeting AlertBox
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Welcome");
                    builder.setMessage("Hello " + name);
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
            }
        });
    }
}
