package EternalIdle.items.materials;

import EternalIdle.crafting.CraftingMaterial;
import EternalIdle.crafting.MaterialType;
import EternalIdle.items.ItemRarity;

public class Core extends CraftingMaterial {
    public Core(String element, int tier) {
        super("Núcleo de " + element,
                "Núcleo poderoso de " + element + " para crafting lendário",
                MaterialType.CORE, tier,
                tier == 1 ? ItemRarity.RARE :
                        tier == 2 ? ItemRarity.EPIC : ItemRarity.LEGENDARY);
    }
}