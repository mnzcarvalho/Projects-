package EternalIdle.model.items.equipment;

import EternalIdle.model.items.ItemRarity;

public class Armor extends Equipment {
    private int defense;
    private int healthBonus;
    private String armorType; // "HELMET", "CHEST", "BOOTS"

    public Armor(String name, int defense, int healthBonus, String armorType, ItemRarity rarity, int requiredLevel) {
        super(name, "Uma peça de armadura resistente", (defense + healthBonus) * 5, rarity, requiredLevel);
        this.defense = defense;
        this.healthBonus = healthBonus;
        this.armorType = armorType;
    }

    @Override
    public void use() {
        System.out.println("🛡️ Equipou " + name + "!");
    }

    public int getDefense() { return defense; }
    public int getHealthBonus() { return healthBonus; }
    public String getArmorType() { return armorType; }

    @Override
    public void displayInfo() {
        System.out.println(getDisplayName() + " [" + armorType + "]");
        System.out.println("  " + description);
        System.out.println("  Defesa: " + defense + " | Vida: +" + healthBonus);
        System.out.println("  Nível requerido: " + requiredLevel);
        System.out.println("  Valor: " + value + " ouro");
    }
}