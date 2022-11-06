package com.unipi.stratoskar.mypois;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewPoi extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String latitude, longitude;
    EditText title,description;
    RadioButton radioButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_poi);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        title = (EditText)findViewById((R.id.editTextTextPersonName));
        description = (EditText)findViewById(R.id.editTextTextPersonName2);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Grand access to location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
    }

    // This method is called in order to add a new POI to database
    public void addNewPOItoDatabase(View view)
    {
        // Gather POI's information
        String categoryValue = takeRadioButtonValue();
        String titleValue = title.getText().toString();
        String descriptionValue = description.getText().toString();
        String timestamp = getCurrentTimeStamp();

        //Toast.makeText(this,timestamp+","+categoryValue+","+titleValue+","+descriptionValue,Toast.LENGTH_SHORT).show();
    }

    /*
     * This method return the value of Radio Button that user selected.
     * If no radio button was checked, then the method return's "Nothing Selected"
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

    /**
     * Get system's current timestamp
     * @return yyyy-MM-dd HH:mm:ss format date as string
     */
    public String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            return currentDateTime;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        Toast.makeText(this,location.getLatitude()+" , "+location.getLongitude(),Toast.LENGTH_SHORT).show();
    }
}

