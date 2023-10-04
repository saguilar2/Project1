package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.project1.SharedCode.IntruderLogic;

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

    private int score = 0;
    private boolean gameStarted = false;

    public ArrayList<ImageButton> imgBtnList = new ArrayList<>(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                if(gameStarted == true){
                    Log.d("DEBUG", "gameStarted = true");
                    runApp(gameStarted);
                }
                else{
                    Log.d("DEBUG", "gamestarted = false");

                }
            }
        });


    }

    public void runApp(boolean gameStarted){
        Log.d("DEBUG", "runApp called");
        IntruderLogic intruder = new IntruderLogic(9);
        Log.d("DEBUG", "Intruder Created");
        int location = intruder.NewLocation();
        while(gameStarted == true) {
            Log.d("DEBUG", "Game Started");
            imgBtnList.get(location).setClickable(true);
            intruder.setIntruderOnScreen();

            imgBtnList.get(location).setClickable(true);
            Log.d("DEBUG", "Window made clickable: " + imgBtnList.get(location).toString());

            Log.d("DEBUG", "Location: " + location);
            gameStarted = false;
        }
        while(gameStarted = false) {
            imgBtnList.get(location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intruder.RoundEnd(true);
                    Log.d("DEBUG", "Correct Location Clicked");
                }
            });

            gameStarted = true;
        }
    }

}