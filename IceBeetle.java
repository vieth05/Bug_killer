package btl_java;

import javax.swing.*;
import java.awt.*;

public class IceBeetle extends Bug {
    private boolean frozen = false;
    private Image image;

    public IceBeetle(int x, int y, int size, int speed) {
        super(x, y, size, speed * 2); // speed nhân đôi khi chưa bị freeze

        // Load ảnh bọ cánh cứng băng giá
        ImageIcon icon = new ImageIcon("ice_beetle.png");
        image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
    }

    @Override
    public void move() {
        if (!frozen && alive) {
            y += speed;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public int getScoreValue() {
        return 3;
    }

    @Override
    public int getRequiredClicks() {
        return 2;
    }
}
