package EternalIdle.model.inventory;

import EternalIdle.model.items.Item;
import EternalIdle.model.items.equipment.Weapon;
import EternalIdle.model.items.equipment.Armor;
import EternalIdle.model.items.consumable.Potion;
import EternalIdle.model.items.currency.Gold;
import java.util.ArrayList;
import java.util.List;

public class StashTab {
    private TabType tabType;
    private List<Item> items;
    private int capacity;
    private boolean isUnlocked;
    private String tabName;

    public StashTab(TabType tabType, int capacity, boolean isUnlocked) {
        this.tabType = tabType;
        this.capacity = capacity;
        this.isUnlocked = isUnlocked;
        this.items = new ArrayList<>();
        this.tabName = tabType.getName();
    }

    public StashTab(String customName, int capacity, boolean isUnlocked) {
        this.tabType = TabType.ALL;
        this.capacity = capacity;
        this.isUnlocked = isUnlocked;
        this.items = new ArrayList<>();
        this.tabName = customName;
    }

    public boolean addItem(Item item) {
        if (items.size() >= capacity) {
            return false;
        }

        // Verificar se o item pertence a esta tab
        if (canStoreItem(item)) {
            items.add(item);
            return true;
        }

        return false;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public boolean canStoreItem(Item item) {
        if (tabType == TabType.ALL) return true;

        return switch(tabType) {
            case WEAPONS -> item instanceof Weapon;
            case ARMOR -> item instanceof Armor;
            case CONSUMABLES -> item instanceof Potion;
            case MATERIALS -> item instanceof Gold; // Materiais de crafting
            case SPECIAL -> item.getRarity().ordinal() >= 3; // Raro ou melhor
            default -> true;
        };
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public int getUsedSlots() {
        return items.size();
    }

    public int getFreeSlots() {
        return capacity - items.size();
    }

    public void displayTab() {
        String status = isUnlocked ? "✅" : "🔒";
        System.out.println("\n" + status + " " + tabType.getEmoji() + " " + tabName + " (" + getUsedSlots() + "/" + capacity + ")");
        System.out.println("   " + tabType.getDescription());

        if (!isUnlocked) {
            System.out.println("   🔒 Tab trancada - Desbloqueie na loja!");
            return;
        }

        if (items.isEmpty()) {
            System.out.println("   (vazia)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.print("   " + (i + 1) + ". ");
                items.get(i).displayInfo();
            }
        }
    }

    // Getters
    public TabType getTabType() { return tabType; }
    public List<Item> getItems() { return items; }
    public int getCapacity() { return capacity; }
    public boolean isUnlocked() { return isUnlocked; }
    public String getTabName() { return tabName; }

    // Setters
    public void setUnlocked(boolean unlocked) { this.isUnlocked = unlocked; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}