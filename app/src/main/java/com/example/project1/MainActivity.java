package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project1.SharedCode.IntruderLogic;
import com.example.project1.SharedCode.Timer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int intruderAppearanceRateInd = 0;
    private final int intruderOnScreenInd = 1;
    private final int locationIndex = 2;
    private final int currScoreInd = 3;
    private final int livesInd = 4;
    private final int currRoundInd = 5;
    private final int newLvlInd = 6;


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

    private TextView scoreTVDisplay;
    private TextView highScoreTVDisplay;
    private final String TAG = "DEBUG";
    private ScoreModel scoreModel;
    private int[] roundEndInfo;
    private Timer burglarOnScreen = new Timer();
    private Timer appearanceRate = new Timer();
    private boolean gameStarted;
    private boolean startNewGame;
    private boolean startNewGame2;
    private boolean boolKillBurglar;

    private final IntruderLogic intruder = new IntruderLogic(9);

    public ArrayList<ImageButton> imgBtnList = new ArrayList<>(10);
    public int locationOfOldOldBurglar = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startNewGame = true;
        startNewGame2 = true;
        gameStarted = false;
        scoreModel = new ViewModelProvider(this).get(ScoreModel.class);

        scoreTVDisplay = findViewById(R.id.scoreTV);
        highScoreTVDisplay = findViewById(R.id.highScoreTV);

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
        final Observer<Integer> ScoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {
                scoreTVDisplay.setText(newScore.toString());

            }
        };
        final Observer<Integer> HighScoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {
                scoreTVDisplay.setText(newScore.toString());

            }
        };
        scoreModel.GetScore().observe(this, ScoreObserver);
        scoreModel.GetHighScore().observe(this, HighScoreObserver);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStarted = true;
                Log.d("DEBUG", "StartBtn Clicked");
                runApp(gameStarted);
            }
        });

        /*
        Create a new handler and init a delay variable
         */
        final Handler handler = new Handler();
        final int delay = 10;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (gameStarted && roundEndInfo != null) {
//                    scoreModel.SetScore(Integer.valueOf(roundEndInfo[currScoreInd]));
                    scoreModel.GetScore();
                    if (roundEndInfo[newLvlInd] == 1) {
                        scoreModel.GetHighScore();
                    }
                }


                if (gameStarted) {

                    if (startNewGame) {
                        appearanceRate.Start(roundEndInfo[intruderAppearanceRateInd]);
                        startNewGame = false;
                    }
                    if (appearanceRate.TimerEnded()) {
                        Log.d(TAG, "time remaning " + appearanceRate.TimerEnded());
                        if (startNewGame2) {
                            if (boolKillBurglar) {
                                imgBtnList.get(locationOfOldOldBurglar).setActivated(false);
                                Log.d(TAG, "Killed the burglar" + boolKillBurglar + locationOfOldOldBurglar);
                                locationOfOldOldBurglar = -1;
                                boolKillBurglar = false;
                            }
                            dispBurglar();
                            burglarOnScreen.Start(roundEndInfo[intruderOnScreenInd]);
                            startNewGame2 = false;
                        }

                        appearanceRate.resetTimer();
                    }

                }
                handler.postDelayed(this, delay);
            }
        }, delay);


    }

    public void runApp(boolean gameStarted) {

        if (gameStarted) {
            roundEndInfo = intruder.GameStart();
        } else {
            Log.d(TAG, "Something bad happened, App wouldn't run");
        }
    }

    public boolean toggleGameStarted(boolean gameStarted) {
        gameStarted = !gameStarted;
        return gameStarted;
    }


    public void dispBurglar() {

        imgBtnList.get(roundEndInfo[locationIndex]).setActivated(true);
        imgBtnList.get(roundEndInfo[locationIndex]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationOfOldOldBurglar = roundEndInfo[locationIndex];
                burglarOnScreen.stop();
                roundEndInfo = intruder.RoundEnd(true);
                boolKillBurglar = true;
                startNewGame = true;
                startNewGame2 = true;
                Log.d("DEBUG", "Correct Location Clicked: " + locationIndex);
            }
        });

    }



}