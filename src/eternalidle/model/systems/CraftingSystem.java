package eternalidle.model.systems;

import eternalidle.model.crafting.CraftingStation;

import java.util.*;

public class CraftingSystem {
    private Map<Object, Integer> playerMaterials; // Use Object temporariamente
    private List<CraftingStation> craftingStations;
    private Scanner scanner;

    public CraftingSystem() {
        this.playerMaterials = new HashMap<>();
        this.craftingStations = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeCraftingSystem();
    }

    private void initializeCraftingSystem() {
        // Estação básica sempre desbloqueada
        CraftingStation basicStation = new CraftingStation("Forja Básica", 1, true);
        craftingStations.add(basicStation);

        // Estação de armaduras trancada inicialmente
        CraftingStation armorStation = new CraftingStation("Estação de Armaduras", 1, false);
        craftingStations.add(armorStation);
    }

    public void openCrafting() {
        System.out.println("🔨 Sistema de Crafting (Em Desenvolvimento)");
        System.out.println("📦 Materiais coletados: " + playerMaterials.size() + " tipos");

        // Menu temporário simplificado
        System.out.println("\nOpções disponíveis em breve:");
        System.out.println("• Craft de armas");
        System.out.println("• Craft de armaduras");
        System.out.println("• Upgrade de equipamentos");
        System.out.println("• Ver materiais coletados");

        System.out.println("\n🚧 Sistema em construção... Volte em breve!");
    }

    public void addMaterial(Object material, int quantity) {
        playerMaterials.put(material, playerMaterials.getOrDefault(material, 0) + quantity);
    }

    // Método temporário para desbloquear estações
    public boolean unlockStation(String stationName) {
        for (CraftingStation station : craftingStations) {
            if (station.getName().equalsIgnoreCase(stationName) && !station.isUnlocked()) {
                station.setUnlocked(true);
                return true;
            }
        }
        return false;
    }

    // Getters
    public Map<Object, Integer> getPlayerMaterials() { return playerMaterials; }
}