package EternalIdle.model.shop;

import EternalIdle.model.items.Item;

public class ShopItem {
    private Item item;
    private long price; // 🔥 Mudei de int para long
    private int stock;
    private int playerLevelRequired;
    private ShopCategory category;

    public ShopItem(Item item, long price, int stock, int playerLevelRequired, ShopCategory category) {
        this.item = item;
        this.price = price;
        this.stock = stock;
        this.playerLevelRequired = playerLevelRequired;
        this.category = category;
    }

    public boolean canPurchase(int playerLevel, long playerGold) { // 🔥 Mudei para long
        return playerLevel >= playerLevelRequired &&
                playerGold >= price &&
                stock > 0;
    }

    public void purchase() {
        if (stock > 0) {
            stock--;
        }
    }

    // Getters
    public Item getItem() { return item; }
    public long getPrice() { return price; } // 🔥 Mudei para long
    public int getStock() { return stock; }
    public int getPlayerLevelRequired() { return playerLevelRequired; }
    public ShopCategory getCategory() { return category; }

    public String getDisplayInfo() {
        return item.getDisplayName() +
                "\n   Preço: " + price + " ouro" +
                "\n   Estoque: " + (stock == -1 ? "∞" : stock) +
                "\n   Nível req: " + playerLevelRequired +
                "\n   " + item.getDescription();
    }
}