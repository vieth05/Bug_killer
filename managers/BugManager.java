package btl_java.managers;

import btl_java.entities.*;

import java.util.*;

public class BugManager {
    private ArrayList<Bug> bugs;
    private Random rand;
    private int antSpeed;
    private int bugsPerSpawn;

    public BugManager(int antSpeed, int bugsPerSpawn, Random rand) {
        this.bugs = new ArrayList<>();
        this.rand = rand;
        this.antSpeed = antSpeed;
        this.bugsPerSpawn = bugsPerSpawn;
        for (int i = 0; i < 5; i++) {
            bugs.add(new Ant(rand.nextInt(770), 0, 50, antSpeed));
        }
    }

    public void update(boolean frozen) {
        if (!frozen) {
            for (Bug bug : bugs) bug.move();
        }
    }
    public void spawnBugs(boolean frozen) {
        if (frozen) return;
        for (int i = 0; i < bugsPerSpawn; i++) {
            int chance = rand.nextInt(100);
            if (chance < 10) {
                bugs.add(new Beetle(rand.nextInt(770), 0, 50, antSpeed / 2));
            } else if (chance < 15) {
                bugs.add(new IceBeetle(rand.nextInt(770), 0, 50, antSpeed));
            }
            else if(chance<20) {
                bugs.add(new red_ant(rand.nextInt(770), 0, 50, antSpeed));

            }
            else {
                bugs.add(new Ant(rand.nextInt(770), 0, 50, antSpeed));
            }
        }
    }

    public ArrayList<Bug> getBugs() {
        return bugs;
    }

    public void removeBug(Bug bug) {
        bugs.remove(bug);
    }

    public void setAntSpeed(int speed) {
        this.antSpeed = speed;
    }

    public void setBugsPerSpawn(int rate) {
        this.bugsPerSpawn = rate;
    }
}

