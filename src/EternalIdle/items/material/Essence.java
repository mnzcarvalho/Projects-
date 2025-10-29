package EternalIdle.items.materials;

import EternalIdle.crafting.CraftingMaterial;
import EternalIdle.crafting.MaterialType;
import EternalIdle.items.ItemRarity;

public class Essence extends CraftingMaterial {
    public Essence(String element, int tier) {
        super("Essência de " + element,
                "Essência pura de " + element + " para crafting avançado",
                MaterialType.ESSENCE, tier,
                tier == 1 ? ItemRarity.UNCOMMON :
                        tier == 2 ? ItemRarity.RARE : ItemRarity.EPIC);
    }
}