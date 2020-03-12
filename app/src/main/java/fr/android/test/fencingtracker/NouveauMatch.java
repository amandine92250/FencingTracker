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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NouveauMatch extends AppCompatActivity {
    ListView sampleList;
    private TextView matchView;
    private DatabaseManger databaseManger;
    EditText nom1,nom2;
   // Button addData , viewData;

    private TextView latitude;
    private TextView longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_match);
        setTitle("Nouveau match");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nom1=(EditText) findViewById(R.id.joueur1);
        nom2=(EditText) findViewById(R.id.joueur2);

        databaseManger = new DatabaseManger (this);

        /*addData=(Button) findViewById(R.id.testButton);
        AjoutBDD();
        */

        /*
        viewData=(Button) findViewById(R.id.testButtonaffiche);
        ViewAll();

         */

        //Quand on click sur le boutton geolocaliser

        Button myButtonPhoto = (Button) findViewById(R.id.ButtonPhoto);
        myButtonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mynewIntentPhotos = new Intent(NouveauMatch.this, Photos.class);
                startActivity(mynewIntentPhotos);
            }
        });

        databaseManger.close();

    }




    public void AjoutBDD()
    {
        boolean isInsert=databaseManger.insertScore(nom1.getText().toString(),nom2.getText().toString());

        if(isInsert==true)
        {
            Toast.makeText(NouveauMatch.this,"inserer avec succe",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(NouveauMatch.this,"PAAS inserer ",Toast.LENGTH_LONG).show();

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

     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
         TextView test=new TextView(this);

         return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_demarrer:
                AjoutBDD();
                Intent myIntentDonneeMatch=new Intent(NouveauMatch.this, DonneeMatch.class);
                startActivity(myIntentDonneeMatch);
                return true;

            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
