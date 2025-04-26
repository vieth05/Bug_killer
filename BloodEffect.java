package btl_java;

import javax.swing.*;
import java.awt.*;

public class BloodEffect {
    private int x, y;
    private long startTime;
    private static final int DURATION = 2000;
    private final Image bloodImage = new ImageIcon("img/blood.png").getImage();

    public BloodEffect(int x, int y) {
        this.x = x;
        this.y = y;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - startTime > DURATION;
    }

    public void draw(Graphics g) {
        long elapsed = System.currentTimeMillis() - startTime;
        float alpha = 1.0f - (float) elapsed / DURATION;
        alpha = Math.max(0, alpha);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(bloodImage, x, y, 50, 50, null);
        g2d.dispose();
    }
}
