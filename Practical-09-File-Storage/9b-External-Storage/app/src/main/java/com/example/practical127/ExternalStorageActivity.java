package com.example.practical127;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class ExternalStorageActivity extends AppCompatActivity {
    EditText edtName, edtRollNo;
    Button btnSave, btnRead;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect Java with XML
        setContentView(R.layout.external_storage);
        // Connect XML components
        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        txtResult = findViewById(R.id.txtResult);
        // SAVE DATA
        btnSave.setOnClickListener(view -> {
                    // Get student name
                    String name = edtName.getText().toString().trim();
                    // Get roll number
                    String rollNo = edtRollNo.getText().toString().trim();
                    // Check empty fields
                    if (name.isEmpty() || rollNo.isEmpty()) {
                        Toast.makeText(
                        this,
                        "Please enter all details",
                        Toast.LENGTH_SHORT
                        ).show();
                        return;
                    }
                    // Data to save
                    String data =
                    "Student Name: " + name +
                    "\nRoll Number: " + rollNo;
                    try {
                        // Get External Storage directory
                        File folder = getExternalFilesDir(null);
                        // Create student.txt file
                        File file = new File(folder, "student.txt");
                        // Open file for writing
                        FileOutputStream fos =
                        new FileOutputStream(file);
                        // Write data
                        fos.write(data.getBytes());
                        // Close file
                        fos.close();
                        // Success message
                        Toast.makeText(
                        this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                        ).show();
                    } catch (Exception e) {
                        Toast.makeText(
                        this,
                        "Error while saving",
                        Toast.LENGTH_SHORT
                        ).show();
                    }
                });
        // READ DATA
        btnRead.setOnClickListener(view -> {
                    try {
                        // Get External Storage directory
                        File folder = getExternalFilesDir(null);
                        // Open student.txt
                        File file = new File(folder, "student.txt");
                        // Open file for reading
                        FileInputStream fis =
                        new FileInputStream(file);
                        StringBuilder data =
                        new StringBuilder();
                        int ch;
                        // Read file
                        while ((ch = fis.read()) != -1) {
                            data.append((char) ch);
                        }
                        // Close file
                        fis.close();
                        // Display data
                        txtResult.setText(data.toString());
                    } catch (Exception e) {
                        Toast.makeText(
                        this,
                        "No data found",
                        Toast.LENGTH_SHORT
                        ).show();
                    }
                });
}}

