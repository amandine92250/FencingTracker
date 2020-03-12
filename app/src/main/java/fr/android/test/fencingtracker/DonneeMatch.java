package fr.android.test.fencingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class DonneeMatch extends AppCompatActivity {

    private TextView matchView;
    private DatabaseManger databaseManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnee_match);

        matchView = (TextView) findViewById(R.id.matchView);

        databaseManger = new DatabaseManger (this);

/*
        List<Match> matchs = databaseManger.readTop10();
        for(Match match : matchs)
        {
            matchView.append(match.toString());
        }


 */

        databaseManger.close();

    }

}
