package deathbyquestion;

public class LifeSystem {
    private int maxLife;
    private int currentLife;

    public LifeSystem(int maxLife) {
        this.maxLife = maxLife;
        this.currentLife = maxLife;
    }

    public void loseLife(int amount) {
        currentLife -= amount;
        if (currentLife < 0) currentLife = 0;
    }

    public void reset() {
        currentLife = maxLife;
    }

    public int getLife() {
        return currentLife;
    }

    public boolean isDead() {
        return currentLife <= 0;
    }
}
