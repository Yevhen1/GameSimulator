package com.example.viatoris.gamesimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    ImageView imageOK;
    ImageView brain;
    TextView brainTrainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logo);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                Storage.beginFlag = true;
                LogoActivity.super.finish();

            }
        }, 1500, 500);

        getSupportActionBar().setTitle("");
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);

        int width = dM.widthPixels;
        int height = dM.heightPixels;
        getWindow().setLayout(width, height);

    }
}

