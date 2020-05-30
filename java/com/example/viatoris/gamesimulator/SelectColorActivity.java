package com.example.viatoris.gamesimulator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;

public class SelectColorActivity extends AppCompatActivity {
    private int colorName;
    private int colorValue;
    private String []allColorName = {"RED", "BLUE", "GREEN", "YELLOW"};
    private int []allColorValue = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    private TextView textColor;
    private ConstraintLayout layout;
    private ProgressBar progressBar;
    private ImageView imageViewMenu;
    private ImageView imageViewDescription;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_select_color);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#aaaaaa'>" + getText(R.string.select_color_title_activity) +"</font>"));
        layout = findViewById(R.id.main_panel);
        setSomeColor();
        timeGame(Storage.time);
        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(
                Color.parseColor("#632983"), android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setScaleY(3f);
        Storage.descriptionCount = 1;
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SelectColorActivity.this);
        myEditor = sharedPreferences.edit();
//        setToMember();

        imageViewMenu = findViewById(R.id.imageView3);
        imageViewDescription = findViewById(R.id.imageViewMenu);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.timer.cancel();
                Intent intent = new Intent(SelectColorActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        imageViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.timer.cancel();
                Intent intent = new Intent(SelectColorActivity.this, DescriptionActivity.class);
                startActivity(intent);
//                FragmentManager manager = getSupportFragmentManager();
//                new DialogFinish().show(manager, "dialog");
            }
        });
    }

//    public void newGame(){
//        Storage.trueAnswer = 0;
//        Storage.falseAnswer = 0;
//        setSomeColor();
//        Storage.timer.start();
//    }
    private void setSomeColor(){
        textColor = findViewById(R.id.colorNameTextView);
        Random random = new Random();
        colorName = random.nextInt(4);
        colorValue = random.nextInt(4);
        textColor.setText(allColorName[colorName]);
        textColor.setTextColor(allColorValue[colorValue]);
    }

//    public void backMenu(){
//        Storage.trueAnswer = 0;
//        Storage.falseAnswer = 0;
//        Storage.timer.cancel();
//        Intent intent = new Intent(SelectColorActivity.this, MainActivity.class);
//        startActivity(intent);
//    }

//    private void setToMember(){
//        myEditor.putInt("true", 0);
//        myEditor.putInt("false", 0);
//        myEditor.commit();
//    }
//    private void getMember(){
//        Storage.trueStorage = sharedPreferences.getInt("true", 0);
//        Storage.falseStorage = sharedPreferences.getInt("false", 0);
//    }
    private void saveResult(){
        if (sharedPreferences.getInt("true", 0) < Storage.trueAnswer){
            myEditor.putInt("true", Storage.trueAnswer);
            myEditor.putInt("false", Storage.falseAnswer);
            myEditor.commit();
            Storage.trueStorage = Storage.trueAnswer;
            Storage.falseStorage = Storage.falseAnswer;
        }else{
            Storage.trueStorage = sharedPreferences.getInt("true", 0);
            Storage.falseStorage = sharedPreferences.getInt("false", 0);
        }
    }

//    private void compareColor(int thisColor){
//        if (thisColor == allColorValue[colorValue]){
//            extendTime();
//        }else {
//            layout.setBackgroundColor(Color.parseColor("#FB3F51"));
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    layout.setBackgroundColor(Color.parseColor("#515658"));
//                }
//            }, 100);
//        }
//        setSomeColor();
//    }

    private void compareColor(int thisColor){
        if (thisColor == allColorValue[colorValue]){
            Storage.trueAnswer++;
        }else {
            Storage.falseAnswer++;
        }
        setSomeColor();
    }

    private void timeGame(long countTime){
//        progressBar = findViewById(R.id.progressBar);
//        Storage.beginFlag = true;
        Storage.timer = new CountDownTimer(countTime, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress((int)l / (int) (Storage.time / 100));
//                time = l;
            }
            @Override
            public void onFinish() {
//                getMember();
                saveResult();
                progressBar.setProgress(0);
//                Storage.beginFlag = false;
//                FragmentManager manager = getSupportFragmentManager();
//                new DialogFinish().show(manager, "dialog");//fack
                Intent intent = new Intent(SelectColorActivity.this, DialogFinish.class);
                startActivity(intent);
            }
        };
        Storage.timer.start();
    }

    public void clickButton(View view){
//        if (Storage.beginFlag){
            switch (view.getId()) {
                case R.id.button_red:
                    compareColor(allColorValue[0]);
                    break;
                case R.id.button_blue:
                    compareColor(allColorValue[1]);
                    break;
                case R.id.button_green:
                    compareColor(allColorValue[2]);
                    break;
                case R.id.button_yellow:
                    compareColor(allColorValue[3]);
                    break;
            }
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Storage.timer.cancel();
    }
}
