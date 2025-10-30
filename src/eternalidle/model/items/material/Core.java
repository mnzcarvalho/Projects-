package eternalidle.model.items.materials;

import eternalidle.model.crafting.CraftingMaterial;
import eternalidle.model.crafting.MaterialType;
import eternalidle.model.items.ItemRarity;

public class Core extends CraftingMaterial {
    public Core(String element, int tier) {
        super("Núcleo de " + element,
                "Núcleo poderoso de " + element + " para crafting lendário",
                MaterialType.CORE, tier,
                tier == 1 ? ItemRarity.RARE :
                        tier == 2 ? ItemRarity.EPIC : ItemRarity.LEGENDARY);
    }
}