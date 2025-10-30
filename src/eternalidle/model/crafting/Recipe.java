package eternalidle.model.crafting;

import eternalidle.model.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private String name;
    private Item result;
    private Map<CraftingMaterial, Integer> ingredients;
    private int requiredLevel;
    private int craftingTime;
    private RecipeType recipeType;

    public Recipe(String name, Item result, int requiredLevel, int craftingTime, RecipeType recipeType) {
        this.name = name;
        this.result = result;
        this.requiredLevel = requiredLevel;
        this.craftingTime = craftingTime;
        this.recipeType = recipeType;
        this.ingredients = new HashMap<>();
    }

    public void addIngredient(CraftingMaterial material, int quantity) {
        ingredients.put(material, quantity);
    }

    public boolean canCraft(int playerLevel, Map<CraftingMaterial, Integer> playerMaterials) {
        if (playerLevel < requiredLevel) return false;

        for (Map.Entry<CraftingMaterial, Integer> entry : ingredients.entrySet()) {
            CraftingMaterial requiredMaterial = entry.getKey();
            int requiredQuantity = entry.getValue();

            int playerQuantity = playerMaterials.getOrDefault(requiredMaterial, 0);
            if (playerQuantity < requiredQuantity) {
                return false;
            }
        }
        return true;
    }

    public Item craft(Map<CraftingMaterial, Integer> playerMaterials) {
        for (Map.Entry<CraftingMaterial, Integer> entry : ingredients.entrySet()) {
            CraftingMaterial material = entry.getKey();
            int quantity = entry.getValue();
            playerMaterials.put(material, playerMaterials.get(material) - quantity);
        }

        System.out.println("🔨 Crafting concluído: " + result.getName());
        return result;
    }

    public String getDisplayInfo() {
        StringBuilder info = new StringBuilder();
        info.append("📜 ").append(name).append("\n");
        info.append("   Resultado: ").append(result.getDisplayName()).append("\n");
        info.append("   Nível req: ").append(requiredLevel).append("\n");
        info.append("   Tempo: ").append(craftingTime).append("s\n");
        info.append("   Ingredientes:\n");

        for (Map.Entry<CraftingMaterial, Integer> entry : ingredients.entrySet()) {
            CraftingMaterial material = entry.getKey();
            int quantity = entry.getValue();
            info.append("     ").append(material.getMaterialType().getEmoji())
                    .append(" ").append(material.getName())
                    .append(" x").append(quantity).append("\n");
        }

        return info.toString();
    }

    // Getters
    public String getName() { return name; }
    public Item getResult() { return result; }
    public int getRequiredLevel() { return requiredLevel; }
    public int getCraftingTime() { return craftingTime; }
    public RecipeType getRecipeType() { return recipeType; }
    public Map<CraftingMaterial, Integer> getIngredients() { return ingredients; }
}