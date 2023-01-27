package com.iiitg.tourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().subscribeToTopic("Main");

        ImageSlider img=findViewById(R.id.slider);
        List<SlideModel> slideimg=new ArrayList<>();
        slideimg.add(new SlideModel(R.drawable.img1,"Tathagata Tsal, Sikkim",ScaleTypes.CENTER_CROP));
        slideimg.add(new SlideModel(R.drawable.img3,"Spiti Valley, Chandigarh",ScaleTypes.CENTER_CROP));
        slideimg.add(new SlideModel(R.drawable.img4,"Mahabalipuram, Chennai",ScaleTypes.CENTER_CROP));
        slideimg.add(new SlideModel(R.drawable.img5,"Hawa Mahal, Rajasthan",ScaleTypes.CENTER_CROP));
        slideimg.add(new SlideModel(R.drawable.img2,"Dhashawamedha Ghat, Varanasi",ScaleTypes.CENTER_CROP));

        img.setImageList(slideimg);

        Button unesco = findViewById(R.id.unesco);
        Button india = findViewById(R.id.india);
        Button underrated = findViewById(R.id.underrated);
        Button aware = findViewById(R.id.aware);
        Button raise = findViewById(R.id.raise);

        unesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Category.class);
                startActivity(i);
            }
        });

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,CategoryIndia.class);
                startActivity(i);

            }
        });

        underrated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,CategoryUnderrated.class);
                startActivity(i);
            }
        });

        aware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Awareness.class);
                startActivity(i);

            }
        });

        raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Issue.class);
                startActivity(i);
            }
        });
    }
}