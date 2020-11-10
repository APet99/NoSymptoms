package com.nosymptoms.questionnaireapp.activity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nosymptoms.questionnaireapp.R;

import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

import java.util.Random;

/**
 * Created By: Alex Peterson        AlexJoseph.Peterson@CalBaptist.edu
 * Created On: October 9, 2020
 *
 * This Location Activity was made with guidance from geek4geek.com
 *
 * */


public class LocationActivity extends AppCompatActivity {

    protected FusedLocationProviderClient mFusedLocationClient;
    protected TextView latitudeTextView, longitTextView;
    int PERMISSION_ID = 44;
    public static String id = "no_symptoms_channel_id";
    NotificationManager mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latitudeTextView = findViewById(R.id.lat);
        longitTextView = findViewById(R.id.lon);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        requestNewLocationData();
        createNotificationChannel();
        notifyByLocation();

    }

    private void createNotificationChannel() {
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence name = "NSChannel";

        String description = "NSDescription";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);

        mChannel.setDescription(description);
        mChannel.enableLights(true);

        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setShowBadge(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);
    }



    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                                new OnCompleteListener<Location>() {
                                    @Override
                                    public void onComplete(
                                            @NonNull Task<Location> task) {
                                        Location location = task.getResult();
                                        if (location == null) {
                                            requestNewLocationData();
                                        } else { latitudeTextView.setText(location.getLatitude() + "");
                                            longitTextView.setText(location.getLongitude() + "");
                                        }
                                    }
                                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(20 * 1000);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(Integer.MAX_VALUE);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitudeTextView.setText(mLastLocation.getLatitude() + "");
            longitTextView.setText(mLastLocation.getLongitude() + "");

            Location cbuLocation = new Location("");
            cbuLocation.setLatitude(33.9280);
            cbuLocation.setLongitude(-117.4255);
            System.out.println("Is at CBU within" + 550 +"meters: " + isWithinMetersRadius(mLastLocation, cbuLocation, 550) + " "  + mLastLocation.distanceTo(cbuLocation));
        }
    };

    // method to check for permissions 
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission
                                .ACCESS_COARSE_LOCATION,
                        Manifest.permission
                                .ACCESS_FINE_LOCATION},
                PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    public boolean isWithinMetersRadius(Location currentLocation, Location centerPoint, double meters){

        notifyByLocation();
        return centerPoint.distanceTo(currentLocation) < meters;

    }

    public void notifyByLocation() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Fill Out Your Questionnaire!")
                .setContentText("To be on campus you must be cleared first.")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        Random r = new Random();

        notificationManager.notify(r.nextInt(1000) + 1, builder.build());
    }
}