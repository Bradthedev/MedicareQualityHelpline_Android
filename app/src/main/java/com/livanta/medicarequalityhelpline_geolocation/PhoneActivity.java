package com.livanta.medicarequalityhelpline_geolocation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class PhoneActivity extends AppCompatActivity {

    private static String TAG = "PhoneActivity.java";

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 5; // 5 minute


    private MyApplication app;

    private String phoneNumber;

    private Button btnCall;
    private Button btnChangeState;
    private Button btnInfo;


    private DBAdapter database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        app = (MyApplication)getApplicationContext();
        database = app.hospitalDatabase;


        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                handleLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        String locationProvider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
            handleLocation(locationManager.getLastKnownLocation(locationProvider));
        }



        phoneNumber = app.getPhoneNumberPrefs();

        btnCall = findViewById(R.id.call_button);
        btnChangeState = findViewById(R.id.change_state);
        btnInfo = findViewById(R.id.i_button);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        btnChangeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setPhoneNumberPrefs(null);
                startActivity(new Intent(PhoneActivity.this, MainActivity.class));
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneActivity.this, InfoActivity.class));
            }
        });
    }

    private void handleLocation(Location _location){

        //0.0001 in Decimal Degrees = about 7.871 meters around the 45th parallel... more information found at https://en.wikipedia.org/wiki/Decimal_degrees
        double DEGREES_DECIMAL_RADIUS = 0.0020; // about 49 meters

        double livantaLat = 39.1303723;
        double livantaLng = -76.7968933;

        double actualLat = _location.getLatitude();
        double actualLng = _location.getLongitude();

        double maxLat = actualLat + DEGREES_DECIMAL_RADIUS;
        double maxLng = actualLng + DEGREES_DECIMAL_RADIUS;

        double minLat = actualLat - DEGREES_DECIMAL_RADIUS;
        double minLng = actualLng - DEGREES_DECIMAL_RADIUS;

        ArrayList<HospitalResults> hospitals = viewDatabase();

        for (HospitalResults h : hospitals){
            if(h.getLat() > minLat && h.getLat() < maxLat && h.getLng() > minLng && h.getLng() < maxLng) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle("Quality of care concern?")
                        .setMessage("Are you on Medicare and concerned about the quality of care you are received at " + h.getName() + ", would you like to call the quality Helpline?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + phoneNumber));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_dialer)
                        .show();
            }
        }
    }

    private ArrayList<HospitalResults> viewDatabase(){
        Cursor databaseCursor = database.getAllRows();
        return HelperFunctions.GetHospitalResultsArray(databaseCursor);
    }
}
