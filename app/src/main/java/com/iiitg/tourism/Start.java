package com.iiitg.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        name=findViewById(R.id.app_name);
        String text="<font color=#f49805>I</font><font color=#168990>n</font><font color=#c5165b>d</font>" +
                "<font color=#e44817>i</font><font color=#067893>e</font><font color=#8e284d>T</font>" +
                "<font color=#1da18a>o</font><font color=#e86a15>u</font><font color=#e9467d>r</font>";
        name.setText(Html.fromHtml(text));
        name.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Start.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);

    }
}