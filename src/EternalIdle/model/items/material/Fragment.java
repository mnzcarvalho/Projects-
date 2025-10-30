package EternalIdle.model.items.materials;

import EternalIdle.model.crafting.CraftingMaterial;
import EternalIdle.model.crafting.MaterialType;
import EternalIdle.model.items.ItemRarity;

public class Fragment extends CraftingMaterial {
    public Fragment(String element, int tier) {
        super("Fragmento de " + element,
                "Fragmento elemental de " + element + " para crafting",
                MaterialType.FRAGMENT, tier,
                tier == 1 ? ItemRarity.COMMON :
                        tier == 2 ? ItemRarity.UNCOMMON : ItemRarity.RARE);
    }
}