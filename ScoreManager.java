package btl_java;

import java.io.*;

public class ScoreManager {
    private static final String FILE_NAME = "highscore.txt";

    // Ghi điểm cao nhất vào file
    public static void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc điểm cao nhất từ file
    public static int loadHighScore() {
        int score = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            score = Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            // File chưa tồn tại hoặc lỗi đọc thì giữ mặc định là 0
        }
        return score;
    }
}