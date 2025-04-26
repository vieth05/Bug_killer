package btl_java.managers;

import java.io.*;

public class ScoreManager {
    private static final String FILE_NAME = "highscore.txt";

    public static void saveHighScore(int score) {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            writer.print(score);
        } catch (IOException ignored) {}
    }
    public static int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }
}
