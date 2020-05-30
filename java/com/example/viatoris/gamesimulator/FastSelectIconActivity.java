package com.example.viatoris.gamesimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class FastSelectIconActivity extends AppCompatActivity {


    private ImageView iconCenter;

    private ImageView iconUp1;
    private ImageView iconUp2;

    private ImageView iconDown;
    private ImageView iconLeft;
    private ImageView iconRight;

//    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowLeft;
    private ImageView arrowRight;

    private int center = -1;
    private int up1 = -1;
    private int up2 = -1;
    private int down;
    private int left;
    private int right;

    private TextView textView;
    private int countTrue;
    private int countFalse;

    private final int[] ID = {R.drawable.ic_shape_bear, R.drawable.ic_shape_butterfly,
            R.drawable.ic_shape_camel, R.drawable.ic_shape_capricorn, R.drawable.ic_shape_cat,
            R.drawable.ic_shape_cobra, R.drawable.ic_shape_crab, R.drawable.ic_shape_deer,
            R.drawable.ic_shape_dolphin, R.drawable.ic_shape_dove, R.drawable.ic_shape_duck,
            R.drawable.ic_shape_eagle, R.drawable.ic_shape_elephant, R.drawable.ic_shape_feather,
            R.drawable.ic_shape_fish2, R.drawable.ic_shape_fish3, R.drawable.ic_shape_flying_ant,
            R.drawable.ic_shape_fox_sitting, R.drawable.ic_shape_fungus_beetle, R.drawable.ic_shape_gecko1,
            R.drawable.ic_shape_gecko_reptile, R.drawable.ic_shape_gull_bird, R.drawable.ic_shape_hippo,
            R.drawable.ic_shape_hummingbird, R.drawable.ic_shape_kangaroo, R.drawable.ic_shape_manatee,
            R.drawable.ic_shape_manatee, R.drawable.ic_shape_manta, R.drawable.ic_shape_monkey,
            R.drawable.ic_shape_moose, R.drawable.ic_shape_otter, R.drawable.ic_shape_owl,
            R.drawable.ic_shape_paw, R.drawable.ic_shape_penguin, R.drawable.ic_shape_rabbit,
            R.drawable.ic_shape_raccoon, R.drawable.ic_shape_raven, R.drawable.ic_shape_rhino,
            R.drawable.ic_shape_sea_lion, R.drawable.ic_shape_shark, R.drawable.ic_shape_snail,
            R.drawable.ic_shape_snake, R.drawable.ic_shape_tapir, R.drawable.ic_shape_toucan,
            R.drawable.ic_shape_two_prints, R.drawable.ic_shape_wild_board, R.drawable.ic_shpe_seahorse};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_select_icon);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#aaaaaa'>" + getText(R.string.select_icon_title_activity) +"</font>"));
        textView = findViewById(R.id.textVariant);
        textView.setText(ID.length + "");
        iconCenter = findViewById(R.id.imageViewCenterIcon);

//        iconUp = findViewById(R.id.imageViewUpIcon);
        iconUp1 = findViewById(R.id.imageViewUpIcon);
        iconUp2 = findViewById(R.id.imageViewArrowUp);

        iconDown = findViewById(R.id.imageViewDownIcon);
        iconLeft = findViewById(R.id.imageViewLeftIcon);
        iconRight = findViewById(R.id.imageViewRightIcon);

//        arrowUp = findViewById(R.id.imageViewArrowUp);
        arrowDown = findViewById(R.id.imageViewArrowDown);
        arrowLeft = findViewById(R.id.imageViewArrowLeft);
        arrowRight = findViewById(R.id.imageViewArrowRight);

        setIcon();

//        iconUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compareIcon(1);
//            }
//        });
        iconDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(3);
            }
        });
        iconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(2);
            }
        });
        iconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(4);
            }
        });
//        arrowUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compareIcon(1);
//            }
//        });
        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(3);
            }
        });
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(4);
            }
        });
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(2);
            }
        });


    }

    private void setIcon(){
        int max = ID.length;
        Random r = new Random();
        if (up1 == -1){
            up1 = r.nextInt(max);
            up2 = r.nextInt(max);
            center = r.nextInt(max);
        }else {
            center = up2;
            up2 = up1;
            up1 = r.nextInt(max);
        }
        switch (r.nextInt(3)){
            case 0:
                right = center;
                down = r.nextInt(max);
                left = r.nextInt(max);
                break;
            case 1:
                down = center;
                right = r.nextInt(max);
                left = r.nextInt(max);
                break;
            case 2:
                left = center;
                down = r.nextInt(max);
                right = r.nextInt(max);
                break;
        }
//        iconUp1.setImageResource(ID[up1]);
//        iconUp2.setImageResource(ID[up2]);
        iconCenter.setImageResource(ID[center]);

//        iconDown.setImageResource(ID[down]);
        iconLeft.setImageResource(ID[left]);
        iconRight.setImageResource(ID[right]);
    }

//    private void setIcon(){
//        int max = ID.length;
//        Random r = new Random();
//
//
//
//        while (true){
//            up = r.nextInt(max);
//            down = r.nextInt(max);
//            left = r.nextInt(max);
//            right = r.nextInt(max);
////            if (up != down & left !=right & up != right & up != left )break;
//            if (up != down & up != right & up != left & down != right & down != left & left != right )break;
//        }
////        iconUp.setImageResource(ID[up]);
//        iconDown.setImageResource(ID[down]);
//        iconLeft.setImageResource(ID[left]);
//        iconRight.setImageResource(ID[right]);
//        switch (r.nextInt(4)){
//            case 0:
//                iconCenter.setImageResource(ID[up]);
//                center = up;
//                break;
//            case 1:
//                iconCenter.setImageResource(ID[down]);
//                center = down;
//                break;
//            case 2:
//                iconCenter.setImageResource(ID[left]);
//                center = left;
//                break;
//            case 3:
//                iconCenter.setImageResource(ID[right]);
//                center = right;
//                break;
//        }
//    }
    private void compareIcon(int n){
//        textView.setText(n + "");
        switch (n){
//            case 1:
//                if (center == up){countTrue++;
//                }else {countFalse++;}
//                break;
            case 2:
                if (center == right){countTrue++;
                }else {countFalse++;}
                break;
            case 3:
                if (center == down){countTrue++;
                }else {countFalse++;}
                break;
            case 4:
                if (center == left){countTrue++;
                }else {countFalse++;}
                break;
            default:textView.setText("error");
        }
        setIcon();
        textView.setText(countTrue + ":" + countFalse);
    }
}
