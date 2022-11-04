package com.unipi.stratoskar.mypois;

import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements LocationListener {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define the SQLite database
        db = openOrCreateDatabase("poi.db",MODE_PRIVATE,null);

        // create sql database
        db.execSQL("Create table if not exists MYPOIS("+
                "title TEXT,"+
                "timestamp TEXT,"+
                "longtitude TEXT PRIMARY KEY," +
                "latitude TEXT," +
                "category TEXT," +
                "description TEXT)");


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}