package fr.android.test.fencingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NouveauJoueur extends AppCompatActivity {

    EditText prenom,nom;
    Button ajoutJoueur;
    private DatabaseManger databaseManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_joueur);
        setTitle("Nouveau joueur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prenom=(EditText) findViewById(R.id.prenom);
        nom=(EditText) findViewById(R.id.nom);

        databaseManger = new DatabaseManger (this);


        //clic sur ajout ramene a la page principale
        Button ajoutJoueur = (Button) findViewById(R.id.ajoutJoueur);
        ajoutJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjoutBDD();
                Intent mynewIntentJoueur = new Intent(NouveauJoueur.this, MainActivity.class);
                startActivity(mynewIntentJoueur);
            }
        });

        databaseManger.close();


    }

    // Ajouter le joueur à la BDD
    public void AjoutBDD()
    {
        boolean isInsert=databaseManger.insertJoueur(prenom.getText().toString(),nom.getText().toString());

        if(isInsert==true)
        {
            Toast.makeText(NouveauJoueur.this,"Ajouté avce succes",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(NouveauJoueur.this,"Probleme insertion ",Toast.LENGTH_LONG).show();

        }

    }


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
