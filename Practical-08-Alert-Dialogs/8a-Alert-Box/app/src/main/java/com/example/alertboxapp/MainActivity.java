package com.example.alertboxapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    Button btnShowAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowAlert = findViewById(R.id.btnShowAlert);
        btnShowAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert Message");
                builder.setMessage("Hello! Welcome to Android Application.");
                builder.setPositiveButton("OK", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
   }}
