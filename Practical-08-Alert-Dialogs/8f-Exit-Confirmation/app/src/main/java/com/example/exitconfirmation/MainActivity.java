package com.example.exitconfirmation;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    // Declare Exit Button
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect Java file with XML layout
        setContentView(R.layout.activity_main);
        // Find Exit button using its ID
        btnExit = findViewById(R.id.btnExit);
        // Set click event on Exit button
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Create AlertDialog
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                // Set title
                builder.setTitle("Exit");
                // Set confirmation message
                builder.setMessage("Do you want to exit?");
                // YES button
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    // Close the application
                    finishAffinity();

                });
                // NO button
                builder.setNegativeButton("No", (dialog, which) -> {
                    // Close only the AlertBox and stay on current page
                    dialog.dismiss();
                });
                // Show AlertBox
                builder.show();
            }
        });
    }
}
