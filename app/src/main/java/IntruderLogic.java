public class IntruderLogic {

    private final static int IntruderValue = 10;
    private int CurrentLevel;

    private int NumberOfRounds;
    private double IntruderOnScreen;

    private double IntruderAppearanceRate;

    private double CurrentScore;

    private int lives;
    IntruderLogic(){
        this.CurrentLevel = 1;
        this.IntruderOnScreen = 1000;
        this.CurrentScore = 0;
        this.IntruderAppearanceRate = 1000;
        this.lives = 3;
        this.NumberOfRounds = 10;
    }

    IntruderLogic(int CurrentLevel){

        this.CurrentLevel = CurrentLevel;
        this.IntruderOnScreen = 1000;
        this.CurrentScore = 0;
        this.IntruderAppearanceRate = 1000;
        this.lives = 3;
        this.NumberOfRounds = 10;
    }
    public void RoundEnd(boolean WasTheIntruderStop) {

        if(WasTheIntruderStop){
            CurrentScore +=  IntruderValue;
        }else{
            lives -=1;
        }

    }
    public void LevelEnd(boolean WasTheIntruderStop) {

        if(WasTheIntruderStop){
            CurrentScore +=  IntruderValue;
        }else{
            lives -=1;
        }

    }

    public int getCurrentLevel() {
        return CurrentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        CurrentLevel = currentLevel;
    }

    public double getIntruderOnScreen() {
        return IntruderOnScreen;
    }

    public void setIntruderOnScreen(double intruderOnScreen) {
        IntruderOnScreen = intruderOnScreen;
    }

    public double getIntruderAppearanceRate() {
        return IntruderAppearanceRate;
    }

    public void setIntruderAppearanceRate(double intruderAppearanceRate) {
        IntruderAppearanceRate = intruderAppearanceRate;
    }

    public double getCurrentScore() {
        return CurrentScore;
    }

    public void setCurrentScore(double currentScore) {
        CurrentScore = currentScore;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
