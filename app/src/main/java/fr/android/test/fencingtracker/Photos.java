package fr.android.test.fencingtracker;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class Photos extends AppCompatActivity {

    Button btnPhoto;
    ImageView imgAffichePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        btnPhoto = (Button) findViewById(R.id.btnPhoto);
        imgAffichePhoto = (ImageView) findViewById(R.id.imgAffichePhoto);

        btnPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // new Intent pour ouvrir new fenetre pour prendre photo
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // ouvre intent
                startActivityForResult(intent, 0);
            }
        });

    }


    @Override
    //retour de l'appel de activityforresult
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        // affiche photo
        imgAffichePhoto.setImageBitmap(bitmap);
    }
}
