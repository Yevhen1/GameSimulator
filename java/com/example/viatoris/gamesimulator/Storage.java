package com.example.viatoris.gamesimulator;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.widget.ProgressBar;

public class Storage {
//    public static boolean beginFlag = false;

    public static boolean selectColorFlag;
    public static boolean digitFlag;
    public static boolean selectIconFlag;
    public static boolean rememberNumberFlag;

//    descriptionCount:logo-0, SelectColor-1, digit-2, selectIcon-3, rememberNumber-4
    public static int descriptionCount = 0;

    public static int trueAnswer = 0;
    public static int falseAnswer = 0;
//    Time in our trainer
    public static long time = 45000;
    public static CountDownTimer timer;

    public static int trueStorage = 0;
    public static int falseStorage = 0;

}