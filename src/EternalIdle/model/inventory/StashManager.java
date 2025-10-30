package EternalIdle.inventory;

import EternalIdle.items.Item;
import java.util.ArrayList;
import java.util.List;

public class StashManager {
    private List<StashTab> stashTabs;
    private int maxTabs;
    private int baseTabCapacity;

    public StashManager() {
        this.stashTabs = new ArrayList<>();
        this.maxTabs = 5;
        this.baseTabCapacity = 20;
        initializeDefaultTabs();
    }

    private void initializeDefaultTabs() {
        // Tab principal sempre desbloqueada
        stashTabs.add(new StashTab(TabType.ALL, baseTabCapacity, true));

        // Tabs especiais trancadas inicialmente
        stashTabs.add(new StashTab(TabType.WEAPONS, baseTabCapacity, false));
        stashTabs.add(new StashTab(TabType.ARMOR, baseTabCapacity, false));
        stashTabs.add(new StashTab(TabType.CONSUMABLES, baseTabCapacity, false));
        stashTabs.add(new StashTab(TabType.SPECIAL, baseTabCapacity, false));
    }

    public boolean addItemToAppropriateTab(Item item) {
        // Tentar adicionar à tab ALL primeiro
        StashTab allTab = getTabByType(TabType.ALL);
        if (allTab != null && allTab.isUnlocked() && !allTab.isFull()) {
            return allTab.addItem(item);
        }

        // Tentar adicionar à tab específica do item
        StashTab specificTab = findAppropriateTab(item);
        if (specificTab != null && specificTab.isUnlocked() && !specificTab.isFull()) {
            return specificTab.addItem(item);
        }

        // Se não encontrou tab apropriada, tentar qualquer tab com espaço
        for (StashTab tab : stashTabs) {
            if (tab.isUnlocked() && !tab.isFull() && tab.canStoreItem(item)) {
                return tab.addItem(item);
            }
        }

        return false;
    }

    private StashTab findAppropriateTab(Item item) {
        for (StashTab tab : stashTabs) {
            if (tab.canStoreItem(item) && tab.getTabType() != TabType.ALL) {
                return tab;
            }
        }
        return null;
    }

    public StashTab getTabByType(TabType tabType) {
        for (StashTab tab : stashTabs) {
            if (tab.getTabType() == tabType) {
                return tab;
            }
        }
        return null;
    }

    public boolean unlockTab(TabType tabType) {
        StashTab tab = getTabByType(tabType);
        if (tab != null && !tab.isUnlocked()) {
            tab.setUnlocked(true);
            return true;
        }
        return false;
    }

    public boolean upgradeTabCapacity(TabType tabType, int additionalSlots) {
        StashTab tab = getTabByType(tabType);
        if (tab != null && tab.isUnlocked()) {
            tab.setCapacity(tab.getCapacity() + additionalSlots);
            return true;
        }
        return false;
    }

    public boolean addCustomTab(String tabName, int capacity) {
        if (stashTabs.size() >= maxTabs) {
            return false;
        }

        StashTab newTab = new StashTab(tabName, capacity, true);
        stashTabs.add(newTab);
        return true;
    }

    public void displayAllTabs() {
        System.out.println("\n🏠 === STASH TABS ===");
        System.out.println("Tabs: " + getUnlockedTabCount() + "/" + stashTabs.size() + " desbloqueadas");
        System.out.println("Slots totais: " + getTotalCapacity() + " | Usados: " + getTotalUsedSlots());
        System.out.println("----------------------------------------");

        for (StashTab tab : stashTabs) {
            tab.displayTab();
        }
        System.out.println("========================================\n");
    }

    public void displayAvailableTabs() {
        System.out.println("\n📂 === TABS DISPONÍVEIS ===");
        for (int i = 0; i < stashTabs.size(); i++) {
            StashTab tab = stashTabs.get(i);
            String status = tab.isUnlocked() ? "✅" : "🔒";
            System.out.println((i + 1) + ". " + status + " " + tab.getTabType().getEmoji() + " " + tab.getTabName() +
                    " (" + tab.getUsedSlots() + "/" + tab.getCapacity() + ")");
        }
        System.out.println("=============================\n");
    }

    // Métodos utilitários
    public int getTotalCapacity() {
        int total = 0;
        for (StashTab tab : stashTabs) {
            if (tab.isUnlocked()) {
                total += tab.getCapacity();
            }
        }
        return total;
    }

    public int getTotalUsedSlots() {
        int total = 0;
        for (StashTab tab : stashTabs) {
            if (tab.isUnlocked()) {
                total += tab.getUsedSlots();
            }
        }
        return total;
    }

    public int getUnlockedTabCount() {
        int count = 0;
        for (StashTab tab : stashTabs) {
            if (tab.isUnlocked()) {
                count++;
            }
        }
        return count;
    }

    // Getters
    public List<StashTab> getStashTabs() { return stashTabs; }
    public int getMaxTabs() { return maxTabs; }
}