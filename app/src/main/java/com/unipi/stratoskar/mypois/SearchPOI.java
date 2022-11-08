package com.unipi.stratoskar.mypois;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchPOI extends AppCompatActivity {

    Button search;
    EditText addTitleToSearch;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_poi);

        search = (Button)findViewById(R.id.searchButton);
        addTitleToSearch = (EditText) findViewById(R.id.searchTitle);
    }

    /*
     * This method runs a SELECT query on the SQLite DB, in order
     * to fetch the record needed!
     */
    public void onClickSearchButton(View view)
    {
        // take user's  input
        String titleToBeSearched = addTitleToSearch.getText().toString();

        // Search to the database
        try
        {
            // define the SQLite database
            db = openOrCreateDatabase("poi.db",MODE_PRIVATE,null);

            // Create the SELECT query
            Cursor cursor = db.rawQuery("SELECT * FROM MYPOI where title ="+addTitleToSearch, null);
            StringBuilder builder = new StringBuilder();
            while (cursor.moveToNext()){
                builder.append("Title:").append(cursor.getString(0)).append("\n");
                builder.append("Timestamp:").append(cursor.getString(1)).append("\n");
                builder.append("Longitude:").append(cursor.getString(2)).append("\n");
                builder.append("Latitude:").append(cursor.getString(3)).append("\n");
                builder.append("Category:").append(cursor.getString(4)).append("\n");
                builder.append("Description:").append(cursor.getString(5)).append("\n");
            }
            showMessage("Here are the results:",builder.toString());
        }
        catch (Exception e)
        {
            showMessage("Error","There was an error. Please try again later!");
        }
    }

    /*
     * Create a nice output message for the user
     */
    public void showMessage(String title, String text)
    {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(text)
                .show();
    }
}
