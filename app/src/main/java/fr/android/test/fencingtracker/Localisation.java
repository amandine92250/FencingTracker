package fr.android.test.fencingtracker;


import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Localisation extends AppCompatActivity  {
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localisation);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        Button buttonLocation = findViewById(R.id.ButtonLocation);
        buttonLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(ActivityCompat.checkSelfPermission(Localisation.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    return;
                }

                client.getLastLocation().addOnSuccessListener(Localisation.this, new OnSuccessListener<Location>(){

                    @Override
                    public void onSuccess(Location location) {

                        if(location != null){
                            //TextView textView = findViewById(R.id.TextView04);
                            //textView.setText(location.toString());
                        }
                    }
                });
            }
        });

    }

    private  void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }


}

