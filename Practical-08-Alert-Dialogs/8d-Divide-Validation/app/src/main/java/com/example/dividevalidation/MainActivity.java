package com.example.dividevalidation;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    // Declare EditText and Button
    EditText editTextNumber1, editTextNumber2;
    Button btnDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect Java with activity_main.xml
        setContentView(R.layout.activity_main);
        // Connect XML components with Java
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        btnDivide = findViewById(R.id.btnDivide);
        // Set click event on Divide button
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get first number
                double number1 = Double.parseDouble(
                        editTextNumber1.getText().toString()
                );
                // Get second number
                double number2 = Double.parseDouble(
                        editTextNumber2.getText().toString()
                );
                // Check if second number is zero
                if (number2 == 0) {
                    // Create AlertBox
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    // Set title and error message
                    builder.setTitle("Error");
                    builder.setMessage("Cannot divide by zero");
                    // Add OK button
                    builder.setPositiveButton("OK", null);
                    // Show AlertBox
                    builder.show();
                } else {
                    // Calculate division
                    double result = number1 / number2;
                    // Create AlertBox
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    // Set title
                    builder.setTitle("Result");
                    // Display result
                    builder.setMessage("Result = " + result);
                    // Add OK button
                    builder.setPositiveButton("OK", null);
                    // Show AlertBox
                    builder.show();
                }
            }
        });
    }
}
