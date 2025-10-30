package EternalIdle.model.systems;

import EternalIdle.model.items.currency.Gold;
import EternalIdle.model.shop.Shop;
import EternalIdle.model.shop.ShopItem;
import EternalIdle.model.shop.ShopCategory;
import EternalIdle.model.entity.Player;
import EternalIdle.model.inventory.Inventory;
import EternalIdle.model.items.Item;
import EternalIdle.model.items.equipment.Equipment;
import EternalIdle.model.inventory.TabType;
import EternalIdle.model.statistics.PlayerStatistics;
import java.util.Scanner;

public class ShopSystem {
    private Shop shop;
    private Scanner scanner;
    private StashSystem stashSystem;
    private PlayerStatistics playerStatistics;

    public ShopSystem() {
        this.shop = new Shop();
        this.scanner = new Scanner(System.in);
        this.stashSystem = new StashSystem();
        this.playerStatistics = null;
    }

    public ShopSystem(StashSystem stashSystem) {
        this.shop = new Shop();
        this.scanner = new Scanner(System.in);
        this.stashSystem = stashSystem;
        this.playerStatistics = null;
    }

    public ShopSystem(StashSystem stashSystem, PlayerStatistics playerStatistics) {
        this.shop = new Shop();
        this.scanner = new Scanner(System.in);
        this.stashSystem = stashSystem;
        this.playerStatistics = playerStatistics;
    }

    public void openShop(Player player, Inventory inventory) {
        System.out.println("🔔 Bem-vindo à Loja!");
        System.out.println("Seu ouro: " + player.getGold());

        boolean inShop = true;

        while (inShop) {
            shop.displayShopMainMenu();
            System.out.print("Escolha uma categoria ou 'sair': ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("sair") || input.equals("6")) {
                System.out.println("👋 Até mais!");
                inShop = false;
                continue;
            }

            ShopCategory selectedCategory = parseCategoryInput(input);
            if (selectedCategory != null) {
                browseCategory(selectedCategory, player, inventory);
            } else {
                System.out.println("❌ Categoria inválida!");
            }
        }
    }

    private void browseCategory(ShopCategory category, Player player, Inventory inventory) {
        boolean inCategory = true;

        while (inCategory) {
            shop.displayShopByCategory(category, player.getLevel(), player.getGold());
            System.out.println("Seu ouro: " + player.getGold());
            System.out.print("Digite o número do item para comprar ou 'voltar': ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("voltar")) {
                inCategory = false;
                continue;
            }

            try {
                int itemIndex = Integer.parseInt(input) - 1;
                purchaseItem(category, itemIndex, player, inventory);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida!");
            }
        }
    }

    private void purchaseItem(ShopCategory category, int itemIndex, Player player, Inventory inventory) {
        var categoryItems = shop.getItemsByCategory(category);

        if (itemIndex < 0 || itemIndex >= categoryItems.size()) {
            System.out.println("❌ Item não encontrado!");
            return;
        }

        ShopItem shopItem = categoryItems.get(itemIndex);

        if (!shopItem.canPurchase(player.getLevel(), player.getGold())) {
            System.out.println("❌ Não é possível comprar este item!");
            System.out.println("   Verifique nível requerido e ouro disponível.");
            return;
        }

        // Processar compra
        Item item = shopItem.getItem();

        // 🔥 ITENS ESPECIAIS
        if (category == ShopCategory.UPGRADES && item instanceof Gold) {
            handleSpecialUpgrades(player, inventory, shopItem);
            return;
        }

        // Compra normal
        if (inventory.addItem(item)) {
            // Deduzir ouro
            player.addGold(-shopItem.getPrice());
            shopItem.purchase();

            // 🔥 ATUALIZAR ESTATÍSTICAS SE DISPONÍVEL
            if (playerStatistics != null) {
                playerStatistics.addGoldSpent(shopItem.getPrice());
            }

            System.out.println("🎉 Compra realizada com sucesso!");
            System.out.println("✅ " + item.getName() + " adicionado ao inventário!");
            System.out.println("💰 Ouro gasto: " + shopItem.getPrice());
            System.out.println("💰 Ouro restante: " + player.getGold());

            // Equipar automaticamente se for equipamento
            if (item instanceof Equipment) {
                Equipment equipment = (Equipment) item;
                if (equipment.canEquip(player.getLevel())) {
                    if (player.equipItem(equipment)) {
                        System.out.println("⚔️ Equipado automaticamente: " + equipment.getName());
                    }
                }
            }
        } else {
            System.out.println("❌ Não foi possível adicionar o item ao inventário!");
        }
    }

    // 🔥 MÉTODO ÚNICO handleSpecialUpgrades - SEM DUPLICAÇÃO!
    private void handleSpecialUpgrades(Player player, Inventory inventory, ShopItem shopItem) {
        long price = shopItem.getPrice();

        if (price == 200L) {
            handleInventoryUpgrade(player, inventory, shopItem);
        } else if (price == 150L) {
            if (stashSystem.unlockTab(TabType.WEAPONS)) {
                completePurchase(player, shopItem, "Tab de Armas desbloqueada!");
            } else {
                System.out.println("❌ Esta tab já está desbloqueada!");
            }
        } else if (price == 100L) {
            if (stashSystem.upgradeTab(TabType.ALL, 10)) {
                completePurchase(player, shopItem, "Tab expandida em +10 slots!");
            } else {
                System.out.println("❌ Não foi possível expandir a tab!");
            }
        } else if (price == 80L) {
            System.out.println("🎒 Kit de materiais recebido!");
            completePurchase(player, shopItem, "Kit de materiais adquirido!");
        } else {
            System.out.println("❌ Item especial não reconhecido!");
        }
    }

    // 🔥 MÉTODO AUXILIAR PARA COMPRAS
    private void completePurchase(Player player, ShopItem shopItem, String message) {
        player.addGold(-shopItem.getPrice());
        shopItem.purchase();
        if (playerStatistics != null) {
            playerStatistics.addGoldSpent(shopItem.getPrice());
        }
        System.out.println("🎉 " + message);
        System.out.println("💰 Ouro gasto: " + shopItem.getPrice());
        System.out.println("💰 Ouro restante: " + player.getGold());
    }

    private void handleInventoryUpgrade(Player player, Inventory inventory, ShopItem shopItem) {
        int newCapacity = inventory.getCapacity() + 5;
        inventory.setCapacity(newCapacity);

        player.addGold(-shopItem.getPrice());
        shopItem.purchase();
        if (playerStatistics != null) {
            playerStatistics.addGoldSpent(shopItem.getPrice());
        }

        System.out.println("🎉 Inventário expandido!");
        System.out.println("📦 Capacidade: " + (newCapacity - 5) + " → " + newCapacity);
        System.out.println("💰 Ouro gasto: " + shopItem.getPrice());
        System.out.println("💰 Ouro restante: " + player.getGold());
    }

    private ShopCategory parseCategoryInput(String input) {
        return switch(input) {
            case "1", "armas", "arma" -> ShopCategory.WEAPONS;
            case "2", "armaduras", "armadura" -> ShopCategory.ARMOR;
            case "3", "poções", "poção" -> ShopCategory.POTIONS;
            case "4", "materiais", "material" -> ShopCategory.MATERIALS;
            case "5", "melhorias", "melhoria" -> ShopCategory.UPGRADES;
            default -> null;
        };
    }

    // Método simples para compra rápida (para teste automático)
    public boolean quickPurchase(Player player, Inventory inventory, String itemName) {
        ShopItem shopItem = shop.findItemByName(itemName);
        if (shopItem != null && shopItem.canPurchase(player.getLevel(), player.getGold())) {
            if (inventory.addItem(shopItem.getItem())) {
                player.addGold(-shopItem.getPrice());
                shopItem.purchase();
                if (playerStatistics != null) {
                    playerStatistics.addGoldSpent(shopItem.getPrice());
                }
                System.out.println("🛒 Compra rápida: " + itemName);
                return true;
            }
        }
        return false;
    }

    // 🔥 Getter para o stash system
    public StashSystem getStashSystem() {
        return stashSystem;
    }
}