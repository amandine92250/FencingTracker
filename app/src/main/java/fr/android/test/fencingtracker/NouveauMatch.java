package fr.android.test.fencingtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NouveauMatch extends AppCompatActivity {
    //ListView sampleList;
    //private TextView matchView;
    private DatabaseManger databaseManger;
    JSONArray ConversionJsonArray;

    Spinner joueur1,joueur2,arme;

    EditText attaques1,attaques2;
    EditText cattaques1,cattaques2;
    EditText points1,points2;


    private TextView latitude;
    private TextView longitude;
    private static AccesDistant accesDistant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_match);
        //setTitle("Nouveau match");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        joueur1=(Spinner) findViewById(R.id.spinner1);
        joueur2=(Spinner) findViewById(R.id.spinner2);
        arme=(Spinner) findViewById(R.id.spinner3);

        attaques1=(EditText) findViewById(R.id.attaquesj1);
        attaques2=(EditText) findViewById(R.id.attaquesj2);

        cattaques1=(EditText) findViewById(R.id.cattaquesj1);
        cattaques2=(EditText) findViewById(R.id.cattaquesj2);

        points1=(EditText) findViewById(R.id.pointsj1);
        points2=(EditText) findViewById(R.id.pointsj2);

        accesDistant = new AccesDistant();
        accesDistant.envoi("dernier", new JSONArray());

        databaseManger = new DatabaseManger (this);

        /*addData=(Button) findViewById(R.id.testButton);
        AjoutBDD();
        */

        /*
        viewData=(Button) findViewById(R.id.testButtonaffiche);
        ViewAll();

         */


        // Quand on clique sur le bouton photo
        Button myButtonPhoto = (Button) findViewById(R.id.ButtonPhoto);
        myButtonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mynewIntentPhotos = new Intent(NouveauMatch.this, Photos.class);
                startActivity(mynewIntentPhotos);
            }
        });

        // Quand on clique sur le bouton Localisation
        Button myButtonLocation =(Button) findViewById(R.id.ButtonLocation);
        myButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mynewIntentLocation = new Intent(NouveauMatch.this, MapsActivity.class);
                startActivity(mynewIntentLocation);
            }
        });

        // Quand on clique sur le bouton Ajout match
        Button myButtonAjout =(Button) findViewById(R.id.AjoutMatch);
        myButtonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AjoutBDD();
                List laListe = new ArrayList();
                laListe.add(joueur1.getSelectedItem().toString());
                laListe.add(attaques1.getText().toString());
                laListe.add(cattaques1.getText().toString());
                laListe.add(points1.getText().toString());
                laListe.add(joueur2.getSelectedItem().toString());
                laListe.add(attaques2.getText().toString());
                laListe.add(cattaques2.getText().toString());
                laListe.add(points2.getText().toString());
                laListe.add(arme.getSelectedItem().toString());

                ConversionJsonArray= new JSONArray(laListe);

                accesDistant.envoi("enreg",ConversionJsonArray);
                Intent myIntentDonneeMatch=new Intent(NouveauMatch.this, MainActivity.class);
                startActivity(myIntentDonneeMatch);

            }
        });


        //DropDown Joueur 1
        Spinner dropmenu1;
        dropmenu1 = (Spinner) findViewById(R.id.spinner1);
        List<String> joueurName = new ArrayList<>();
        joueurName = databaseManger.getAllNameJoueur();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, joueurName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropmenu1.setAdapter(dataAdapter);

        //DropDown Joueur 2
        Spinner dropmenu2;
        dropmenu2 = (Spinner) findViewById(R.id.spinner2);
        List<String> joueurName2 = new ArrayList<>();
        joueurName2 = databaseManger.getAllNameJoueur();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, joueurName2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropmenu2.setAdapter(dataAdapter2);


        //DropDown Arme
        Spinner dropmenu3;
        dropmenu3 = (Spinner) findViewById(R.id.spinner3);

        databaseManger.close();

    }

    public void AjoutBDD()
    {
        boolean isInsert=databaseManger.insertScore(joueur1.getSelectedItem().toString(),joueur2.getSelectedItem().toString(),attaques1.getText().toString(),cattaques1.getText().toString(), points1.getText().toString(),attaques2.getText().toString(), cattaques2.getText().toString(), points2.getText().toString(),arme.getSelectedItem().toString());

        if(isInsert==true)
        {
            Toast.makeText(NouveauMatch.this,"Ajouté avec succes",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(NouveauMatch.this,"Problème insertion ",Toast.LENGTH_LONG).show();

        }

    }






/*
    private void ViewAll()
    {
        viewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor data=databaseManger.getAllData();
                        StringBuffer buffer = new StringBuffer();
                        while (data.moveToNext()){
                            buffer.append("name1 : "+data.getString(1));
                            buffer.append("name2 : "+data.getString(2));

                        }
                        showMessage("data",buffer.toString());


                    }
                }
        );
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


 */

    /* public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
         TextView test=new TextView(this);

         return true;
    }*/

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {


            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
