package com.iiitg.tourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class places extends AppCompatActivity {
    TextView locat, temp,place,desp1,desp2,fflink,ytt,distt;
    Button visit;
    String apikey = "bee55b8f3740782d492dc2cae0ac8c83";
    String s;
    //FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        place = findViewById(R.id.Name);
        locat = findViewById(R.id.location);
        temp = findViewById(R.id.temp_diplay);
        desp1 = findViewById(R.id.box1);
        desp2 = findViewById(R.id.box2);
        visit=findViewById(R.id.visit);
        fflink=findViewById(R.id.flink);
        ytt=findViewById(R.id.yt);
        distt=findViewById(R.id.dist);

        String pic=getIntent().getStringExtra("pic");
        String pic1=getIntent().getStringExtra("pic1");
        String pic2=getIntent().getStringExtra("pic2");
        String name=getIntent().getStringExtra("name");
        String location=getIntent().getStringExtra("location");
        String des1=getIntent().getStringExtra("des1");
        String des2=getIntent().getStringExtra("des2");
        String link=getIntent().getStringExtra("link");
        String Grid=getIntent().getStringExtra("grid");

        double lat1 = 26.0812;
        double lat2=0.0;
        double lon1 =91.5620;
        double lon2=0.0;
        if(Grid.length()!=0) {
            List<String> latlong = Arrays.asList(Grid.split(","));
            if (latlong.size() == 2) {
                lat2 = Double.valueOf(latlong.get(0));
                lon2 = Double.valueOf(latlong.get(1));
            }
        }


        distance(lat1, lat2, lon1, lon2);

        String flink=getIntent().getStringExtra("flink");
        String yt=getIntent().getStringExtra("yt");



        ImageSlider img = findViewById(R.id.slider);
        List<SlideModel> slideimg = new ArrayList<>();
        slideimg.add(new SlideModel(pic, ScaleTypes.CENTER_CROP));
        slideimg.add(new SlideModel(pic1, ScaleTypes.CENTER_CROP));
        slideimg.add(new SlideModel(pic2, ScaleTypes.CENTER_CROP));
        img.setImageList(slideimg);

        place.setText(name);
        locat.setText(location);
        desp1.setText(des1);
        desp2.setText(des2);
        fflink.setText(flink);

        ytt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(yt));
                startActivity(i);
            }
        });


        fflink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(flink));
                startActivity(i);
            }
        });


        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(i);
            }
        });

        getweather(this);





    }


    public void getweather(places v){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<Example> examplecall=myapi.getweather(locat.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==404){
                    temp.setText("32° C");
                }
                else if(!(response.isSuccessful())){
                    temp.setText("32° C");
                }
                Example mydata=response.body();
                Main main=mydata.getMain();
                Double tempi=main.getTemp();
                Integer temperature=(int)(tempi-273.15);
                temp.setText(String.valueOf(temperature)+"° C");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(places.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public double distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 8000;

        // calculate the result

        distt.setText(""+Math.abs((int)(c*r))+" KM");
        return(c * r);
    }


}