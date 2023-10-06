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
import android.widget.ImageView;
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
    private Button stpBtn;
    private ImageView life1;
    private ImageView life2;
    private ImageView life3;

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

    private int PreviousLocation;
    private final IntruderLogic intruder = new IntruderLogic(9);

    private ArrayList<ImageButton> imgBtnList = new ArrayList<>(10);
    private ArrayList<ImageView> imgViewList = new ArrayList<>(3);

    /**
     * onCreate
     * Contains all findViewById for all elements
     * A live observer instance for the Score
     * On click methods for Start and Stop Buttons
     * Handler that contains a runnable for the game
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Init flags for game
         */
        PreviousLocation = -1;
        startNewGame = true;
        startNewGame2 = true;
        gameStarted = false;
        boolKillBurglar = false;
        scoreModel = new ViewModelProvider(this).get(ScoreModel.class);

        /*
            Find views for all Text elements and Buttons
         */
        scoreTVDisplay = findViewById(R.id.scoreTV);
        highScoreTVDisplay = findViewById(R.id.highScoreTV);
        startBtn = findViewById(R.id.startbtn);
        stpBtn = findViewById(R.id.stpBtn);

        /*
          Find views of all windows/burglars
         */
        burg1 = findViewById(R.id.window1);
        burg2 = findViewById(R.id.window2);
        burg3 = findViewById(R.id.window3);
        burg4 = findViewById(R.id.window4);
        burg5 = findViewById(R.id.window5);
        burg6 = findViewById(R.id.window6);
        burg7 = findViewById(R.id.window7);
        burg8 = findViewById(R.id.window8);
        burg9 = findViewById(R.id.window9);

        /*
            Add Window/Burglar Image Buttons to ArrayList
         */
        imgBtnList.add(burg1);
        imgBtnList.add(burg2);
        imgBtnList.add(burg3);
        imgBtnList.add(burg4);
        imgBtnList.add(burg5);
        imgBtnList.add(burg6);
        imgBtnList.add(burg7);
        imgBtnList.add(burg8);
        imgBtnList.add(burg9);

        /*
            Find heart Image Views
         */
        life1 = findViewById(R.id.heart1);
        life2 = findViewById(R.id.heart2);
        life3 = findViewById(R.id.heart3);

        /*
            Add life ImageViews to ArrayList
         */
        imgViewList.add(life1);
        imgViewList.add(life2);
        imgViewList.add(life3);

        /*
            Create observer Instance for Score
         */
        final Observer<Integer> ScoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {
                scoreTVDisplay.setText("Score: " + newScore.toString());

            }
        };
        /*
            Create observer Instance for HighScore
         */
        final Observer<Integer> HighScoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {

                highScoreTVDisplay.setText("High Score: " + newScore.toString());

            }
        };
        scoreModel.GetScore().observe(this, ScoreObserver);
        scoreModel.GetHighScore().observe(this, HighScoreObserver);

        /*
         * Start Button onClickListener, when clicked:
         * Set gameStarted flag to true
         * and call runApp()
         *
         */
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStarted = true;
                runApp();
            }
        });

        /*
         *Stop Button onClickListener, when clicked:
         * Set gameStarted flag to false
         */
        stpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStarted = false;
                startNewGame = true;
                startNewGame2 = true;
                for(int i = 0; i < imgBtnList.size(); i++){
                    imgBtnList.get(i).setActivated(false);
                }
                scoreModel.SetScore(0);
            }
        });

        /*
         * Create a new handler and init a delay variable
         */
        final Handler handler = new Handler();
        final int delay = 10;

        /*
            Runnable used to check game states after the first round
            When the game is started, check timers, check mole locations,
            activate or deactivate selectors
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Only entered when the round is > 1;
                if (gameStarted && roundEndInfo != null) {

                    //If burglar clicked, change window/burglar and reset flag

                    if (boolKillBurglar) {
                        imgBtnList.get(PreviousLocation).setActivated(false);
                        Log.d(TAG, "Killed the burglar " + boolKillBurglar + " " + PreviousLocation);
                        boolKillBurglar = false;
                    }

                    // If start of new round, and burglar has not been displayed yet:
                    if (startNewGame && startNewGame2) {
                        //check lives and set imageViews
                        for(int i = 0; i <imgViewList.size(); i++){
                            if(i+1 <= roundEndInfo[livesInd]){
                                imgViewList.get(i).setActivated(true);
                            } else {
                                imgViewList.get(i).setActivated(false);
                            }
                        }
                        // If the player had died, reset and stop the game
                        if (roundEndInfo[livesInd] <= 0) {
                            scoreModel.SetScore(Integer.valueOf(0));
                            gameStarted = false;
                            intruder.restart();
                        }
                        // Check the score and set the textView for score
                        scoreModel.SetScore(Integer.valueOf(roundEndInfo[currScoreInd]));
                        scoreModel.GetScore();
                        scoreModel.GetHighScore();
                    }
                    // If start of new round, set the amount of time in between the new mole
                    if (startNewGame) {
                        // Start the wait timer before populating window/burglar
                        appearanceRate.Start(roundEndInfo[intruderAppearanceRateInd]);
                        // reset flag
                        startNewGame = false;
                    }
                    // Check if timer has ended and it is time to switch the window/burglar
                    if (appearanceRate.TimerEnded()) {
                        Log.d(TAG, "time remaning " + appearanceRate.TimerEnded());
                        // Start timer for how long burglar should be on screen
                        if (startNewGame2) {
                            dispBurglar();  // Display the Burglar
                            burglarOnScreen.Start(roundEndInfo[intruderOnScreenInd]);   // Start the round
                            startNewGame2 = false;  // Reset flag

                        }

                        // If you missed the burglar, then send info back to engine
                        if (burglarOnScreen.TimerEnded()) {
                            roundEndInfo = intruder.RoundEnd(false);    //missed
                            startNewGame = true; // reset flag
                            startNewGame2 = true; // reset flag
                            boolKillBurglar = true; // reset flag
                        }
                    }

                }
                handler.postDelayed(this, delay);
            }
        }, delay);


    }

    /**
     * runApp()
     * Called on the first round of every game
     */
    public void runApp() {

        if (gameStarted) {
            roundEndInfo = intruder.GameStart();
        } else {
            Log.d(TAG, "Something bad happened, App wouldn't run");
        }
    }

    /**
     * dispBurglar()
     * Called when the game engine instructs the UI to populate
     * a burglar in the grid. When you add a burglar,
     * set and onClickListener for the burglar
     */
    public void dispBurglar() {
        PreviousLocation = roundEndInfo[locationIndex];
        imgBtnList.get(roundEndInfo[locationIndex]).setActivated(true);
        imgBtnList.get(roundEndInfo[locationIndex]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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