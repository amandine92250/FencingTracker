package fr.android.test.fencingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DonneeMatch extends AppCompatActivity {

    private TextView scoreView;
    private DatabaseManger databaseManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnee_match);

        scoreView = (TextView) findViewById(R.id.scoreView);
        databaseManger = new DatabaseManger(this);

        databaseManger.insertScore("Bruno", 100);
        databaseManger.insertScore("Axelle", 200);

        databaseManger.close();

    }
}
