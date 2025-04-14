package btl_java;

import javax.swing.*;
import java.awt.*;

public class Ant extends Bug {
    private Image image;

    public Ant(int x, int y, int size, int speed) {
        super(x, y, size, speed);
        // Tải ảnh kiến và scale theo kích thước
        ImageIcon icon = new ImageIcon("ant.png");
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
        return 1;
    }

    @Override
    public int getScoreValue() {
        return 1;
    }
}
