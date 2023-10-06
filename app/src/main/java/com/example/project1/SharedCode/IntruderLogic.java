package com.example.project1.SharedCode;

import java.util.Random;

public class IntruderLogic {
// default values
    private final static int IntruderValue = 10;

    //Time in milliseconts
    private final static int StartTime = 1000;
    private final static int MinIntruderAppearanceRate = 10;
    private final static int TimeDecresRate = 10;
    private final static int MinIntruderOnScreen = 100;

    private final static int NumberOfRoundsDefult = 10;

    private final static int livesDefult = 3;

    // values to keep track of
    private int CurrentLevel;
    private int CurrentRound;
    private int IntruderOnScreen;

    private int IntruderAppearanceRate;
    private final int SizeOfGrid;
    private int CurrentScore;
    private int LevelScore;
    private int NumberOfRounds;
    private int lives;

    // default constructor
    public IntruderLogic(int SizeOfGrid) {
        this.CurrentLevel = 1;
        this.IntruderOnScreen = StartTime;
        this.CurrentScore = 0;
        this.LevelScore = 0;
        this.IntruderAppearanceRate = StartTime;
        this.lives = livesDefult;
        this.SizeOfGrid = SizeOfGrid;
        this.NumberOfRounds = NumberOfRoundsDefult;
        this.CurrentRound = NumberOfRoundsDefult;
    }
// constructor for expansion
    public IntruderLogic(int CurrentLevel, int SizeOfGrid, int lives) {

        this.CurrentLevel = CurrentLevel;
        this.IntruderOnScreen = StartTime;
        this.CurrentScore = 0;
        this.LevelScore = 0;
        this.IntruderAppearanceRate = StartTime;
        this.lives = lives;
        this.SizeOfGrid = SizeOfGrid;
        this.CurrentRound = NumberOfRoundsDefult;
        this.NumberOfRounds = NumberOfRoundsDefult;
    }
// Re sets the game
    public void restart() {
        this.CurrentLevel = 1;
        this.IntruderOnScreen = StartTime;
        this.CurrentScore = 0;
        this.LevelScore = 0;
        this.IntruderAppearanceRate = StartTime;
        this.lives = livesDefult;
        this.CurrentRound = NumberOfRoundsDefult;
        this.NumberOfRounds = NumberOfRoundsDefult;
    }
// determent the next location for the intruder to appear
    public int NewLocation() {
        Random rand = new Random();
        return rand.nextInt(SizeOfGrid);
    }

    // used to start a game
    public int[] GameStart() {
        CurrentRound--;

        return new int[]{IntruderAppearanceRate, IntruderOnScreen, NewLocation(), CurrentScore, lives, CurrentRound, 0};

    }

    // used tp send new information for next round to be played
    public int[] RoundEnd(boolean WasTheIntruderStop) {
        int NewLevel = 0;

        CurrentRound--;

        if (WasTheIntruderStop) {
            CurrentScore += IntruderValue;
            LevelScore += IntruderValue;
        } else {
            lives -= 1;
        }


        if (CurrentRound == 0) {
            LevelEnd();
            NewLevel = 1;
        }

        return new int[]{IntruderAppearanceRate, IntruderOnScreen, NewLocation(), CurrentScore, lives, CurrentRound, NewLevel};
    }
    // calls all need actions at the end of a level
    private void LevelEnd() {
        IncreasingLeve();
        setIntruderOnScreen();
        setIntruderAppearanceRate();
        setNumberOfRounds();
        AddBonus();
    }
// calculates the bones for a level
    private void AddBonus() {
        CurrentScore += LevelScore * CurrentLevel;
        LevelScore = 0;
    }
// figures the new number of rounds per level
    private void setNumberOfRounds() {

        if (CurrentLevel % 10 == 0) {

            NumberOfRounds = NumberOfRoundsDefult + (CurrentLevel / 10) * NumberOfRoundsDefult;
        }
        CurrentRound = NumberOfRounds;
    }
// increases the level
    private void IncreasingLeve() {
        CurrentLevel++;
    }

// calculates the new time the intruder would be on screen
    public void setIntruderOnScreen() {

        if (IntruderOnScreen <= StartTime && IntruderOnScreen > MinIntruderOnScreen) {
            IntruderOnScreen = StartTime - (CurrentLevel * TimeDecresRate);
        }

    }

    // calculates the new time before the intruder appears
    public void setIntruderAppearanceRate() {
        if (IntruderAppearanceRate <= StartTime && IntruderAppearanceRate > MinIntruderAppearanceRate) {
            IntruderAppearanceRate = StartTime - (CurrentLevel * TimeDecresRate);
        }
    }
}
