package com.example.viatoris.gamesimulator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionActivity extends Activity{

    private Button buttonOk;
    private TextView textTitle;
    private TextView descriptionRules;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_digit_description);
        setContentView(R.layout.activity_description);
        textTitle = findViewById(R.id.textView2);
        descriptionRules = findViewById(R.id.textView6);

        switch (Storage.descriptionCount){
            case 0:break;
            case 1:
                textTitle.setText(R.string.select_color_title_activity);
                descriptionRules.setText(R.string.rule_select_color);
                break;
            case 2:
                textTitle.setText(R.string.digit_title_activity);
                descriptionRules.setText(R.string.rule_digit_sum);
                break;
            case 3:
                textTitle.setText(R.string.select_icon_title_activity);
                descriptionRules.setText(R.string.rule_select_icon);
                break;
            case 4:
                textTitle.setText(R.string.remember_numbers_title_activity);
                descriptionRules.setText(R.string.rule_remember_number);
                break;
            default:break;
        }


//        DisplayMetrics dM = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dM);
//        int width = dM.widthPixels;
//        int height = dM.heightPixels;
//        getWindow().setLayout(width, height);

    }

    public void ClickButtonOk(View view){
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        Storage.timer.start();
        DescriptionActivity.super.finish();
    }
}
