package com.nosymptoms.questionnaireapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.dao.UserDao;
import com.nosymptoms.questionnaireapp.model.User;

import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    public EditText cbuIDNum;
    public EditText password;
    public Button login;
    public TextView welcome;

    @Inject
    public UserDao userDAO;


    int PERMISSION_ID = 44;
    NotificationManager mNotificationManager;
    private FusedLocationProviderClient fusedLocationClient;
    public static String id = "no_symptoms_channel_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cbuIDNum = findViewById(R.id.cbu_id_num);
        password = findViewById(R.id.password_login);
        login = findViewById(R.id.login_button);
        welcome = findViewById(R.id.welcome);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        requestNewLocationData();
        createNotificationChannel();
        //notifyByLocation();
    }

    public void onLoginButtonClick(View view) {
        String idStr = cbuIDNum.getText().toString();
        System.out.println("Logging in...");

        if (idStr.matches("\\d+6")) {
            System.out.println("Valid id");
            int id =Integer.parseInt(idStr);

            userDAO.getUserById(id).get().addOnSuccessListener(documentSnapshot -> {
                User user = documentSnapshot.toObject(User.class);

                System.out.println("Checking for user...");
                //check if user is valid
                boolean isValid = isUserInDB(user, password.getText().toString());

                if (isValid) {
                    System.out.println("User exists");

                    Intent intent = new Intent(this, HomeActivity.class);

                    startActivity(intent);
                }
            });

        }


    }

    private boolean isUserInDB (User user, String userPass){

        //check if the username and password match a user in the database
        if((user == null) || (!user.getPassword().equals(userPass))){
            System.out.println(String.format("User: %s", user));
            Toast.makeText(getApplicationContext(), "Username or password is not valid", Toast.LENGTH_SHORT).show();
            failedLogin();
            return false;
        }

        successfulLogin();
        return true;
    }

    public void successfulLogin(){
        login.setEnabled(true);
        finish();
    }

    public void failedLogin(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }

    public void onSignUpButtonClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onForgotButtonClick(View view) {
        Intent intent = new Intent(this, ForgotActivity.class);
        startActivity(intent);
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

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else { //latitudeTextView.setText(location.getLatitude() + "");
                                    //longitTextView.setText(location.getLongitude() + "");
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
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(Integer.MAX_VALUE);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission
                                .ACCESS_COARSE_LOCATION,
                        Manifest.permission
                                .ACCESS_FINE_LOCATION,
                        Manifest.permission
                                .ACCESS_BACKGROUND_LOCATION},
                PERMISSION_ID);
    }

    //Location Callback
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            //latitudeTextView.setText(mLastLocation.getLatitude() + "");
            //longitTextView.setText(mLastLocation.getLongitude() + "");

            Location cbuLocation = new Location("");
            cbuLocation.setLatitude(33.9280);
            cbuLocation.setLongitude(-117.4255);
            System.out.println("Is at CBU within" + 550 +"meters: " + isWithinMetersRadius(mLastLocation, cbuLocation, 550) + " "  + mLastLocation.distanceTo(cbuLocation));
        }
    };

    public boolean isWithinMetersRadius(Location currentLocation, Location centerPoint, double meters){
        //System.out.println(!didSubmit("logID", "Fri Oct 09 13:54:09 PDT 2020///123456", "userRef","123456"));
        if (centerPoint.distanceTo(currentLocation) < meters ){notifyByLocation();}

        return centerPoint.distanceTo(currentLocation) < meters;
    }

    protected boolean didSubmit(String fieldOne, String valueOne, String fieldTwo, String valueTwo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference entryRefs = db.collection("Entries");
        Query queryEquals =
                entryRefs.whereEqualTo(fieldOne, valueOne).whereEqualTo(fieldTwo, valueTwo);
        queryEquals.get();
        int size = queryEquals.get().getResult().size();
        if (queryEquals.get().isSuccessful()){
            return size > 0;}else{return false;}
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
