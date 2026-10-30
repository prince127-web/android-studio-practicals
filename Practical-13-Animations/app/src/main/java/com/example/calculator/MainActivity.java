package com.example.calculator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    ImageView imgCar;
    Button btnFade, btnRotate, btnBlink, btnMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize views
        imgCar = findViewById(R.id.imgCar);
        btnFade = findViewById(R.id.btnFade);
        btnRotate = findViewById(R.id.btnRotate);
        btnBlink = findViewById(R.id.btnBlink);
        btnMove = findViewById(R.id.btnMove);
        // Fade Animation
        btnFade.setOnClickListener(v -> {
            imgCar.clearAnimation();
            AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
            fade.setDuration(1000);
            fade.setRepeatCount(1);
            fade.setRepeatMode(Animation.REVERSE);
            imgCar.startAnimation(fade);
        });
        // Rotate Animation
        btnRotate.setOnClickListener(v -> {
            imgCar.clearAnimation();
            RotateAnimation rotate = new RotateAnimation(
                    0f,
                    360f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
            );
            rotate.setDuration(1000);
            rotate.setRepeatCount(1);
            rotate.setRepeatMode(Animation.RESTART);
            imgCar.startAnimation(rotate);
        });
        // Blink Animation
        btnBlink.setOnClickListener(v -> {
            imgCar.clearAnimation();
            AlphaAnimation blink = new AlphaAnimation(1.0f, 0.0f);
            blink.setDuration(500);
            blink.setRepeatCount(Animation.INFINITE);
            blink.setRepeatMode(Animation.REVERSE);
            imgCar.startAnimation(blink);
        });
        // Move Animation
        btnMove.setOnClickListener(v -> {
            imgCar.clearAnimation();
            TranslateAnimation move = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0f,
                    Animation.RELATIVE_TO_PARENT, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f
            );
            move.setDuration(1000);
            move.setRepeatCount(1);
            move.setRepeatMode(Animation.REVERSE);
            imgCar.startAnimation(move);
        });
    }
}
