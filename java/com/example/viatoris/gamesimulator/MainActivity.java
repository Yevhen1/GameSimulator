package com.example.viatoris.gamesimulator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btColor;
//    private Button btDigit;
    private Button btIcon;
//    private Button btMemberDigit;
//    private String colorMabi = "#282828";
    private ImageView brain;
    private TextView brainTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#aaaaaa'>" + getText(R.string.main_title_activity) +"</font>"));

        setContentView(R.layout.activity_logo);
        getSupportActionBar().setTitle("");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//            Storage.beginFlag = true;
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
//            MainActivity.super.finish();
            }
        }, 1500);


//        brain.findViewById(R.id.imageVievBrain);
//        brainTrainer.findViewById(R.id.textViewBrain);
//        brain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//                startActivity(intent);
//            }
//        });

//        if (!Storage.beginFlag){
//            Intent intent = new Intent(MainActivity.this, LogoActivity.class);
//            startActivity(intent);
//        }

/*
        btColor = findViewById(R.id.buttonSelectColor);
        btColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SelectColorActivity.class);
                startActivity(myIntent);
            }
        });

        btIcon = findViewById(R.id.buttonDigit);
        btIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DigitActivity.class);
                startActivity(myIntent);
            }
        });

        btIcon = findViewById(R.id.buttonSelectIcon);
        btIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SelectIconActivity.class);
                startActivity(myIntent);
            }
        });

        btIcon = findViewById(R.id.buttonMemberDigit);
        btIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DialogFinish.class);
                startActivity(myIntent);
            }
        });

//        btIcon = findViewById(R.id.buttonFastSelect);
//        btIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LogoActivity.class);
//                startActivity(intent);
//            }
//        });
*/

    }
}
