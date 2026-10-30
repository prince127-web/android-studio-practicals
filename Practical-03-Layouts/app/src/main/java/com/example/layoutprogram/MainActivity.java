package com.example.layoutprogram;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Button btnLinear, btnRelative, btnConstraint, btnFrame, btnScroll;
    FrameLayout layoutContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLinear = findViewById(R.id.btnLinear);
        btnRelative = findViewById(R.id.btnRelative);
        btnConstraint = findViewById(R.id.btnConstraint);
        btnFrame = findViewById(R.id.btnFrame);
        btnScroll = findViewById(R.id.btnScroll);
        layoutContainer = findViewById(R.id.layoutContainer);
        btnLinear.setOnClickListener(v -> {
            layoutContainer.removeAllViews();
            getLayoutInflater().inflate(R.layout.layout_linear, layoutContainer, true);
        });
        btnRelative.setOnClickListener(v -> {
            layoutContainer.removeAllViews();
            getLayoutInflater().inflate(R.layout.layout_relative, layoutContainer, true);
        });
        btnConstraint.setOnClickListener(v -> {
            layoutContainer.removeAllViews();
            getLayoutInflater().inflate(R.layout.layout_constraint, layoutContainer, true);
        });
        btnFrame.setOnClickListener(v -> {
            layoutContainer.removeAllViews();
            getLayoutInflater().inflate(R.layout.layout_frame, layoutContainer, true);
        });
        btnScroll.setOnClickListener(v -> {
            layoutContainer.removeAllViews();
            getLayoutInflater().inflate(R.layout.layout_scroll, layoutContainer, true);
        });
    }
}
