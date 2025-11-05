package eternalidle.model.inventory;

import eternalidle.model.items.Item;
import java.util.List;
import java.util.ArrayList;

public class StashTab {
    private TabType tabType;
    private List<Item> items;
    private int capacity;
    private boolean unlocked;

    public StashTab(TabType tabType, int capacity) {
        this.tabType = tabType;
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.unlocked = tabType.isUnlocked(); // Usa o método do enum
    }

    // 🔥 MÉTODO 1: isUnlocked()
    public boolean isUnlocked() {
        return this.unlocked;
    }

    // 🔥 MÉTODO 2: getTabName()
    public String getTabName() {
        return this.tabType.getName();
    }

    // 🔥 MÉTODO 3: displayTab()
    public void displayTab() {
        System.out.println(this.tabType.getEmoji() + " " + this.getTabName().toUpperCase() +
                " (" + this.items.size() + "/" + this.capacity + "):");
        System.out.println("─".repeat(50));

        if (this.items.isEmpty()) {
            System.out.println("   (vazio)");
            return;
        }

        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            System.out.printf("   %2d. %-20s [%s]%n",
                    (i + 1), item.getName(), item.getRarity());
        }
    }

    // 🔥 MÉTODO ADICIONAL: unlock()
    public void unlock() {
        this.unlocked = true;
    }

    // Métodos existentes
    public TabType getTabType() {
        return tabType;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int newCapacity) {
        if (newCapacity < this.items.size()) {
            System.out.println("⚠️ Não é possível reduzir capacidade abaixo do número atual de itens: " + this.items.size());
            return;
        }
        this.capacity = newCapacity;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public boolean addItem(Item item) {
        if (items.size() < capacity && unlocked) {
            items.add(item);
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }
}