package com.example.project1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreModel extends ViewModel {
    // stores the players current score
    private MutableLiveData<Integer> Score;

    // stores the players current high score
    private MutableLiveData<Integer> HighScore;

   // Initialises all livedata
    private void InitialiseScoreModel() {
        if (Score == null) {
            Score = new MutableLiveData<Integer>();
            Score.setValue(0);
        }
        if (HighScore == null) {
            HighScore = new MutableLiveData<Integer>();
            HighScore.setValue(0);
        }
    }

    // returns the current Score
    public MutableLiveData<Integer> GetScore() {
        InitialiseScoreModel();
        return Score;
    }

    // returns the current High Score
    public MutableLiveData<Integer> GetHighScore() {

        InitialiseScoreModel();
        return HighScore;
    }

    // Set Score and check if current score is a high score and set it
    public void SetScore(Integer NewScore) {
        InitialiseScoreModel();
        Score.setValue(NewScore);
        if (NewScore > HighScore.getValue()) {
            HighScore.setValue(NewScore);
        }
    }

}
