package btl_java;

import javax.swing.*;
import java.awt.CardLayout;

public class BugKiller {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bug Killer 🐜");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        GamePanel gamePanel = new GamePanel();
        MenuPanel menuPanel = new MenuPanel(gamePanel, cardLayout, mainPanel);

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel, "game");

        frame.add(mainPanel);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "menu");
    }
}


