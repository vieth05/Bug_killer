package btl_java.managers;

public class GameStats {
    public int score = 0;
    public int lives = 3;
    public int highScore;
    public int oldScore;
    public int antSpeed = 2;
    public int bugsPerSpawn = 1;

    public GameStats() {
        highScore = ScoreManager.loadHighScore();
        oldScore = highScore;
    }

    public void increaseScore(int value) {
        score += value;
        if (score > highScore) {
            highScore = score;
            ScoreManager.saveHighScore(highScore);
        }

        if (score % 20 == 0) antSpeed++;
        if (score % 40 == 0) bugsPerSpawn++;
    }
}
