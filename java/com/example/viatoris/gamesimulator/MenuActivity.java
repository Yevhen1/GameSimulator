package com.example.viatoris.gamesimulator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        int width = dM.widthPixels;
        int height = dM.heightPixels;
        getWindow().setLayout((int)(width*0.8), (int) (height*0.8));

        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
        bt4 = findViewById(R.id.button4);

        switch(Storage.descriptionCount){
            case 1:
                bt1.setBackgroundResource(R.drawable.select_menu_bottom);
                bt1.setTextColor(Color.parseColor("#e0cfe2"));
                break;
            case 2:
                bt2.setBackgroundResource(R.drawable.select_menu_bottom);
                bt2.setTextColor(Color.parseColor("#e0cfe2"));
                break;
            case 3:
                bt3.setBackgroundResource(R.drawable.select_menu_bottom);
                bt3.setTextColor(Color.parseColor("#e0cfe2"));
                break;
            case 4:
                bt4.setBackgroundResource(R.drawable.select_menu_bottom);
                bt4.setTextColor(Color.parseColor("#e0cfe2"));
                break;
                default: break;

        }
    }

    public void changeActivity(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.button:
                if (Storage.descriptionCount == 1){
                    setStorage();
                    break;
                }
                intent = new Intent(MenuActivity.this, SelectColorActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                if (Storage.descriptionCount == 2){
                    setStorage();
                    break;
                }
                intent = new Intent(MenuActivity.this, DigitActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                if (Storage.descriptionCount == 3){
                    setStorage();
                    break;
                }
                intent = new Intent(MenuActivity.this, SelectIconActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                if (Storage.descriptionCount == 4){
                    setStorage();
                    break;
                }
                intent = new Intent(MenuActivity.this, RememberNumbersActivity.class);
                startActivity(intent);
                break;
        }
        MenuActivity.super.finish();
    }

    private void setStorage(){
        Storage.timer.start();
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
    }

}
