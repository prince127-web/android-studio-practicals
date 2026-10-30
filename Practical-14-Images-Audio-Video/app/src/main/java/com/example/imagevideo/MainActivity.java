package com.example.imagevideo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btnAudio;
    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        btnAudio = findViewById(R.id.btnAudio);
        videoView = findViewById(R.id.videoView);
        imageView.setImageResource(R.drawable.sample_image);
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
        btnAudio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            btnAudio.setText("Play Audio");
                        } else {
                            mediaPlayer.start();
                            btnAudio.setText("Pause Audio");
                        }
                    }
                });
        // Reset button state when audio playback completes
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnAudio.setText("Play Audio");
                    }
                });
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.sample_video);
        videoView.setVideoURI(videoUri);
        // Add standard playback controls (Play, Pause, Seek bar)
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Pause playback if the activity goes to the background
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnAudio.setText("Play Audio");
        }
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release hardware resources allocated by MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

