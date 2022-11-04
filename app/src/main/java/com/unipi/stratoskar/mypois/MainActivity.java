package com.unipi.stratoskar.mypois;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LocationListener {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define the SQLite database
        db = openOrCreateDatabase("POIs.db",MODE_PRIVATE,null);

        // create table
        db.execSQL("Create table if not exists POIS("+
                "Title TEXT," +
                "Timestamp TEXT,"+
                "Latitude TEXT PRIMARY KEY,"+
                "Longtitude TEXT PRIMARY KEY,"+
                "Category TEXT,"+
                "Description TEXT)");
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}