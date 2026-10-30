package com.example.sharedpreferences_127;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText edtName;
    Button btnSave, btnDisplay;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        btnSave = findViewById(R.id.btnSave);
        btnDisplay = findViewById(R.id.btnDisplay);
        txtResult = findViewById(R.id.txtResult);
        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            getSharedPreferences("StudentData", MODE_PRIVATE)
                    .edit()
                    .putString("student_name", name)
                    .apply();
            txtResult.setText("Data Saved");
        });
        btnDisplay.setOnClickListener(v -> {
            String name = getSharedPreferences(
                    "StudentData",
                    MODE_PRIVATE
            ).getString(
                    "student_name",
                    "No Data Found"
            );
            txtResult.setText("Student Name: " + name);
        });
    }
}
