package eternalidle.model.items.materials;

import eternalidle.model.crafting.CraftingMaterial;
import eternalidle.model.crafting.MaterialType;
import eternalidle.model.items.ItemRarity;

public class Essence extends CraftingMaterial {
    public Essence(String element, int tier) {
        super("Essência de " + element,
                "Essência pura de " + element + " para crafting avançado",
                MaterialType.ESSENCE, tier,
                tier == 1 ? ItemRarity.UNCOMMON :
                        tier == 2 ? ItemRarity.RARE : ItemRarity.EPIC);
    }
}