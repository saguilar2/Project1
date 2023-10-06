package com.example.project1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreModel extends ViewModel {
    private MutableLiveData<Integer> Score;
    private MutableLiveData<Integer> HighScore;

    public void InitialiseScoreModel() {
        if (Score == null) {
            Score = new MutableLiveData<Integer>();
        }
        if (HighScore == null) {
            HighScore = new MutableLiveData<Integer>();
            HighScore.setValue(0);
        }
    }

    public MutableLiveData<Integer> GetScore() {
        InitialiseScoreModel();
        return Score;
    }

    public MutableLiveData<Integer> GetHighScore() {
        InitialiseScoreModel();
        return HighScore;
    }

    public void SetScore(Integer NewScore) {
        InitialiseScoreModel();
        Score.setValue(NewScore);
        if (NewScore > HighScore.getValue()) {
            HighScore.setValue(NewScore);
        }
    }

}
