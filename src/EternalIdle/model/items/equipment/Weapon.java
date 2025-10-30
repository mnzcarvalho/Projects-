package EternalIdle.model.items.equipment;

import EternalIdle.model.items.ItemRarity;

public class Weapon extends Equipment {
    private int damage;
    private double attackSpeed;
    private String weaponType;

    public Weapon(String name, int damage, double attackSpeed, String weaponType, ItemRarity rarity, int requiredLevel) {
        super(name, "Uma arma poderosa", damage * 10, rarity, requiredLevel);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.weaponType = weaponType;
    }

    @Override
    public void use() {
        System.out.println("⚔️ Equipou " + name + "!");
    }

    public int getDamage() { return damage; }
    public double getAttackSpeed() { return attackSpeed; }
    public String getWeaponType() { return weaponType; }

    public double getDPS() {
        return damage * attackSpeed;
    }

    @Override
    public void displayInfo() {
        System.out.println(getDisplayName() + " [" + weaponType + "]");
        System.out.println("  " + description);
        System.out.println("  Dano: " + damage + " | Velocidade: " + attackSpeed + "/s");
        System.out.println("  DPS: " + String.format("%.1f", getDPS()));
        System.out.println("  Nível requerido: " + requiredLevel);
        System.out.println("  Valor: " + value + " ouro");
    }
}