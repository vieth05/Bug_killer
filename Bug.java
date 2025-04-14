package btl_java;

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

    // Phương thức vẽ - sẽ được override ở từng class con (Ant, Beetle, v.v.)
    public abstract void draw(Graphics g);

    // Di chuyển côn trùng
    public void move() {
        if (alive) {
            y += speed;
        }
    }

    // Kiểm tra nếu click trúng côn trùng
    public boolean isClicked(int mx, int my) {
        if (alive && mx >= x && mx <= x + size && my >= y && my <= y + size) {
            // Nếu bug chưa chết và click vào bug, tăng số lần click
            incrementClickCount();
            return true; // Trả về true để xác nhận bug đã bị click
        }
        return false; // Nếu không có click hợp lệ thì trả về false
    }


    // Lấy toạ độ y (cho kiểm tra chạm đáy)
    public int getY() {
        return y;
    }

    // Tăng số lần click, nếu đạt số yêu cầu thì chết
    public void incrementClickCount() {
        clickCount++;
        if (clickCount >= getRequiredClicks()) {
            alive = false;
        }
    }

    // Hàm abstract: số lần click cần thiết để tiêu diệt (mỗi loại bug sẽ override)
    public abstract int getRequiredClicks();

    // Lấy điểm thưởng khi tiêu diệt
    public abstract int getScoreValue();
}

