package com.example.project1.SharedCode;

import java.util.Random;
public class IntruderLogic {

    private final static int IntruderValue = 10;

    //Time in milliseconts
    private final static int StartTime = 1000;
    private final static int MinIntruderAppearanceRate = 10;
    private final static int TimeDecresRate = 10;
    private final static int MinIntruderOnScreen = 100;

    private final static int  NumberOfRoundsDefult = 10;

    private final static int livesDefult = 3;
    private int CurrentLevel;
    private int CurrentRound;
    private int IntruderOnScreen;

    private int IntruderAppearanceRate;
    private final int SizeOfGrid;
    private int CurrentScore;
    private int LevelScore;
    private int NumberOfRounds;
    private int lives;
    
    public IntruderLogic(int SizeOfGrid){
        this.CurrentLevel = 1;
        this.IntruderOnScreen = StartTime;
        this.CurrentScore = 0;
        this.LevelScore = 0;
        this.IntruderAppearanceRate = StartTime;
        this.lives = livesDefult;
        this.SizeOfGrid = SizeOfGrid;
        this.NumberOfRounds= NumberOfRoundsDefult;
        this.CurrentRound = NumberOfRoundsDefult;
    }

    public IntruderLogic(int CurrentLevel,int SizeOfGrid,int lives){

        this.CurrentLevel = CurrentLevel;
        this.IntruderOnScreen = StartTime;
        this.CurrentScore = 0;
        this.LevelScore = 0;
        this.IntruderAppearanceRate = StartTime;
        this.lives = lives;
        this.SizeOfGrid = SizeOfGrid;
        this.CurrentRound = NumberOfRoundsDefult;
        this.NumberOfRounds= NumberOfRoundsDefult;
    }
    public void restart() {
        this.CurrentLevel = 1;
        this.IntruderOnScreen = StartTime;
        this.CurrentScore = 0;
        this.LevelScore = 0;
        this.IntruderAppearanceRate = StartTime;
        this.lives = livesDefult;
        this.CurrentRound = NumberOfRoundsDefult;
        this.NumberOfRounds= NumberOfRoundsDefult;
    }
    public int NewLocation() {
        Random rand = new Random();
        return rand.nextInt(SizeOfGrid);
    }
    public int[] GameStart(){
        CurrentRound--;

        return new int[]{IntruderAppearanceRate, IntruderOnScreen,NewLocation(),CurrentScore,lives,CurrentRound,0};

    }
    public int[]  RoundEnd(boolean WasTheIntruderStop) {
        int NewLevel = 0;

        CurrentRound--;

        if(WasTheIntruderStop){
            CurrentScore +=  IntruderValue;
            LevelScore +=  IntruderValue;
        }else{
            lives -=1;
        }


        if(CurrentRound == 0){
            LevelEnd();
            NewLevel = 1;
        }

        return new int[]{IntruderAppearanceRate, IntruderOnScreen,NewLocation(),CurrentScore,lives,CurrentRound,NewLevel};
    }
    private void LevelEnd() {
       IncreasingLeve();
        setIntruderOnScreen();
        setIntruderAppearanceRate();
        setNumberOfRounds();
        AddBonus();
    }
    private void AddBonus() {
        CurrentScore += LevelScore*CurrentLevel;
        LevelScore = 0;
    }
    private void setNumberOfRounds() {

        if(CurrentLevel%10 == 0){

            NumberOfRounds = NumberOfRoundsDefult + (CurrentLevel/10)*NumberOfRoundsDefult;
        }
            CurrentRound = NumberOfRounds;
    }

    private void IncreasingLeve() {
        CurrentLevel++;
    }
    public void setCurrentLevel(int currentLevel) {
        CurrentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return CurrentLevel;
    }

    public void setIntruderOnScreen() {

        if(IntruderOnScreen <= StartTime && IntruderOnScreen > MinIntruderOnScreen){
            IntruderOnScreen = StartTime - (CurrentLevel*TimeDecresRate);
        }

    }


    public void setIntruderAppearanceRate() {
        if(IntruderAppearanceRate <= StartTime && IntruderAppearanceRate > MinIntruderAppearanceRate){
            IntruderAppearanceRate = StartTime - (CurrentLevel*TimeDecresRate);
        }
    }
}
