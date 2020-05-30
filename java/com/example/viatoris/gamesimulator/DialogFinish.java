package com.example.viatoris.gamesimulator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogFinish extends Activity{

    private TextView textViewTrue;
    private TextView textViewFalse;
    private TextView textViewBestTrue;
    private TextView textViewBestFalse;
    private Button buttonAgain;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEditor;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_dialog_finish);
           textViewTrue = findViewById(R.id.textView9);
           textViewFalse = findViewById(R.id.textView10);
           textViewBestTrue = findViewById(R.id.textView12);
           textViewBestFalse = findViewById(R.id.textView13);

           textViewTrue.setText( "correct answers - " + Storage.trueAnswer);
           textViewFalse.setText("wrong answers -   " + Storage.falseAnswer);
//           textViewBestTrue.setText( "correct answers - " + Storage.trueStorage);
//           textViewBestFalse.setText("wrong answers -   " + Storage.falseStorage);

           buttonAgain = findViewById(R.id.btn_first);
           buttonAgain.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Storage.trueAnswer = 0;
                   Storage.falseAnswer = 0;
                   Storage.timer.start();
                   DialogFinish.super.finish();
               }
           });

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(DialogFinish.this);
            myEditor = sharedPreferences.edit();
            storage();

            DisplayMetrics dM = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dM);
            int width = dM.widthPixels;
            int height = dM.heightPixels;
            getWindow().setLayout((int)(width*0.8), (int) (height*0.6));
        }

        private void storage(){
            if (sharedPreferences.getInt("true" + Storage.descriptionCount, 0) < Storage.trueAnswer){
                myEditor.putInt("true" + Storage.descriptionCount, Storage.trueAnswer);
                myEditor.putInt("false" + Storage.descriptionCount, Storage.falseAnswer);
                myEditor.commit();
                textViewBestTrue.setText( "correct answers - " + Storage.trueAnswer);
                textViewBestFalse.setText("wrong answers -   " + Storage.falseAnswer);
            }else {
                textViewBestTrue.setText( "correct answers - " + sharedPreferences.getInt("true" + Storage.descriptionCount, 0));
                textViewBestFalse.setText("wrong answers -   " + sharedPreferences.getInt("false" + Storage.descriptionCount, 0));
            }
        }

    }
















