package eternalidle.model.inventory;

import eternalidle.model.items.Item;
import java.util.List;
import java.util.ArrayList;

public class StashManager {
    private List<StashTab> tabs;
    private List<TabType> unlockedTabs;

    public StashManager() {
        this.tabs = new ArrayList<>();
        this.unlockedTabs = new ArrayList<>();
        initializeTabs();
    }

    private void initializeTabs() {
        // Inicializar apenas tabs básicas
        tabs.add(new StashTab(TabType.WEAPONS, 20));
        tabs.add(new StashTab(TabType.ARMOR, 20));
        tabs.add(new StashTab(TabType.MATERIALS, 20));

        // Desbloquear tabs básicas
        unlockedTabs.add(TabType.WEAPONS);
        unlockedTabs.add(TabType.ARMOR);
        unlockedTabs.add(TabType.MATERIALS);
    }
    // 🔥 MÉTODO ATUALIZADO: isUnlocked() - delega para a tab
    public boolean isUnlocked(TabType tabType) {
        if (tabType == TabType.ALL) return true;

        StashTab tab = getTabByType(tabType);
        return tab != null && tab.isUnlocked();
    }

    // 🔥 MÉTODO ATUALIZADO: getTabName() - delega para a tab
    public String getTabName(TabType tabType) {
        if (tabType == TabType.ALL) return "Todos Itens";

        StashTab tab = getTabByType(tabType);
        return tab != null ? tab.getTabName() : tabType.getName();
    }

    // 🔥 MÉTODO ATUALIZADO: displayTab()
    public void displayTab(TabType tabType) {
        StashTab tab = getTabByType(tabType);
        if (tab == null) {
            System.out.println("❌ Aba não encontrada!");
            return;
        }

        if (!tab.isUnlocked()) {
            System.out.println("❌ Aba " + tab.getTabName() + " está bloqueada!");
            return;
        }

        tab.displayTab(); // ✅ Agora chama o método da própria tab
    }

    // 🔥 MÉTODO ADICIONADO: displayAllTabs()
    public void displayAllTabs() {
        System.out.println("🏠 TODAS AS ABAS DO STASH:");
        System.out.println("┌─────────────────────────────────────┐");

        for (TabType tabType : TabType.values()) {
            if (tabType == TabType.ALL) continue; // Pular ALL na listagem geral

            String status = isUnlocked(tabType) ? "✅" : "🔒";
            StashTab tab = getTabByType(tabType);
            String usage = "0/0";

            if (tab != null) {
                usage = tab.getItems().size() + "/" + tab.getCapacity();
            }

            System.out.printf("│ %s %s %-15s %8s │%n",
                    status, tabType.getEmoji(), tabType.getName(), usage);
        }

        System.out.println("└─────────────────────────────────────┘");
    }

    // 🔥 MÉTODO ADICIONADO: getStashTabs()
    public List<StashTab> getStashTabs() {
        return new ArrayList<>(tabs);
    }

    // 🔥 MÉTODO ATUALIZADO: unlockTab()
    public boolean unlockTab(TabType tabType) {
        if (tabType == TabType.ALL) {
            System.out.println("ℹ️ Aba 'Todos Itens' já está sempre disponível!");
            return true;
        }

        StashTab tab = getTabByType(tabType);
        if (tab == null) {
            // Criar nova tab
            tab = new StashTab(tabType, 10);
            tabs.add(tab);
        }

        if (tab.isUnlocked()) {
            System.out.println("ℹ️ Aba " + tab.getTabName() + " já está desbloqueada!");
            return true;
        }

        tab.unlock();
        unlockedTabs.add(tabType);
        System.out.println("🎉 Aba " + tab.getTabName() + " desbloqueada!");
        return true;
    }

    // 🔥 MÉTODO ADICIONADO: upgradeTabCapacity()
    public boolean upgradeTabCapacity(TabType tabType, int additionalSlots) {
        if (tabType == TabType.ALL) {
            System.out.println("⚠️ Não é possível expandir capacidade da aba 'Todos Itens'");
            return false;
        }

        StashTab tab = getTabByType(tabType);
        if (tab == null) {
            System.out.println("❌ Aba " + tabType.getName() + " não encontrada!");
            return false;
        }

        if (!isUnlocked(tabType)) {
            System.out.println("❌ Aba " + tabType.getName() + " está bloqueada!");
            return false;
        }

        if (additionalSlots <= 0) {
            System.out.println("❌ Número de slots inválido: " + additionalSlots);
            return false;
        }

        tab.setCapacity(tab.getCapacity() + additionalSlots);
        System.out.println("✅ +" + additionalSlots + " slots na aba " +
                tabType.getName() + " | Total: " + tab.getCapacity());
        return true;
    }

    // Métodos auxiliares existentes
    public StashTab getTabByType(TabType type) {
        if (type == TabType.ALL) {
            return createVirtualAllTab();
        }

        for (StashTab tab : tabs) {
            if (tab.getTabType() == type) {
                return tab;
            }
        }
        return null;
    }

    private StashTab createVirtualAllTab() {
        StashTab virtualTab = new StashTab(TabType.ALL, getTotalCapacity());

        // Adicionar todos os itens de todas as tabs desbloqueadas
        for (StashTab tab : tabs) {
            if (isUnlocked(tab.getTabType())) {
                virtualTab.getItems().addAll(tab.getItems());
            }
        }

        return virtualTab;
    }

    public void displayAvailableTabs() {
        System.out.println("🏠 STASH - ABAS DISPONÍVEIS:");
        System.out.println("┌──────────────────────────────────┐");

        for (TabType tabType : unlockedTabs) {
            StashTab tab = getTabByType(tabType);
            if (tab != null) {
                String usage = tab.getItems().size() + "/" + tab.getCapacity();
                System.out.printf("│ %s %-15s %8s │%n",
                        tabType.getEmoji(), tabType.getName(), usage);
            }
        }

        // Sempre mostrar ALL
        StashTab allTab = getTabByType(TabType.ALL);
        if (allTab != null) {
            String usage = allTab.getItems().size() + "/" + allTab.getCapacity();
            System.out.printf("│ %s %-15s %8s │%n",
                    TabType.ALL.getEmoji(), TabType.ALL.getName(), usage);
        }

        System.out.println("└──────────────────────────────────┘");
        System.out.println("📊 Total: " + getTotalItemCount() + "/" + getTotalCapacity() + " itens");
    }

    // Métodos existentes (mantidos)
    public boolean isFull() {
        return getTotalItemCount() >= getTotalCapacity();
    }

    public int getTotalItemCount() {
        int total = 0;
        for (StashTab tab : tabs) {
            if (isUnlocked(tab.getTabType())) {
                total += tab.getItems().size();
            }
        }
        return total;
    }

    public int getTotalCapacity() {
        int total = 0;
        for (StashTab tab : tabs) {
            if (isUnlocked(tab.getTabType())) {
                total += tab.getCapacity();
            }
        }
        return total;
    }

    public boolean removeItemFromTab(Item item, TabType tabType) {
        if (tabType == TabType.ALL) {
            // Remover de todas as tabs
            boolean removed = false;
            for (StashTab tab : tabs) {
                if (tab.getItems().remove(item)) {
                    removed = true;
                }
            }
            return removed;
        }

        StashTab tab = getTabByType(tabType);
        return tab != null && tab.getItems().remove(item);
    }

    public void organizeAllTabs() {
        for (StashTab tab : tabs) {
            if (isUnlocked(tab.getTabType())) {
                tab.getItems().sort((item1, item2) ->
                        item1.getName().compareToIgnoreCase(item2.getName()));
            }
        }
    }

    public boolean addItemToAppropriateTab(Item item) {
        TabType appropriateType = determineTabType(item);
        StashTab tab = getTabByType(appropriateType);

        if (tab != null && tab.getItems().size() < tab.getCapacity()) {
            tab.getItems().add(item);
            return true;
        }
        return false;
    }

    private TabType determineTabType(Item item) {
        if (item instanceof eternalidle.model.items.equipment.Weapon) {
            return TabType.WEAPONS;
        } else if (item instanceof eternalidle.model.items.equipment.Armor) {
            return TabType.ARMOR;
        } else {
            return TabType.MATERIALS;
        }
    }

    public List<StashTab> getTabs() {
        return tabs;
    }
}