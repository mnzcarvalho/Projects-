package EternalIdle.model.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CraftingStation {
    private String name;
    private List<Recipe> availableRecipes;
    private int stationLevel;
    private boolean isUnlocked;

    public CraftingStation(String name, int stationLevel, boolean isUnlocked) {
        this.name = name;
        this.stationLevel = stationLevel;
        this.isUnlocked = isUnlocked;
        this.availableRecipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        availableRecipes.add(recipe);
    }

    public List<Recipe> getRecipesByType(RecipeType type) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : availableRecipes) {
            if (recipe.getRecipeType() == type && recipe.getRequiredLevel() <= stationLevel) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public Recipe findRecipeByName(String recipeName) {
        for (Recipe recipe : availableRecipes) {
            if (recipe.getName().equalsIgnoreCase(recipeName)) {
                return recipe;
            }
        }
        return null;
    }

    public void upgradeStation() {
        stationLevel++;
        System.out.println("🔼 " + name + " melhorada para nível " + stationLevel + "!");
    }

    public void displayRecipes(RecipeType type, int playerLevel, Map<CraftingMaterial, Integer> playerMaterials) {
        List<Recipe> recipes = getRecipesByType(type);

        System.out.println("\n🔨 === " + name + " - " + type.getEmoji() + " " + type.getName() + " === 🔨");

        if (recipes.isEmpty()) {
            System.out.println("   Nenhuma receita disponível.");
        } else {
            for (int i = 0; i < recipes.size(); i++) {
                Recipe recipe = recipes.get(i);
                String canCraft = recipe.canCraft(playerLevel, playerMaterials) ? "✅" : "❌";
                System.out.println(canCraft + " " + (i + 1) + ". " + recipe.getDisplayInfo());
            }
        }
        System.out.println("==========================================\n");
    }

    // Getters
    public String getName() { return name; }
    public int getStationLevel() { return stationLevel; }
    public boolean isUnlocked() { return isUnlocked; }
    public List<Recipe> getAvailableRecipes() { return availableRecipes; }

    // Setters
    public void setUnlocked(boolean unlocked) { this.isUnlocked = unlocked; }
}