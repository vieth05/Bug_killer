package btl_java;

import btl_java.managers.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MenuPanel extends JPanel {
    private JButton startButton;
    private Image backgroundImage;

    public MenuPanel(GamePanel gamePanel, CardLayout cardLayout, JPanel containerPanel) {
        // GridBagLayout căn giữa các thành phần
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        backgroundImage = new ImageIcon("img/bg.jpeg").getImage();

        // Tiêu đề
        JLabel titleLabel = new JLabel("Bug Killer 🐜", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(titleLabel, gbc);

        // High score
        JLabel highScoreLabel = new JLabel("High Score: " + ScoreManager.loadHighScore(), SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(highScoreLabel, gbc);

        // Start Game
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(e -> {
            if (gamePanel != null) {
                gamePanel.startGame();
                cardLayout.show(containerPanel, "game"); // chuyển qua game panel
            }
        });

        // căn giữa Start Game
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(startButton, gbc);

        // hover
        startButton.addMouseListener(new MouseAdapter() {
            @Override // cập nhật kích thước
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(new Color(0, 255, 0));
                startButton.setForeground(Color.BLACK);

                startButton.setFont(new Font("Arial", Font.BOLD, 30));
                startButton.setPreferredSize(new Dimension(250, 60));
                startButton.revalidate();
            }

            @Override // cập nhật trạng thái ban đầu
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(new Color(255, 255, 255));
                startButton.setForeground(Color.BLACK);

                startButton.setFont(new Font("Arial", Font.BOLD, 25));
                startButton.setPreferredSize(new Dimension(200, 50));
                startButton.revalidate();
            }
        });

        // hiệu ứng click
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setBackground(new Color(0, 200, 0));
                new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startButton.setBackground(new Color(255, 255, 255));
                    }
                }).start();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // làm mờ
        Graphics2D g2d = (Graphics2D) g;
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f); // 50% mờ
        g2d.setComposite(alpha);

        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
