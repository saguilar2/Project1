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

    public int getCurrentLevel() {
        return CurrentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        CurrentLevel = currentLevel;
    }

    public int getCurrentScore() {
        return CurrentScore;
    }

    public void setCurrentScore(int currentScore) {
        CurrentScore = currentScore;
    }

    public int getHighScore() {
        return HighScore;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    public int getLevelScore() {
        return LevelScore;
    }

    public void setLevelScore(int levelScore) {
        LevelScore = levelScore;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
