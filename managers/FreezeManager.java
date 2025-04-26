package btl_java.managers;

public class FreezeManager {
    private boolean frozen = false;
    private long freezeStartTime = 0;

    public void applyFreeze() {
        frozen = true;
        freezeStartTime = System.currentTimeMillis();
    }

    public void update() {
        if (frozen && System.currentTimeMillis() - freezeStartTime >= 2000) {
            frozen = false;
        }
    }

    public boolean isFrozen() {
        return frozen;
    }
}
