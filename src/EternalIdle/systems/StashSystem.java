package EternalIdle.systems;

import EternalIdle.inventory.StashManager;
import EternalIdle.inventory.StashTab;
import EternalIdle.inventory.TabType;
import EternalIdle.items.Item;
import java.util.Scanner;

public class StashSystem {
    private StashManager stashManager;
    private Scanner scanner;

    public StashSystem() {
        this.stashManager = new StashManager();
        this.scanner = new Scanner(System.in);
    }

    public void openStash() {
        System.out.println("🏠 Abrindo Stash...");

        boolean inStash = true;
        while (inStash) {
            stashManager.displayAvailableTabs();
            System.out.println("Comandos: 'ver [número]', 'todos', 'sair'");
            System.out.print("Escolha uma opção: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "sair":
                    inStash = false;
                    System.out.println("👋 Fechando stash...");
                    break;
                case "todos":
                    stashManager.displayAllTabs();
                    break;
                default:
                    if (input.startsWith("ver ")) {
                        viewSpecificTab(input);
                    } else {
                        try {
                            int tabIndex = Integer.parseInt(input) - 1;
                            viewTabByIndex(tabIndex);
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Comando inválido!");
                        }
                    }
                    break;
            }
        }
    }

    private void viewSpecificTab(String input) {
        try {
            String[] parts = input.split(" ");
            int tabIndex = Integer.parseInt(parts[1]) - 1;
            viewTabByIndex(tabIndex);
        } catch (Exception e) {
            System.out.println("❌ Use: 'ver [número]'");
        }
    }

    private void viewTabByIndex(int tabIndex) {
        if (tabIndex >= 0 && tabIndex < stashManager.getStashTabs().size()) {
            StashTab tab = stashManager.getStashTabs().get(tabIndex);
            if (tab.isUnlocked()) {
                System.out.println("\n📂 === " + tab.getTabName().toUpperCase() + " ===");
                tab.displayTab();
            } else {
                System.out.println("❌ Esta tab está trancada!");
                System.out.println("💡 Desbloqueie na loja por 150 ouro.");
            }
        } else {
            System.out.println("❌ Tab não encontrada!");
        }
    }

    public boolean autoStoreItem(Item item) {
        return stashManager.addItemToAppropriateTab(item);
    }

    public boolean unlockTab(TabType tabType) {
        boolean success = stashManager.unlockTab(tabType);
        if (success) {
            System.out.println("🎉 Tab " + tabType.getName() + " desbloqueada!");
        }
        return success;
    }

    public boolean upgradeTab(TabType tabType, int additionalSlots) {
        boolean success = stashManager.upgradeTabCapacity(tabType, additionalSlots);
        if (success) {
            System.out.println("📦 Tab " + tabType.getName() + " expandida em +" + additionalSlots + " slots!");
        }
        return success;
    }

    // Getters
    public StashManager getStashManager() { return stashManager; }
}