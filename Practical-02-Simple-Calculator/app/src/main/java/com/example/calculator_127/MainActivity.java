package com.example.calculator_127;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    TextView result;
    Button add, sub, mul, div;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextNumber);
        e2 = findViewById(R.id.editTextNumber2);
        result = findViewById(R.id.textResult);
        add = findViewById(R.id.btnAdd);
        sub = findViewById(R.id.btnSub);
        mul = findViewById(R.id.btnMul);
        div = findViewById(R.id.btnDiv);
        add.setOnClickListener(v -> calculate('+'));
        sub.setOnClickListener(v -> calculate('-'));
        mul.setOnClickListener(v -> calculate('*'));
        div.setOnClickListener(v -> calculate('/'));
    }
    private void calculate(char op) {
        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        if (s1.isEmpty() || s2.isEmpty()) {
            result.setText("Please enter both numbers");
            return;
        }
        double num1 = Double.parseDouble(s1);
        double num2 = Double.parseDouble(s2);
        double ans = 0;
        switch (op) {
            case '+':
                ans = num1 + num2;
                break;
            case '-':
                ans = num1 - num2;
                break;
            case '*':
                ans = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    result.setText("Cannot divide by zero");
                    return;
                }
                ans = num1 / num2;
                break;
        }
        result.setText("Result : " + ans);
    }
}
