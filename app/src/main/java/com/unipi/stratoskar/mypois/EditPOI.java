package com.unipi.stratoskar.mypois;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class EditPOI extends AppCompatActivity {

    private EditText oldTitle,newTitle,description;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_poi);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup23);
        oldTitle = (EditText)findViewById(R.id.editTextTextPersonName6);
        newTitle = (EditText)findViewById(R.id.editTextTextPersonName8);
        description = (EditText)findViewById(R.id.editTextTextPersonName9);
    }

    /*
     *This method is called in order to edit an existing POI in database
     */
    public void EditNewPOItoDatabase(View view)
    {
        // Gather POI's information
        String categoryValue = takeRadioButtonValue();
        String oldTitleValue = oldTitle.getText().toString();
        String newTitleValue = newTitle.getText().toString();
        String descriptionValue = description.getText().toString();
        String table_name = "MYPOI";

        // try to run an UPDATE query
        try
        {
            db = openOrCreateDatabase("poi.db",MODE_PRIVATE,null);

            // on below line we are passing all values
            // along with its key and value pair.
            ContentValues values = new ContentValues();
            values.put("title", newTitleValue);
            values.put("category", categoryValue);
            values.put("description", descriptionValue);

            if(db.update(table_name, values, "title=?", new String[]{oldTitleValue}) > 0)
            {
                showMessage("Update data: Success", "Record where successfully updated!");
            }
            else
            {
                showMessage("Update data: No result","No record was affected!");
            }
        }
        catch (Exception e) // problem with the database
        {
            showMessage("Update data: Fail","There was a problem with data updating!");
        }
    }

    /*
     * Create a nice output message for the user
     */
    public void showMessage(String title, String text)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(text).show();
    }

    /*
     * This method returns the value of Radio Button that user selected.
     * If no radio button was checked, then the method returns "Nothing Selected".
     */
    public String takeRadioButtonValue() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        if (selectedId == -1) {
            return "Nothing Selected";
        } else {
            return (String) radioButton.getText();
        }
    }
}
