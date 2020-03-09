package fr.android.test.fencingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class NouveauMatch extends AppCompatActivity {
    ListView sampleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_match);
        setTitle("Nouveau match");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
         TextView test=new TextView(this);
         test.setText("AAaaa");
         test.setTextColor(getResources().getColor(R.color.colorAccent));
         return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_demarrer:
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
