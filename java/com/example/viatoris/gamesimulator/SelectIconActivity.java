package com.example.viatoris.gamesimulator;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class SelectIconActivity extends AppCompatActivity  implements OnTouchListener{

    private ImageView iconCenter;

    private ImageView iconUp;
    private ImageView iconDown;
    private ImageView iconLeft;
    private ImageView iconRight;

    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowLeft;
    private ImageView arrowRight;

    private int center;
    private int up;
    private int down;
    private int left;
    private int right;

//    private TextView textView;
    private ProgressBar progressBar;
    private ImageView imageViewDescription;
    private ImageView imageViewMenu;

    private boolean flagPlay;
//    private int countTrue;
//    private int countFalse;

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
        setContentView(R.layout.activity_select_icon);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#aaaaaa'>" + getText(R.string.select_icon_title_activity) +"</font>"));
//        textView = findViewById(R.id.textVariant);
//        textView.setText(ID.length + "");
        progressBar = findViewById(R.id.progressBar3);
        progressBar.getProgressDrawable().setColorFilter(
                Color.parseColor("#632983"), android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setScaleY(3f);
        iconCenter = findViewById(R.id.imageViewCenterIcon);

        iconUp = findViewById(R.id.imageViewUpIcon);
        iconDown = findViewById(R.id.imageViewDownIcon);
        iconLeft = findViewById(R.id.imageViewLeftIcon);
        iconRight = findViewById(R.id.imageViewRightIcon);

        arrowUp = findViewById(R.id.imageViewArrowUp);
        arrowDown = findViewById(R.id.imageViewArrowDown);
        arrowLeft = findViewById(R.id.imageViewArrowLeft);
        arrowRight = findViewById(R.id.imageViewArrowRight);

        setIcon();
        timeGame(Storage.time);
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        Storage.descriptionCount = 3;

        iconUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(1);
            }
        });
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
        arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareIcon(1);
            }
        });
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

        imageViewMenu = findViewById(R.id.imageView5);
        imageViewDescription = findViewById(R.id.imageViewIcon);

        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagPlay = true;
                Storage.timer.cancel();
                Intent intent = new Intent(SelectIconActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        imageViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagPlay = true;
                Storage.timer.cancel();
                Intent intent = new Intent(SelectIconActivity.this, DescriptionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    private void setIcon(){
        int max = ID.length;
        Random r = new Random();
        while (true){
            up = r.nextInt(max);
            down = r.nextInt(max);
            left = r.nextInt(max);
            right = r.nextInt(max);
//            if (up != down & left !=right & up != right & up != left )break;
            if (up != down & up != right & up != left & down != right & down != left & left != right )break;
        }
        iconUp.setImageResource(ID[up]);
        iconDown.setImageResource(ID[down]);
        iconLeft.setImageResource(ID[left]);
        iconRight.setImageResource(ID[right]);
        switch (r.nextInt(4)){
            case 0:
                iconCenter.setImageResource(ID[up]);
                center = up;
                break;
            case 1:
                iconCenter.setImageResource(ID[down]);
                center = down;
                break;
            case 2:
                iconCenter.setImageResource(ID[left]);
                center = left;
                break;
            case 3:
                iconCenter.setImageResource(ID[right]);
                center = right;
                break;
        }
    }

    public void newGame(){
        flagPlay = true;
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        setIcon();
        Storage.timer.start();
    }

    public void backMenu(){
        Storage.trueAnswer = 0;
        Storage.falseAnswer = 0;
        Storage.timer.cancel();
        Intent intent = new Intent(SelectIconActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void compareIcon(int n){
//        textView.setText(n + "");
//        if (flagPlay) {
            switch (n) {
                case 1:
                    if (center == up) {
                        Storage.trueAnswer++;
                    } else {
                        Storage.falseAnswer++;
                    }
                    break;
                case 2:
                    if (center == right) {
                        Storage.trueAnswer++;
                    } else {
                        Storage.falseAnswer++;
                    }
                    break;
                case 3:
                    if (center == down) {
                        Storage.trueAnswer++;
                    } else {
                        Storage.falseAnswer++;
                    }
                    break;
                case 4:
                    if (center == left) {
                        Storage.trueAnswer++;
                    } else {
                        Storage.falseAnswer++;
                    }
                    break;
                default:
                    break;//textView.setText("error");
            }
            setIcon();
//        textView.setText(countTrue + ":" + countFalse);
//        }
    }
    private void timeGame(final long countTime){
//        progressBar = findViewById(R.id.progressBar);
        flagPlay = true;
        Storage.timer = new CountDownTimer(countTime, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress((int)l / (int) (Storage.time/100));
            }
            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                flagPlay = false;
//                FragmentManager manager = getSupportFragmentManager();
//                new DialogFinish().show(manager, "dialog");
                Intent intent = new Intent(SelectIconActivity.this, DialogFinish.class);
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
