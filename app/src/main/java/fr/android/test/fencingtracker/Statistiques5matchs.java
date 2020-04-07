package fr.android.test.fencingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;import java.util.List;
import android.database.Cursor;
import android.widget.ArrayAdapter;



public class Statistiques5matchs extends AppCompatActivity {
    private DatabaseManger databaseManger;
    private TextView matchView;
    Cursor c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques5matchs);
        setTitle("5 derniers matchs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseManger = new DatabaseManger(this);
        matchView = (TextView) findViewById(R.id.matchView);




        //OK

/*
        Cursor data=databaseManger.getAllData();
        while (data.moveToNext()) {
            matchView.append("Joueur 1 : " + data.getString(1) + " ");
            matchView.append("Joueur 2 : " + data.getString(2) + "\n" + "\n");

        }
        */


//Fin ok

        Cursor data2=databaseManger.get5matches();
        if (data2.moveToLast()) {
            matchView.append("Joueur 1 : " + data2.getString(1) + " ");
            matchView.append("Joueur 2 : " + data2.getString(2) + " ");
            matchView.append("Arme : " + data2.getString(3) + "\n" + "\n");

        }
        int i=0;
        while(i!=4)
        {
            if(data2.moveToPrevious())
            {
                matchView.append("Joueur 1 : " + data2.getString(1) + " ");
                matchView.append("Joueur 2 : " + data2.getString(2) + " ");
                matchView.append("Arme : " + data2.getString(3) + "\n" + "\n");
            };
            i++;

        }



/*
        List<Match> matchs = databaseManger.readTop10();
        for(Match match : matchs)
        {
            matchView.append(match.toString()+ "\n\n");
        }

 */


        databaseManger.close();
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




