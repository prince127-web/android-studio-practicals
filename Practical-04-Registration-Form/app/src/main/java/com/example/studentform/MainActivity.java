package com.example.studentform;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtMobile;
    RadioGroup radioGender;
    CheckBox checkCPlusPlus, checkPython;
    Spinner spinnerCourse;
    Button btnRegister;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtMobile = findViewById(R.id.edtMobile);
        radioGender = findViewById(R.id.radioGender);
        checkCPlusPlus = findViewById(R.id.checkAndroid);
        checkPython = findViewById(R.id.checkJava);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        btnRegister = findViewById(R.id.btnRegister);
        txtResult = findViewById(R.id.txtResult);
        String[] courses = {
                "Select Course",
                "MCA",
                "MMS",
                "BMS",
                "BCA"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                courses
        );
        spinnerCourse.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String mobile = edtMobile.getText().toString();
                int selectedId = radioGender.getCheckedRadioButtonId();
                String gender = "";
                if (selectedId != -1) {
                    RadioButton selectedRadio = findViewById(selectedId);
                    gender = selectedRadio.getText().toString();
                }
                String skills = "";
                if (checkCPlusPlus.isChecked()) {
                    skills += "C++ ";
                }
                if (checkPython.isChecked()) {
                    skills += "Python ";
                }
                String course =
                        spinnerCourse.getSelectedItem().toString();
                String result =
                                "Name: " + name + "\n" +
                                "Email: " + email + "\n" +
                                "Mobile: " + mobile + "\n" +
                                "Gender: " + gender + "\n" +
                                "Skills: " + skills + "\n" +
                                "Course: " + course;

                txtResult.setText(result);
            }
        });
    }
}
