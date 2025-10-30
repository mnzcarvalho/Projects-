package eternalidle.model.shop;

import eternalidle.model.items.currency.Gold;
import eternalidle.model.items.equipment.Weapon;
import eternalidle.model.items.equipment.Armor;
import eternalidle.model.items.consumable.Potion;
import eternalidle.model.items.ItemRarity;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<ShopItem> shopItems;
    private boolean isUnlocked;

    public Shop() {
        this.shopItems = new ArrayList<>();
        this.isUnlocked = true; // Loja sempre disponível
        initializeShopItems();
    }

    private void initializeShopItems() {
        // 🔥 ARMAS (use L para indicar long)
        shopItems.add(new ShopItem(
                new Weapon("Espada de Ferro", 12, 1.0, "Espada", ItemRarity.COMMON, 1),
                50L, 5, 1, ShopCategory.WEAPONS // 🔥 Adicione L no final
        ));

        shopItems.add(new ShopItem(
                new Weapon("Arco de Caça", 8, 1.5, "Arco", ItemRarity.COMMON, 1),
                45L, 5, 1, ShopCategory.WEAPONS // 🔥 Adicione L no final
        ));

        shopItems.add(new ShopItem(
                new Weapon("Machado de Batalha", 18, 0.8, "Machado", ItemRarity.UNCOMMON, 5),
                120L, 3, 5, ShopCategory.WEAPONS // 🔥 Adicione L no final
        ));

        // 🔥 ARMADURAS
        shopItems.add(new ShopItem(
                new Armor("Elmo de Ferro", 5, 20, "HELMET", ItemRarity.COMMON, 1),
                40L, 5, 1, ShopCategory.ARMOR // 🔥 Adicione L no final
        ));

        shopItems.add(new ShopItem(
                new Armor("Peitoral de Aço", 8, 35, "CHEST", ItemRarity.COMMON, 1),
                80L, 5, 1, ShopCategory.ARMOR // 🔥 Adicione L no final
        ));

        // 🔥 POÇÕES
        shopItems.add(new ShopItem(
                new Potion("Poção de Cura Pequena", 30, "HEAL", ItemRarity.COMMON),
                20L, 10, 1, ShopCategory.POTIONS // 🔥 Adicione L no final
        ));

        shopItems.add(new ShopItem(
                new Potion("Poção de Cura Grande", 60, "HEAL", ItemRarity.UNCOMMON),
                50L, 5, 5, ShopCategory.POTIONS // 🔥 Adicione L no final
        ));

        // 🔥 MATERIAIS
        shopItems.add(new ShopItem(
                new Gold(100), // Pacote de ouro
                90L, // Preço com desconto (10% off) 🔥 Adicione L no final
                3, 3, ShopCategory.MATERIALS
        ));

        // 🔥 MELHORIAS
        shopItems.add(new ShopItem(
                new Gold(0), // Slot de inventário (item especial)
                200L, -1, 10, ShopCategory.UPGRADES // 🔥 Adicione L no final
        ) {
            @Override
            public String getDisplayInfo() {
                return "🎒 Expansão de Inventário" +
                        "\n   Preço: " + getPrice() + " ouro" +
                        "\n   Estoque: ∞" +
                        "\n   Nível req: " + getPlayerLevelRequired() +
                        "\n   +5 slots de inventário";
            }
        });
        // 🔥 EXPANSÕES DE STASH
        shopItems.add(new ShopItem(
                new Gold(0), // Desbloquear Tab de Armas
                150L, 1, 5, ShopCategory.UPGRADES
        ) {
            @Override
            public String getDisplayInfo() {
                return "📂 Tab de Armas" +
                        "\n   Preço: " + getPrice() + " ouro" +
                        "\n   Estoque: " + getStock() +
                        "\n   Nível req: " + getPlayerLevelRequired() +
                        "\n   Desbloqueia tab especial para armas";
            }
        });

        shopItems.add(new ShopItem(
                new Gold(0), // Expansão de Tab
                100L, 3, 3, ShopCategory.UPGRADES
        ) {
            @Override
            public String getDisplayInfo() {
                return "📦 Expansão de Tab" +
                        "\n   Preço: " + getPrice() + " ouro" +
                        "\n   Estoque: " + getStock() +
                        "\n   Nível req: " + getPlayerLevelRequired() +
                        "\n   +10 slots em qualquer tab";
            }
        });
    }

    public void displayShopByCategory(ShopCategory category, int playerLevel, long playerGold) {
        // 🔥 Mudei o último parâmetro para long
        List<ShopItem> categoryItems = getItemsByCategory(category);

        System.out.println("\n🏪 === " + category.getEmoji() + " " + category.getDisplayName() + " === 🏪");

        if (categoryItems.isEmpty()) {
            System.out.println("   Nenhum item disponível nesta categoria.");
        } else {
            for (int i = 0; i < categoryItems.size(); i++) {
                ShopItem shopItem = categoryItems.get(i);
                String number = (i + 1) + ".";
                String canBuy = shopItem.canPurchase(playerLevel, playerGold) ? "✅" : "❌";

                System.out.println(canBuy + " " + number + " " + shopItem.getDisplayInfo());
                System.out.println();
            }
        }
        System.out.println("====================================\n");
    }

    public List<ShopItem> getItemsByCategory(ShopCategory category) {
        List<ShopItem> categoryItems = new ArrayList<>();
        for (ShopItem shopItem : shopItems) {
            if (shopItem.getCategory() == category) {
                categoryItems.add(shopItem);
            }
        }
        return categoryItems;
    }

    public ShopItem findItemByName(String itemName) {
        for (ShopItem shopItem : shopItems) {
            if (shopItem.getItem().getName().equalsIgnoreCase(itemName)) {
                return shopItem;
            }
        }
        return null;
    }

    public void displayShopByCategory(ShopCategory category, int playerLevel, int playerGold) {
        List<ShopItem> categoryItems = getItemsByCategory(category);

        System.out.println("\n🏪 === " + category.getEmoji() + " " + category.getDisplayName() + " === 🏪");

        if (categoryItems.isEmpty()) {
            System.out.println("   Nenhum item disponível nesta categoria.");
        } else {
            for (int i = 0; i < categoryItems.size(); i++) {
                ShopItem shopItem = categoryItems.get(i);
                String number = (i + 1) + ".";
                String canBuy = shopItem.canPurchase(playerLevel, playerGold) ? "✅" : "❌";

                System.out.println(canBuy + " " + number + " " + shopItem.getDisplayInfo());
                System.out.println();
            }
        }
        System.out.println("====================================\n");
    }

    public void displayShopMainMenu() {
        System.out.println("\n🏪 === LOJA DO MERCADOR === 🏪");
        for (ShopCategory category : ShopCategory.values()) {
            System.out.println(category.getEmoji() + " " + category.getDisplayName());
        }
        System.out.println("💾 Sair da Loja");
        System.out.println("==============================\n");
    }

    // Getters
    public List<ShopItem> getShopItems() { return shopItems; }
    public boolean isUnlocked() { return isUnlocked; }
}