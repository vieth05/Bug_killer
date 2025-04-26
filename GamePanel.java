package btl_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

import btl_java.entities.*;
import btl_java.managers.*;

public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private static final int FRAME_RATE = 30;

    private boolean gameStarted = false;
    private Timer timer;
    private Random rand = new Random();
    private int frameCount = 0;

    private Image backgroundImage;
    private Image freezeBackgroundImage;

    private BugManager bugManager;
    private BloodEffectManager bloodManager;
    private GameStats stats;
    private FreezeManager freezeManager;

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 800));
        this.addMouseListener(this);

        backgroundImage = new ImageIcon("img/bg.jpeg").getImage();
        freezeBackgroundImage = new ImageIcon("img/freeze.jpg").getImage();

        stats = new GameStats();
        freezeManager = new FreezeManager();
        bugManager = new BugManager(stats.antSpeed, stats.bugsPerSpawn, rand);
        bloodManager = new BloodEffectManager();

        timer = new Timer(FRAME_RATE, this);
    }

    public void startGame() {
        if (!gameStarted) {
            gameStarted = true;
            timer.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (freezeManager.isFrozen()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(freezeBackgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        bloodManager.updateAndDraw(g);

        for (Bug bug : bugManager.getBugs()) {
            bug.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + stats.score, 20, 30);
        g.drawString("High Score: " + stats.highScore, 20, 50);
        g.drawString("Lives: " + stats.lives, 20, 70);
        g.drawString("Speed: " + stats.antSpeed, 20, 90);
        g.drawString("Spawn Rate: " + stats.bugsPerSpawn + " bugs/sec", 20, 110);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frameCount++;
        freezeManager.update();

        if (frameCount % 30 == 0) {
            bugManager.setAntSpeed(stats.antSpeed);
            bugManager.setBugsPerSpawn(stats.bugsPerSpawn);
            bugManager.spawnBugs(freezeManager.isFrozen());
        }

        bugManager.update(freezeManager.isFrozen());

        Iterator<Bug> it = bugManager.getBugs().iterator();
        while (it.hasNext()) {
            Bug bug = it.next();
            if (bug.getY() > 800) {
                it.remove();
                stats.lives--;
                if (stats.lives == 0) {
                    timer.stop();
                    showGameOverDialog();
                }
            }
        }

        repaint();
    }

    private void showGameOverDialog() {
        if (stats.score <= stats.oldScore) {
            ImageIcon icon = new ImageIcon(new ImageIcon("normal.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JOptionPane.showMessageDialog(this, "Your Score: " + stats.score + "\nHigh Score: " + stats.highScore,
                    "Game Over", JOptionPane.PLAIN_MESSAGE, icon);
        } else {
            ImageIcon icon = new ImageIcon(new ImageIcon("congrate.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JOptionPane.showMessageDialog(this, "🎉 New High Score: " + stats.highScore + " 🎉",
                    "🏆 Congratulations!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Iterator<Bug> it = bugManager.getBugs().iterator();
        while (it.hasNext()) {
            Bug bug = it.next();
            if (bug.isClicked(e.getX(), e.getY()) && !bug.getStatus()) {
                bloodManager.addEffect(bug.getX(), bug.getY());
                it.remove();
                stats.increaseScore(bug.getScoreValue());

                if (bug instanceof IceBeetle) {
                    freezeManager.applyFreeze();
                } else if (bug instanceof Beetle) {
                    stats.lives++;
                }
                else if(bug instanceof red_ant) {
                    for (Bug b : bugManager.getBugs()) {
                        if (b != bug) {
                            stats.increaseScore(b.getScoreValue());
                        }
                    }
                bugManager.getBugs().clear();
                }
            }
        }
        repaint();
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
