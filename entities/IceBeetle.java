package btl_java.entities;

import javax.swing.*;
import java.awt.*;

public class IceBeetle extends Bug {
    private boolean frozen = false;
    private Image image;

    public IceBeetle(int x, int y, int size, int speed) {
        super(x, y, size, speed * 2);

        ImageIcon icon = new ImageIcon("img/ice_beetle.png");
        image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public int getScoreValue() { return 0; }

    @Override
    public int getRequiredClicks() {
        return 2;
    }
}
