package com.unipi.stratoskar.mypois;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DeletePOI extends AppCompatActivity {

    private SQLiteDatabase db;
    private EditText addTitle;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_poi);

        addTitle = (EditText) findViewById(R.id.editTextTextPersonName5);
        deleteButton = (Button)findViewById((R.id.button7));
    }

    public void onClickDeleteButton(View view)
    {

        String titleToBeDeleted = addTitle.getText().toString();
        String table_name = "MYPOI";

        // Delete row from database
        try
        {
            db = openOrCreateDatabase("poi.db",MODE_PRIVATE,null);
            if(db.delete(table_name, "title=?", new String[]{titleToBeDeleted}) > 0)
            {
                showMessage("Deletion: Success","Record has been deleted!");
            }
            else
            {
                showMessage("Deletion: Fail","Failed to delete the row!");
            }
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
