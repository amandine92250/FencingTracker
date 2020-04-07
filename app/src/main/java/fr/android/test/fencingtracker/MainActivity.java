package fr.android.test.fencingtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button viewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button myButtonNvMatch=(Button) findViewById(R.id.ButtonNvMatch);
        myButtonNvMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent explicite
                Intent myIntentNvMatch=new Intent(MainActivity.this, NouveauMatch.class);
                startActivity(myIntentNvMatch);
            }
        });

        Button myButtonNvJoueur=(Button) findViewById(R.id.ButtonNvJoueur);
        myButtonNvJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent explicite
                Intent myIntentNvJoueur=new Intent(MainActivity.this, NouveauJoueur.class);
                startActivity(myIntentNvJoueur);
            }
        });


        //On ouvre une page pour afficher les statistiques de 5 précédents matchs
        Button viewData=(Button) findViewById(R.id.testButtonaffiche);
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent explicite
                Intent myIntentStats=new Intent(MainActivity.this, Statistiques5matchs.class);
                startActivity(myIntentStats);
            }
        });
    }





}
