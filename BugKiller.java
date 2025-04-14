package btl_java;

import javax.swing.*;

public class BugKiller {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bug Killer 🐜");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
