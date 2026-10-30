package com.example.sqlitecrud;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    // Input fields
    EditText edtId, edtName, edtCourse;
    // Buttons
    Button btnInsert, btnView, btnUpdate, btnDelete;
    // TextView for displaying records
    TextView txtResult;
    // Database object
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Connect Java with XML
        setContentView(R.layout.activity_main);
        // Connect EditText fields
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtCourse = findViewById(R.id.edtCourse);
        // Connect Buttons
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
// Connect TextView
        txtResult = findViewById(R.id.txtResult);
        // Create DatabaseHelper object
        databaseHelper = new DatabaseHelper(this);
        // INSERT BUTTON
        btnInsert.setOnClickListener(v -> {
            // Get values from EditText
            int id = Integer.parseInt(
                    edtId.getText().toString()
            );
            String name = edtName.getText().toString();
            String course = edtCourse.getText().toString();
            // Insert student
            boolean result = databaseHelper.insertStudent(
                    id,
                    name,
                    course
            );
            if (result) {
                Toast.makeText(
                        MainActivity.this,
                        "Student Inserted Successfully",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Insertion Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        // VIEW BUTTON
        btnView.setOnClickListener(v -> {
// Get all students
            Cursor cursor = databaseHelper.getAllStudents();
            StringBuilder data = new StringBuilder();
            // Check if records exist
            if (cursor.getCount() == 0) {
                txtResult.setText("No Records Found");
                return;
            }
            // Read each record
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String course = cursor.getString(2);
                data.append("ID: ")
                        .append(id)
                        .append("\n");
                data.append("Name: ")
                        .append(name)
                        .append("\n");
                data.append("Course: ")
                        .append(course)
                        .append("\n\n");
            }
            // Close Cursor
            cursor.close();
            // Display records
            txtResult.setText(data.toString());
        });
        // UPDATE BUTTON
        btnUpdate.setOnClickListener(v -> {
            int id = Integer.parseInt(
                    edtId.getText().toString()
            );
String name = edtName.getText().toString();
            String course = edtCourse.getText().toString();
            // Update student
            boolean result = databaseHelper.updateStudent(
                    id,
                    name,
                    course
            );
            if (result) {
                Toast.makeText(
                        MainActivity.this,
                        "Student Updated Successfully",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Student Not Found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        // DELETE BUTTON
        btnDelete.setOnClickListener(v -> {
            int id = Integer.parseInt(
                    edtId.getText().toString()
            );
            // Delete student
            boolean result = databaseHelper.deleteStudent(id);
            if (result) {
                Toast.makeText(
                        MainActivity.this,
                        "Student Deleted Successfully",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Student Not Found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
