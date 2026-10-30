package com.example.fadeanimation;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.annotation.NonNull;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView t1;
    Button b1;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.textView);
        b1 = findViewById(R.id.button);
        locationManager = (LocationManager)
                getSystemService(LOCATION_SERVICE);
        b1.setOnClickListener(v -> getLocation());}
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, 100);
            return;}
        if (!locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)) {
            t1.setText("Please enable GPS");
            return;}
        t1.setText("Getting location...");
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(
                    @NonNull Location location) {
                t1.setText("Latitude: "
                        + location.getLatitude()
                        + "\nLongitude: "
                        + location.getLongitude());
                locationManager.removeUpdates(this);}
            @Override
            public void onProviderEnabled(
                    @NonNull String provider) {}
            @Override
            public void onProviderDisabled(
                    @NonNull String provider) {
                t1.setText("GPS disabled");
            }};
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0, listener);}
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                t1.setText("Location permission denied");}}}}
