package SharedCode;

public class Player {

    private int CurrentLevel;
    private int CurrentScore;
    private int HighScore;
    private int LevelScore;
    private int lives;

    Player(){
     CurrentLevel = 0;
     CurrentScore = 0;
     HighScore = 0;
     LevelScore = 0;
     lives = 0;
    }

    Player(int CurrentLevel,int CurrentScore, int HighScore, int LevelScore, int lives){
        this.CurrentLevel = CurrentLevel;
        this.CurrentScore = CurrentScore;
        this.HighScore = HighScore;
        this.LevelScore = LevelScore;
        this.lives = lives;
    }

}
