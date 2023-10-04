package com.example.project1;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.project1.SharedCode.IntruderLogic;
import com.example.project1.SharedCode.Timer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton burg1;
    private ImageButton burg2;
    private ImageButton burg3;
    private ImageButton burg4;
    private ImageButton burg5;
    private ImageButton burg6;
    private ImageButton burg7;
    private ImageButton burg8;
    private ImageButton burg9;
    private Button startBtn;
    private final String TAG = "DEBUG";

    private int[] roundEndInfo;
    private Timer burglarOnScreen = new Timer();
    private Timer appearanceRate = new Timer();
    private int score = 0;
    private boolean gameStarted;
    private int location;

    private final IntruderLogic intruder = new IntruderLogic(9);

    public ArrayList<ImageButton> imgBtnList = new ArrayList<>(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameStarted = false;

        burg1 = findViewById(R.id.window1);
        burg2 = findViewById(R.id.window2);
        burg3 = findViewById(R.id.window3);
        burg4 = findViewById(R.id.window4);
        burg5 = findViewById(R.id.window5);
        burg6 = findViewById(R.id.window6);
        burg7 = findViewById(R.id.window7);
        burg8 = findViewById(R.id.window8);
        burg9 = findViewById(R.id.window9);
        startBtn = findViewById(R.id.startbtn);

        imgBtnList.add(burg1);
        imgBtnList.add(burg2);
        imgBtnList.add(burg3);
        imgBtnList.add(burg4);
        imgBtnList.add(burg5);
        imgBtnList.add(burg6);
        imgBtnList.add(burg7);
        imgBtnList.add(burg8);
        imgBtnList.add(burg9);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStarted = true;
                Log.d("DEBUG", "StartBtn Clicked");
                runApp(gameStarted);
            }
        });


    }

    public void runApp(boolean gameStarted){

        if (gameStarted){
            location = intruder.NewLocation();

            imgBtnList.get(location).setActivated(true);

            b
            /*


             */

            imgBtnList.get(location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgBtnList.get(location).setActivated(false);
//                    imgBtnList.get(location).setSelected(true);   //Close the window for alittle bit
                    roundEndInfo = intruder.RoundEnd(true);
                    Log.d("DEBUG", "Correct Location Clicked");
                }
            });

        }
        else {
            Log.d(TAG,"Something bad happened, App wouldn't run");
        }
    }

    public boolean toggleGameStarted(boolean gameStarted){
        gameStarted = !gameStarted;
        return gameStarted;
    }



}