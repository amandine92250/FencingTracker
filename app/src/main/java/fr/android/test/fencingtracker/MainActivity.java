package fr.android.test.fencingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }
}
