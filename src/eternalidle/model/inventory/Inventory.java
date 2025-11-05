package eternalidle.model.inventory;

import eternalidle.model.items.Item;
import java.util.List;
import java.util.ArrayList;

public class Inventory {
    private List<Item> items;
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    // 🔥 MÉTODO ADICIONADO: setCapacity()
    public void setCapacity(int newCapacity) {
        // Verificar se a nova capacidade é válida
        if (newCapacity < this.items.size()) {
            System.out.println("⚠️ Não é possível reduzir capacidade abaixo do número atual de itens: " + this.items.size());
            return;
        }

        this.capacity = newCapacity;
        System.out.println("✅ Capacidade do inventário aumentada para: " + newCapacity);
    }

    // 🔥 MÉTODO ADICIONADO: increaseCapacity() - alternativa mais segura
    public void increaseCapacity(int additionalSlots) {
        if (additionalSlots > 0) {
            this.capacity += additionalSlots;
            System.out.println("✅ +" + additionalSlots + " slots | Capacidade total: " + this.capacity);
        }
    }

    // Métodos existentes
    public void displayInventory() {
        System.out.println("🎒 INVENTÁRIO (" + items.size() + "/" + capacity + " slots):");

        if (items.isEmpty()) {
            System.out.println("   (vazio)");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println("   " + (i + 1) + ". " + item.getName() +
                    " [" + item.getRarity() + "]");
        }
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public boolean addItem(Item item) {
        if (items.size() < capacity) {
            items.add(item);
            return true;
        }
        return false;
    }
}