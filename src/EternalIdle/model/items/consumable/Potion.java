package EternalIdle.items.consumable;

import EternalIdle.items.Item;
import EternalIdle.items.ItemRarity;

public class Potion extends Item {
    private int healAmount;
    private String potionType;

    public Potion(String name, int healAmount, String potionType, ItemRarity rarity) {
        super(name, "Uma poção restauradora", healAmount * 2, rarity);
        this.healAmount = healAmount;
        this.potionType = potionType;
    }

    @Override
    public void use() {
        System.out.println("🧪 Usou " + name + "! +" + healAmount + " de vida");
    }

    public int getHealAmount() { return healAmount; }
    public String getPotionType() { return potionType; }

    @Override
    public void displayInfo() {
        System.out.println(getDisplayName());
        System.out.println("  " + description);
        System.out.println("  Cura: " + healAmount + " pontos de vida");
        System.out.println("  Valor: " + value + " ouro");
    }
}
