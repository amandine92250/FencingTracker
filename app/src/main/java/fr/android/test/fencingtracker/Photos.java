package fr.android.test.fencingtracker;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Photos extends AppCompatActivity {

    Button btnPhoto;
    ImageView imgAffichePhoto;
    Button btnEnreg;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        btnPhoto = (Button) findViewById(R.id.btnPhoto);
        imgAffichePhoto = (ImageView) findViewById(R.id.imgAffichePhoto);
        btnEnreg = (Button) findViewById(R.id.btnEnreg);

        btnPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // new Intent pour ouvrir new fenetre pour prendre photo
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // ouvre intent
                startActivityForResult(intent, 0);
            }
        });

        //Enregister image dans galerie
        btnEnreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"enregister","image match");
                Toast.makeText(Photos.this,"Enregistré",Toast.LENGTH_LONG).show();

                }


        });

    }


    @Override
    //retour de l'appel de activityforresult
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap) data.getExtras().get("data");
        // affiche photo
        imgAffichePhoto.setImageBitmap(bitmap);
    }


}
