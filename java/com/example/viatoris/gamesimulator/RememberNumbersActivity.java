package com.example.viatoris.gamesimulator;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class RememberNumbersActivity extends AppCompatActivity /*implements View.OnTouchListener*/{

    private TableLayout tbLayout;
    private ConstraintLayout layout;
    private Button tbButton[][];
    private DisplayMetrics metrics;
    private int widthPx;
    private int digitArray[][];
    private int flagSum;
    private int timeMemory = 4;
    private boolean flagButtonActive;
    private CountDownTimer timer;
    private ImageView imageViewDescription;
    private ImageView imageViewMenu;
//    private CountDownTimer mainTimerSimulator;
    private ProgressBar mainProgressBar;
    private TextView timerMemory;
    private int maxNumber = 3;
    private int trueAnsver;
    private int trueAnsverInRow;
    private int level = 0;
    private int side = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_numbers);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#aaaaaa'>" + getText(R.string.remember_numbers_title_activity) +"</font>"));
        layout = findViewById(R.id.main_panel);
        mainProgressBar = findViewById(R.id.ProgressBar);
        mainProgressBar.getProgressDrawable().setColorFilter(
                Color.parseColor("#632983"), android.graphics.PorterDuff.Mode.SRC_IN);
        mainProgressBar.setScaleY(3f);

        Storage.descriptionCount = 4;
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        widthPx = metrics.widthPixels;
        addButton(side, side);
        fillButtonArray(maxNumber);
        memberTimer();
        mainTimer();

        imageViewMenu = findViewById(R.id.imageView6);
        imageViewDescription = findViewById(R.id.imageView2);
        imageViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.timer.cancel();
                Intent intent = new Intent(RememberNumbersActivity.this, DescriptionActivity.class);
                startActivity(intent);
            }
        });
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.timer.cancel();
                Intent intent = new Intent(RememberNumbersActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event){
//        return true;
//    }

    private void addButton(int rows, int columns ){
        int side = (metrics.widthPixels - (16 + rows * 2)) / rows;
        tbLayout = findViewById(R.id.digitTabel);
        tbButton = new Button[rows][columns];
        digitArray = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tbLayout.setColumnShrinkable(0, false);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int k = 0; k < columns; k++){
                final Button bt = new Button(this);
                digitArray[i][k] = -1;
                bt.setId(-1);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculationDigit(bt);
                    }
                });
                tableRow.addView(bt, side ,side);
                bt.setBackgroundResource(R.drawable.circle_border_button);
                bt.setTextSize(30.0f);
                bt.setTextColor(Color.parseColor("#632983"));
                tbButton[i][k] = bt;
            }
            tbLayout.addView(tableRow, i);
        }
        flagButtonActive = false;
    }

    private void calculationDigit(Button bt){
        if (flagButtonActive) {
            if (bt.getId() == maxNumber - 1){
                bt.setText(bt.getId() + "");
//                bt.setBackgroundResource(R.drawable.circle_fill_button);
//                bt.setTextColor(Color.parseColor("#e0cfe2"));
                timeWatch();
                Storage.trueAnswer++;
                level++;
            }else {
                if (bt.getId() - flagSum == 1) {
                    bt.setText(bt.getId() + "");
//                    bt.setBackgroundResource(R.drawable.circle_fill_button);
//                    bt.setTextColor(Color.parseColor("#e0cfe2"));
                    flagSum = bt.getId();
                } else {
//                    clearId();
//                    clearArray();
//                    selectLevel();
//                    fillButtonArray(maxNumber);
//                    memberTimer();
////                    redBackgroundColor();
                    timeWatch();
                    if(level > 0)level--;
                    Storage.falseAnswer++;
                }
            }
        }else{
            timer.cancel();
            flagSum = 0;
            clearArray();
            flagButtonActive = true;
            timerMemory.setText("");
//            bt.setBackgroundResource(R.drawable.circle_border_button);
//            bt.setTextColor(Color.parseColor("#632983"));
        }
    }
    private void timeWatch(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clearId();
                clearArray();
                selectLevel();
                fillButtonArray(maxNumber);
                memberTimer();
            }
        }, 300);
    }
    private void redBackgroundColor(){
        layout.setBackgroundColor(Color.parseColor("#FB3F51"));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setBackgroundColor(Color.parseColor("#515658"));
            }
        }, 100);
    }
    private void blueBackgroundColor(){
        layout.setBackgroundColor(Color.parseColor("#5084F5"));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setBackgroundColor(Color.parseColor("#515658"));
            }
        }, 100);
    }

    private void fillButtonArray(int maxNumber){
        flagButtonActive = false;
        clearId();
        if (maxNumber <= (tbButton.length * tbButton.length)) {
            Random r = new Random();
            int rows;
            int columns;
            int result = 1;
            while (result < maxNumber) {
                rows = r.nextInt(tbButton.length);
                columns = r.nextInt(tbButton.length);
                if (tbButton[rows][columns].getId() == -1) {
                    tbButton[rows][columns].setText(result + "");
                    tbButton[rows][columns].setId(result);
                    result++;
                }
            }
        }
    }

    private void clearId(){
        for (int rows = 0; rows < tbButton.length; rows++){
            for (int columns = 0; columns < tbButton.length; columns++){
                tbButton[rows][columns].setId(-1);
            }
        }
    }

    private void clearArray(){
        for (int rows = 0; rows < tbButton.length; rows++){
            for (int columns = 0; columns < tbButton.length; columns++){
                tbButton[rows][columns].setText("");
            }
        }
    }

    private void memberTimer(){
        timerMemory = findViewById(R.id.textVariant);
        timer = new CountDownTimer(timeMemory * 1000, 100) {
            @Override
            public void onTick(long l) {
            timerMemory.setText(l / 1000 + "");
            }

            @Override
            public void onFinish() {
                flagSum = 0;
                clearArray();
                flagButtonActive = true;
                timerMemory.setText("");
            }
        };
        timer.start();
    }

    private void mainTimer(){

        Storage.timer = new CountDownTimer(Storage.time, 10){
            @Override
            public void onTick(long l) {
                mainProgressBar.setProgress((int)l/ (int) (Storage.time/100));
            }

            @Override
            public void onFinish() {
//                timerMemory = findViewById(R.id.textVariant);
//                timerMemory.setText("G_OVER");
//                create some window to display all information
//                FragmentManager manager = getSupportFragmentManager();
//                new DialogFinish().show(manager, "dialog");
                Intent intent = new Intent(RememberNumbersActivity.this, DialogFinish.class);
                startActivity(intent);
            }
        };
        Storage.timer.start();
    }

    private void selectLevel(){
        switch(level){
            case 0 :{
                maxNumber = 3;
                break;
            }case 1:{
                maxNumber = 4;
                break;
            }case 2:{
                maxNumber = 5;
                break;
            }case 3:{
                maxNumber = 6;
                break;
            }case 4 :{
                maxNumber = 7;
                break;
            }case 5:{
                maxNumber = 8;
                break;
            }case 6:{
                maxNumber = 9;
                break;
            }case 7:{
                maxNumber = 10;
                break;
            }case 8 :{
                maxNumber = 11;
                break;
            }case 9:{
                maxNumber = 12;
                break;
            }case 10:{
                maxNumber = 13;
                break;
            }case 11:{
                maxNumber = 7;
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Storage.timer.cancel();
    }
}
