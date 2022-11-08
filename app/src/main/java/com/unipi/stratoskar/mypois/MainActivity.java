package com.unipi.stratoskar.mypois;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define the SQLite database
        db = openOrCreateDatabase("poi.db",MODE_PRIVATE,null);

        // create sql database
        db.execSQL("Create table if not exists MYPOI("+
                "title TEXT PRIMARY KEY,"+
                "timestamp TEXT,"+
                "longtitude TEXT," +
                "latitude TEXT," +
                "category TEXT," +
                "description TEXT)");
    }

    // Add new POI button
    public void add_new_POI(View view) {
        Intent intent = new Intent(this,AddNewPoi.class);
        startActivity(intent);
    }

    // Edit POI button
    public void edit_POI(View view){
        Intent intent = new Intent(this,EditPOI.class);
        startActivity(intent);
    }

    // Delete POI button
    public void delete_POI (View view){
        Intent intent = new Intent(this,DeletePOI.class);
        startActivity(intent);
    }

    // View all POIs button
    public void view_all (View view){
        Intent intent = new Intent(this,ViewALL.class);
        startActivity(intent);
    }

    // Search POI button
    public void search_POI(View view){
        Intent intent = new Intent(this,SearchPOI.class);
        startActivity(intent);
    }
}