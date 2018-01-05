package com.livanta.medicarequalityhelpline_geolocation;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.gcm.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;

/**
 * Created by bradcollins on 1/3/18.
 */

public class MyApplication extends Application {

    private Context context;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    public DBAdapter hospitalDatabase;


    @Override
    public void onCreate() {
        super.onCreate();

        //shared prefs
        context = this;
        sharedPrefs = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();

        //database
        hospitalDatabase = new DBAdapter(this);
        hospitalDatabase.open();
    }

    public void setPhoneNumberPrefs(String phoneNumber) {
        editor.putString(getString(R.string.phone_number_key), phoneNumber);
        editor.commit();
    }

    public String getPhoneNumberPrefs() {
        return sharedPrefs.getString(getString(R.string.phone_number_key), null);
    }

}
