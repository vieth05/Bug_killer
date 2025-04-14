package btl_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private static final int ANT_SIZE = 50;
    private static final int FRAME_RATE = 30;  // Thời gian delay cho timer của game

    private Timer timer;
    private ArrayList<Bug> bugs;
    private int score = 0;
    private int lives = 3;
    private int frameCount = 0;
    private Image backgroundImage;
    private Image freezeBackgroundImage;

    private int antSpeed = 2;
    private int bugsPerSpawn = 1;
    private boolean frozen = false;

    private Random rand = new Random();  // Khai báo biến rand cho việc sinh số ngẫu nhiên

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 800));
        this.addMouseListener(this);

        bugs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bugs.add(new Ant(rand.nextInt(770), 0, ANT_SIZE, antSpeed));
        }

        // Tải hình ảnh một lần
        backgroundImage = new ImageIcon("_.jpeg").getImage();
        freezeBackgroundImage = new ImageIcon("freeze_img.png").getImage();

        timer = new Timer(FRAME_RATE, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dùng trạng thái frozen để chọn hình nền
        g.drawImage(frozen ? freezeBackgroundImage : backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Vẽ tất cả các bug
        for (Bug bug : bugs) {
            bug.draw(g);
        }

        // Hiển thị điểm số, mạng sống và các thông tin khác
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Lives: " + lives, 20, 60);
        g.drawString("Speed: " + antSpeed, 20, 90);
        g.drawString("Spawn Rate: " + bugsPerSpawn + " bugs/sec", 20, 120);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frameCount++;

        // Sinh bug mới theo chu kỳ frame count
        if (frameCount % 30 == 0) {
            for (int i = 0; i < bugsPerSpawn; i++) {
                int chance = rand.nextInt(100);
                if (chance < 10) {
                    bugs.add(new Beetle(rand.nextInt(770), 0, ANT_SIZE, antSpeed / 2));
                } else if (chance < 15) {
                    bugs.add(new IceBeetle(rand.nextInt(770), 0, ANT_SIZE, antSpeed / 2));
                } else {
                    bugs.add(new Ant(rand.nextInt(770), 0, ANT_SIZE, antSpeed));
                }
            }
        }

        // Di chuyển các bug và kiểm tra điều kiện kết thúc game
        Iterator<Bug> iterator = bugs.iterator();
        while (iterator.hasNext()) {
            Bug bug = iterator.next();
            if (!frozen) bug.move();

            // Xóa bug nếu ra ngoài màn hình
            if (bug.getY() > 800) {
                iterator.remove();
                lives--;
                if (lives == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
                    System.exit(0);
                }
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Xử lý khi click vào bug
        Iterator<Bug> iterator = bugs.iterator();
        while (iterator.hasNext()) {
            Bug bug = iterator.next();
            if (bug.isClicked(e.getX(), e.getY()) && !bug.alive) {
                iterator.remove();
                score += bug.getScoreValue();

                if (bug instanceof IceBeetle) {
                    applyFreezeEffect(); // Áp dụng hiệu ứng đóng băng cho IceBeetle
                } else if (bug instanceof Beetle) {
                    lives++; // Tăng mạng sống cho Beetle
                }

                // Tăng tốc độ và tần suất sinh bug khi đạt điểm số nhất định
                if (score % 20 == 0) antSpeed++;
                if (score % 40 == 0) bugsPerSpawn++;
            }
        }

        repaint();
    }

    private void applyFreezeEffect() {
        if (!frozen) {
            frozen = true;
            new Timer(3000, e -> frozen = false).start();  // Đóng băng trong 3 giây
        }
    }

    // Các phương thức mouse listener (không sử dụng, có thể xóa nếu không cần thiết)
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
