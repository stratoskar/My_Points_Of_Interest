package com.unipi.stratoskar.mypois;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewALL extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all);

        // define the SQLite database
        db = openOrCreateDatabase("poi.db",MODE_PRIVATE,null);

        // Run a SELECT query to fetch all the data in database
        Cursor cursor = db.rawQuery("Select * from MYPOI",null);

        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()){
            builder.append("Title:").append(cursor.getString(0)).append("\n");
            builder.append("Timestamp:").append(cursor.getString(1)).append("\n");
            builder.append("Longitude:").append(cursor.getString(2)).append("\n");
            builder.append("Latitude:").append(cursor.getString(3)).append("\n");
            builder.append("Category:").append(cursor.getString(4)).append("\n");
            builder.append("Description:").append(cursor.getString(5)).append("\n");
        }
        showMessage("View all the POIs:",builder.toString());
    }

    /*
     * Create a nice output message for the user
     */
    public void showMessage(String title, String text){
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(text).show();
    }
}
