package com.example.confirmationalertbox;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the button using its ID
        btnConfirm = findViewById(R.id.btnConfirm);
        // Set click event on the button
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create AlertDialog
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                // Set title
                builder.setTitle("Confirmation");
                // Set message
                builder.setMessage("Do you want to continue?");
                // Add Yes button
                builder.setPositiveButton("Yes", null);
                // Add No button
                builder.setNegativeButton("No", null);
                // Show AlertBox
                builder.show();
            }
        });
    }
}
