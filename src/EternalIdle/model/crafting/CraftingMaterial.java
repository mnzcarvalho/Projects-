package EternalIdle.model.crafting;

import EternalIdle.model.items.Item;
import EternalIdle.model.items.ItemRarity;

public class CraftingMaterial extends Item {
    private MaterialType materialType;
    private int tier;

    public CraftingMaterial(String name, String description, MaterialType materialType, int tier, ItemRarity rarity) {
        super(name, description, tier * 10, rarity);
        this.materialType = materialType;
        this.tier = tier;
    }

    @Override
    public void use() {
        System.out.println("🔧 " + name + " usado para crafting!");
    }

    // Getters
    public MaterialType getMaterialType() { return materialType; }
    public int getTier() { return tier; }
}