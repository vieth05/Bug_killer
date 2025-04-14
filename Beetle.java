package btl_java;

import javax.swing.*;
import java.awt.*;

public class Beetle extends Bug {
    private Image image;

    public Beetle(int x, int y, int size, int speed) {
        super(x, y, size, speed);
        // Tải ảnh bọ cánh cứng và scale theo kích thước
        ImageIcon icon = new ImageIcon("Beetle.png"); // bạn đổi tên file theo đúng ảnh bạn có nhé
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
