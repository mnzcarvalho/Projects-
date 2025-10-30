package EternalIdle.model.crafting;

import EternalIdle.model.items.Item;
import EternalIdle.model.items.materials.Fragment;
import EternalIdle.model.items.materials.Essence;
import EternalIdle.model.items.equipment.Weapon;
import EternalIdle.model.items.equipment.Armor;
import EternalIdle.model.items.ItemRarity;
import java.util.*;

public class CraftingSystem {
    private Map<CraftingMaterial, Integer> playerMaterials;
    private List<CraftingStation> craftingStations;
    private Scanner scanner;

    public CraftingSystem() {
        this.playerMaterials = new HashMap<>();
        this.craftingStations = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeCraftingSystem();
    }

    private void initializeCraftingSystem() {
        // 🔥 Estação de Forja (Armas)
        CraftingStation forge = new CraftingStation("Forja", 1, true);

        // Receita: Espada Básica
        Recipe basicSwordRecipe = new Recipe("Espada Básica",
                new Weapon("Espada do Aprendiz", 15, 1.1, "Espada", ItemRarity.COMMON, 1),
                1, 10, RecipeType.WEAPON);
        basicSwordRecipe.addIngredient(new Fragment("Ferro", 1), 3);
        basicSwordRecipe.addIngredient(new Fragment("Fogo", 1), 2);
        forge.addRecipe(basicSwordRecipe);

        // Receita: Arco do Caçador
        Recipe hunterBowRecipe = new Recipe("Arco do Caçador",
                new Weapon("Arco do Caçador", 12, 1.6, "Arco", ItemRarity.UNCOMMON, 3),
                3, 15, RecipeType.WEAPON);
        hunterBowRecipe.addIngredient(new Fragment("Madeira", 1), 5);
        hunterBowRecipe.addIngredient(new Essence("Vento", 1), 1);
        forge.addRecipe(hunterBowRecipe);

        // 🔥 Estação de Armaduras
        CraftingStation armorStation = new CraftingStation("Estação de Armaduras", 1, false);

        // Receita: Armadura de Couro
        Recipe leatherArmorRecipe = new Recipe("Armadura de Couro",
                new Armor("Peitoral de Couro", 8, 25, "CHEST", ItemRarity.COMMON, 2),
                2, 12, RecipeType.ARMOR);
        leatherArmorRecipe.addIngredient(new Fragment("Couro", 1), 4);
        leatherArmorRecipe.addIngredient(new Fragment("Tecido", 1), 3);
        armorStation.addRecipe(leatherArmorRecipe);

        craftingStations.add(forge);
        craftingStations.add(armorStation);
    }

    public void openCrafting() {
        System.out.println("🔨 Bem-vindo ao Sistema de Crafting!");

        boolean inCrafting = true;
        while (inCrafting) {
            displayCraftingMainMenu();
            System.out.print("Escolha uma opção: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "sair":
                    inCrafting = false;
                    System.out.println("👋 Fechando crafting...");
                    break;
                case "materiais":
                    displayPlayerMaterials();
                    break;
                case "estacoes":
                    displayCraftingStations();
                    break;
                case "1":
                    useCraftingStation(0); // Forja
                    break;
                case "2":
                    useCraftingStation(1); // Estação de Armaduras
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
                    break;
            }
        }
    }

    private void displayCraftingMainMenu() {
        System.out.println("\n🔨 === SISTEMA DE CRAFTING ===");
        System.out.println("Materiais disponíveis: " + getTotalMaterials());

        for (int i = 0; i < craftingStations.size(); i++) {
            CraftingStation station = craftingStations.get(i);
            String status = station.isUnlocked() ? "✅" : "🔒";
            System.out.println((i + 1) + ". " + status + " " + station.getName() + " (Nível " + station.getStationLevel() + ")");
        }

        System.out.println("materiais - Ver materiais");
        System.out.println("estacoes - Ver todas estações");
        System.out.println("sair - Sair do crafting");
        System.out.println("==============================\n");
    }

    private void displayCraftingStations() {
        System.out.println("\n🏭 === ESTAÇÕES DE CRAFTING ===");
        for (CraftingStation station : craftingStations) {
            String status = station.isUnlocked() ? "✅ Desbloqueada" : "🔒 Trancada";
            System.out.println("🔨 " + station.getName());
            System.out.println("   " + status + " | Nível: " + station.getStationLevel());
            System.out.println("   Receitas: " + station.getAvailableRecipes().size());
            System.out.println();
        }
    }

    private void useCraftingStation(int stationIndex) {
        if (stationIndex < 0 || stationIndex >= craftingStations.size()) {
            System.out.println("❌ Estação não encontrada!");
            return;
        }

        CraftingStation station = craftingStations.get(stationIndex);
        if (!station.isUnlocked()) {
            System.out.println("❌ Esta estação está trancada!");
            System.out.println("💡 Desbloqueie na loja por 200 ouro.");
            return;
        }

        boolean inStation = true;
        while (inStation) {
            System.out.println("\n🔨 === " + station.getName().toUpperCase() + " ===");
            System.out.println("1. ⚔️ Armas");
            System.out.println("2. 🛡️ Armaduras");
            System.out.println("3. 🧪 Poções");
            System.out.println("voltar - Voltar");
            System.out.print("Escolha um tipo: ");

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "voltar":
                    inStation = false;
                    break;
                case "1":
                    craftInStation(station, RecipeType.WEAPON);
                    break;
                case "2":
                    craftInStation(station, RecipeType.ARMOR);
                    break;
                case "3":
                    craftInStation(station, RecipeType.POTION);
                    break;
                default:
                    System.out.println("❌ Tipo inválido!");
                    break;
            }
        }
    }

    private void craftInStation(CraftingStation station, RecipeType recipeType) {
        station.displayRecipes(recipeType, 1, playerMaterials); // Assume player level 1 por enquanto

        List<Recipe> recipes = station.getRecipesByType(recipeType);
        if (recipes.isEmpty()) {
            System.out.println("❌ Nenhuma receita disponível deste tipo!");
            return;
        }

        System.out.print("Digite o número da receita para craftar ou 'voltar': ");
        String input = scanner.nextLine().trim();

        if (input.equals("voltar")) return;

        try {
            int recipeIndex = Integer.parseInt(input) - 1;
            if (recipeIndex >= 0 && recipeIndex < recipes.size()) {
                Recipe selectedRecipe = recipes.get(recipeIndex);
                attemptCraft(selectedRecipe);
            } else {
                System.out.println("❌ Receita não encontrada!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida!");
        }
    }

    private void attemptCraft(Recipe recipe) {
        if (recipe.canCraft(1, playerMaterials)) { // Assume player level 1
            System.out.println("🔄 Crafting " + recipe.getName() + "... (" + recipe.getCraftingTime() + "s)");

            // Simular tempo de crafting
            try {
                for (int i = 0; i < recipe.getCraftingTime(); i++) {
                    Thread.sleep(100); // 100ms por "segundo" para teste
                    System.out.print("⏳ ");
                }
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println("❌ Crafting interrompido!");
                return;
            }

            Item result = recipe.craft(playerMaterials);
            System.out.println("🎉 " + result.getName() + " craftado com sucesso!");

        } else {
            System.out.println("❌ Não é possível craftar esta receita!");
            System.out.println("   Verifique nível e materiais necessários.");
        }
    }

    public void addMaterial(CraftingMaterial material, int quantity) {
        playerMaterials.put(material, playerMaterials.getOrDefault(material, 0) + quantity);
    }

    public void displayPlayerMaterials() {
        System.out.println("\n🎒 === SEUS MATERIAIS ===");

        if (playerMaterials.isEmpty()) {
            System.out.println("   Nenhum material coletado.");
        } else {
            for (Map.Entry<CraftingMaterial, Integer> entry : playerMaterials.entrySet()) {
                CraftingMaterial material = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("   " + material.getMaterialType().getEmoji() + " " +
                        material.getName() + " x" + quantity);
            }
        }
        System.out.println("========================\n");
    }

    private int getTotalMaterials() {
        int total = 0;
        for (int quantity : playerMaterials.values()) {
            total += quantity;
        }
        return total;
    }

    // Getters
    public Map<CraftingMaterial, Integer> getPlayerMaterials() { return playerMaterials; }
    public List<CraftingStation> getCraftingStations() { return craftingStations; }

    // Método para desbloquear estação
    public boolean unlockStation(String stationName) {
        for (CraftingStation station : craftingStations) {
            if (station.getName().equalsIgnoreCase(stationName) && !station.isUnlocked()) {
                station.setUnlocked(true);
                return true;
            }
        }
        return false;
    }
}