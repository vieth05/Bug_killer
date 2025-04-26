package btl_java.managers;

import btl_java.BloodEffect;

import java.awt.*;
import java.util.*;

public class BloodEffectManager {
    private ArrayList<BloodEffect> bloodEffects = new ArrayList<>();

    public void addEffect(int x, int y) {
        bloodEffects.add(new BloodEffect(x, y));
    }

    public void updateAndDraw(Graphics g) {
        Iterator<BloodEffect> iter = bloodEffects.iterator();
        while (iter.hasNext()) {
            BloodEffect blood = iter.next();
            if (blood.isExpired()) {
                iter.remove();
            } else {
                blood.draw(g);
            }
        }
    }
}
