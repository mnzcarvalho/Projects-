package EternalIdle.model.items.equipment;

import EternalIdle.model.items.Item;
import EternalIdle.model.items.ItemRarity;

public abstract class Equipment extends Item {
    protected int requiredLevel;
    protected boolean isEquipped;

    public Equipment(String name, String description, int value, ItemRarity rarity, int requiredLevel) {
        super(name, description, value, rarity);
        this.requiredLevel = requiredLevel;
        this.isEquipped = false;
    }

    public int getRequiredLevel() { return requiredLevel; }
    public boolean isEquipped() { return isEquipped; }
    public void setEquipped(boolean equipped) { this.isEquipped = equipped; }

    public boolean canEquip(int playerLevel) {
        return playerLevel >= requiredLevel;
    }
}