package eternalidle.model.entity;

import java.math.BigDecimal;

public class Monster {
    private String name;
    private int level;
    private BigDecimal health;
    private BigDecimal maxHealth;
    private BigDecimal damage;
    private double attackSpeed;
    private int spawnTime;
    private long expReward;
    private long goldReward;

    public Monster(String name, int level, String health, String damage,
                   double attackSpeed, int spawnTime) {
        this.name = name;
        this.level = level;
        this.maxHealth = new BigDecimal(health);
        this.health = this.maxHealth;
        this.damage = new BigDecimal(damage);
        this.attackSpeed = attackSpeed;
        this.spawnTime = spawnTime;
        this.expReward = level * 10L;
        this.goldReward = level * 5L;
    }

    // Getters
    public String getName() { return name; }
    public int getLevel() { return level; }
    public BigDecimal getHealth() { return health; }
    public BigDecimal getMaxHealth() { return maxHealth; }
    public BigDecimal getDamage() { return damage; }
    public long getExpReward() { return expReward; }
    public long getGoldReward() { return goldReward; }

    // Cálculo de DPS para combate idle
    public BigDecimal getDamagePerSecond() {
        return damage.multiply(BigDecimal.valueOf(attackSpeed));
    }

    // Métodos de combate
    public boolean takeDamage(BigDecimal damage) {
        health = health.subtract(damage);
        return health.compareTo(BigDecimal.ZERO) <= 0;
    }

    public void respawn() {
        this.health = this.maxHealth;
    }

    public boolean isAlive() {
        return health.compareTo(BigDecimal.ZERO) > 0;
    }

    public void displayInfo() {
        System.out.println("🐉 " + name + " (Nível " + level + ")");
        System.out.println("   Vida: " + health + "/" + maxHealth);
        System.out.println("   Dano: " + damage + " (" + attackSpeed + "/s)");
        System.out.println("   DPS: " + getDamagePerSecond());
    }

    public void setExpReward(long expReward) {
        this.expReward = expReward;
    }

    public void setGoldReward(long goldReward) {
        this.goldReward = goldReward;
    }
}
