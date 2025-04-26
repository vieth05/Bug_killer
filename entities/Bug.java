package btl_java.entities;

import java.awt.*;

public abstract class Bug {
    protected int x, y, size, speed;
    protected boolean alive = true;
    protected int clickCount = 0;

    public Bug(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public abstract void draw(Graphics g);
    public void move() {
        if (alive) {
            y += speed;
        }
    }

    public boolean isClicked(int mx, int my) {
        if (alive && mx >= x && mx <= x + size && my >= y && my <= y + size) {
            incrementClickCount();
            return true;
        }
        return false;
    }

    public int getY() {
        return y;
    }

    public int getX() { return x; }

    public boolean getStatus() { return alive; }

    public void incrementClickCount() {
        clickCount++;
        if (clickCount >= getRequiredClicks()) {
            alive = false;
        }
    }

    public abstract int getRequiredClicks();

    public abstract int getScoreValue();
}

