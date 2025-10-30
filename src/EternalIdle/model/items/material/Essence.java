package EternalIdle.model.items.materials;

import EternalIdle.model.crafting.CraftingMaterial;
import EternalIdle.model.crafting.MaterialType;
import EternalIdle.model.items.ItemRarity;

public class Essence extends CraftingMaterial {
    public Essence(String element, int tier) {
        super("Essência de " + element,
                "Essência pura de " + element + " para crafting avançado",
                MaterialType.ESSENCE, tier,
                tier == 1 ? ItemRarity.UNCOMMON :
                        tier == 2 ? ItemRarity.RARE : ItemRarity.EPIC);
    }
}