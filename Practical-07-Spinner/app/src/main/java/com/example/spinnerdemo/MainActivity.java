package com.example.spinnerdemo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Spinner spinnerLanguages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerLanguages = findViewById(R.id.spinnerLanguages);
        spinnerLanguages.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
                        String language =
                                parent.getItemAtPosition(position).toString();
                        Toast.makeText(
                                MainActivity.this,
                                "Selected: " + language,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {
                        // Nothing selected
                    }
                }
        );
    }
}
