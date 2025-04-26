package btl_java.entities;

import javax.swing.*;
import java.awt.*;

public class Beetle extends Bug {
    private Image image;

    public Beetle(int x, int y, int size, int speed) {
        super(x, y, size, speed);
        ImageIcon icon = new ImageIcon("img/Beetle.png");
        image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public int getRequiredClicks() {
        return 2;
    }

    @Override
    public int getScoreValue() {
        return 3;
    }
}
