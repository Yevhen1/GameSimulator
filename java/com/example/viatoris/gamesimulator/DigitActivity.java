package com.example.viatoris.gamesimulator;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;

import java.util.Random;



public class DigitActivity extends AppCompatActivity {

    private TableLayout tbLayout;
    private Button tbButton[][];
    private DisplayMetrics metrics;
    private int widthPx;
    private int digitArray[][];
    private int minNumber = 5;
    private int maxNumber = 15;

    private int sum = 0;
    private int clickCount = 0;
    private int sumArray[][];

    private ProgressBar progressBar;
    private ImageView imageViewMenu;
    private ImageView imageViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_numbers);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#aaaaaa'>" + getText(R.string.digit_title_activity) +"</font>"));

        Storage.descriptionCount = 2;
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        sumArray = new int[3][2];

        progressBar = findViewById(R.id.progressBar2);
        progressBar.getProgressDrawable().setColorFilter(
                Color.parseColor("#632983"), android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setScaleY(3f);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        widthPx = metrics.widthPixels;
        addButton(5, 5);
        timeGame(Storage.time);

        imageViewMenu = findViewById(R.id.imageView4);
        imageViewDescription = findViewById(R.id.imageViewMenu2);

        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DigitActivity.this, MenuActivity.class);
                startActivity(intent);
//                flagPlay = true;
                Storage.timer.cancel();

            }
        });
        imageViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DigitActivity.this, DescriptionActivity.class);
                startActivity(intent);
//                flagPlay = true;
                Storage.timer.cancel();

            }
        });

    }

    private void addButton(int rows, int columns ){
        int side = (metrics.widthPixels - (16 + rows * 2)) / rows;
        tbLayout = findViewById(R.id.digitTabel);
        tbButton = new Button[rows][columns];
        digitArray = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tbLayout.setColumnShrinkable(0, false);
            tableRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            for (int k = 0; k < columns; k++){
                final Button bt = new Button(this);
                final int finalRows = i;
                final int finalColumns = k;
                digitArray[i][k] = getSomeDigit(minNumber, maxNumber);
                bt.setText(digitArray[i][k] + "");
//                bt.setId(digitArray[i][k]);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculationDigit(bt, finalRows, finalColumns);
                    }
                });
                bt.setTextSize(30.0f);
                bt.setTextColor(Color.parseColor("#632983"));
                tableRow.addView(bt, side ,side);
                bt.setBackgroundResource(R.drawable.circle_border_button);
                tbButton[i][k] = bt;
            }
            tbLayout.addView(tableRow, i);
        }
    }

    public void newGame(){
//        flagPlay = true;
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        setNewTable();
        Storage.timer.start();
        while (true){
            setNewTable();
            if (getAllVariant(33) != 0) break;
        }
        setNewTable();
//        getCrossVariant(36);
//        timeGame(25000);
    }

    public void backMenu(){
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        Storage.timer.cancel();
        Intent intent = new Intent(DigitActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void setNewTable(){
        for (int i = 0; i < digitArray.length; i++){
            for (int k = 0; k < digitArray.length; k++){
                digitArray[i][k] = getSomeDigit(minNumber, maxNumber);
                tbButton[i][k].setText(digitArray[i][k] + "");
            }
        }
    }

//  click button
    @NonNull
    private void calculationDigit(Button bt, int rows,int column){
            bt.setBackgroundResource(R.drawable.circle_fill_button);
            bt.setTextColor(Color.parseColor("#e0cfe2"));
            sumArray[clickCount][0] = rows;
            sumArray[clickCount][1] = column;
            clickCount++;
            sum += digitArray[rows][column];

            if (clickCount == 3) {
                if (sum == 33) {
                    while (true) {
                        digitArray[sumArray[0][0]][sumArray[0][1]] = getSomeDigit(minNumber, maxNumber);
                        digitArray[sumArray[1][0]][sumArray[1][1]] = getSomeDigit(minNumber, maxNumber);
                        digitArray[sumArray[2][0]][sumArray[2][1]] = getSomeDigit(minNumber, maxNumber);
                        if (getAllVariant(33) != 0) {
                            tbButton[sumArray[0][0]][sumArray[0][1]].setText(digitArray[sumArray[0][0]][sumArray[0][1]] + "");
                            tbButton[sumArray[1][0]][sumArray[1][1]].setText(digitArray[sumArray[1][0]][sumArray[1][1]] + "");
                            tbButton[sumArray[2][0]][sumArray[2][1]].setText(digitArray[sumArray[2][0]][sumArray[2][1]] + "");
                            Storage.trueAnswer++;
                            break;
                        }
                    }
                } else Storage.falseAnswer++;

                tbButton[sumArray[0][0]][sumArray[0][1]].setBackgroundResource(R.drawable.circle_border_button);
                tbButton[sumArray[1][0]][sumArray[1][1]].setBackgroundResource(R.drawable.circle_border_button);
                tbButton[sumArray[2][0]][sumArray[2][1]].setBackgroundResource(R.drawable.circle_border_button);

                tbButton[sumArray[0][0]][sumArray[0][1]].setTextColor(Color.parseColor("#632983"));
                tbButton[sumArray[1][0]][sumArray[1][1]].setTextColor(Color.parseColor("#632983"));
                tbButton[sumArray[2][0]][sumArray[2][1]].setTextColor(Color.parseColor("#632983"));
                clickCount = 0;
                sum = 0;
            }
    }

    private int getSomeDigit(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

//    private void getSumAll(){
//        TextView textView = findViewById(R.id.textVariant);
//        int x = 0;
//        for (int i = 0; i < digitArray.length; i++){
//            for (int k = 0; k < digitArray.length; k++){
//            x += digitArray[i][k];
//            }
//        }
//        textView.setText(x + "");
//    }

//    private void getCrossVariant(int sum){
//        TextView textView = findViewById(R.id.textVariant);
//        int crossVariant = 0;
//        for (int i = 0; i < digitArray.length; i++){
//            for (int k = 0; k < digitArray.length -3; k++){
//                int x = digitArray[i][k] + digitArray[i][k+1] + digitArray[i][k+2];
//                if (sum == x){crossVariant += 1;}
//            }
//        }
//
//        for (int k = 0; k < digitArray.length; k++){
//            for (int i = 0; i < digitArray.length - 3; i++){
//                int x = digitArray[i][k] + digitArray[i+1][k] + digitArray[i+2][k];
//                if (sum == x){crossVariant += 1;}
//            }
//        }
//        textView.setText(crossVariant + "==" + getAllVariant(36));
//    }

    private int getAllVariant(int sum1){

        int returnDigit = 0;

        for (int i1 = 0; i1 < digitArray.length; i1++){
            for (int k1 = 0; k1 < digitArray.length; k1++){
                for (int i2 = 0; i2 < digitArray.length; i2++){
                    for (int k2 = 0; k2 < digitArray.length; k2++){
                        for (int i3 = 0; i3 < digitArray.length; i3++){
                            for (int k3 = 0; k3 < digitArray.length; k3++){
                                if (i1 == i2 && k1 == k2 || i2 == i3 && k2 == k3 || i1 == i3 && k1 == k3)continue;
                                if ((digitArray[i1][k1] + digitArray[i2][k2] + digitArray[i3][k3]) == sum1){
//                                    System.out.println(i1 + "-" + k1 + ":" + i2 + "-" + k2 + ":" + i3 + "-" + k3 );
                                    returnDigit+=1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return returnDigit;
    }


    private void timeGame(final long countTime){
//        progressBar = findViewById(R.id.progressBar);
//        flagPlay = true;
        Storage.timer = new CountDownTimer(countTime, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress((int)l / (int) (Storage.time/100));
            }
            @Override
            public void onFinish() {
                progressBar.setProgress(0);
//                flagPlay = false;
//                FragmentManager manager = getSupportFragmentManager();
//                new DialogFinish().show(manager, "dialog");
                Intent intent = new Intent(DigitActivity.this, DialogFinish.class);
                startActivity(intent);
            }
        };
        Storage.timer.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Storage.timer.cancel();
    }
}
