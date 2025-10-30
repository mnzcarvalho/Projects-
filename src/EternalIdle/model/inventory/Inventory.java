package EternalIdle.inventory;

import EternalIdle.items.Item;
import EternalIdle.items.currency.Gold;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private int capacity;
    private int gold;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.gold = 0;
    }

    public boolean addItem(Item item) {
        if (items.size() < capacity) {
            // 🔥 CORREÇÃO: Se for ouro, soma direto ao gold em vez de add ao inventário
            if (item instanceof Gold) {
                Gold goldItem = (Gold) item;
                addGold(goldItem.getAmount());
                System.out.println("💰 +" + goldItem.getAmount() + " ouro adicionado!");
                return true;
            } else {
                items.add(item);
                System.out.println("🎒 " + item.getName() + " adicionado ao inventário!");
                return true;
            }
        } else {
            System.out.println("❌ Inventário cheio! Não foi possível adicionar " + item.getName());
            return false;
        }
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void displayInventory() {
        System.out.println("\n=== INVENTÁRIO ===");
        System.out.println("💰 Ouro: " + gold); // 🔥 Agora mostra o ouro correto
        System.out.println("🎒 Itens (" + items.size() + "/" + capacity + "):");

        if (items.isEmpty()) {
            System.out.println("  (vazio)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.print("  " + (i + 1) + ". ");
                items.get(i).displayInfo();
            }
        }
        System.out.println("==================\n");
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
        System.out.println("📦 Inventário expandido para " + newCapacity + " slots!");
    }

    // Getters - 🔥 REMOVI A CHAVE EXTRA DAQUI!
    public List<Item> getItems() { return items; }
    public int getGold() { return gold; }
    public int getCapacity() { return capacity; }
}
// 🔥 SÓ UMA CHAVE DE FECHAMENTO NO FINAL!