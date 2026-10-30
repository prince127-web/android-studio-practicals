package com.example.alertbox_prince_five;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Record")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Success")
                                    .setMessage("Record Deleted")
                                    .setPositiveButton("OK", null)
                                    .show();})
                        .setNegativeButton("No", null)
                        .show();}});}}
