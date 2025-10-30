package eternalidle.controller;

import eternalidle.model.systems.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShopController {

    private GameManager gameManager;
    private Stage shopStage;

    // 🔥 COMPONENTES FXML
    @FXML private Label shopTitle;
    @FXML private Label goldLabel;
    @FXML private GridPane itemsGrid;

    // 🔥 MÉTODO OBRIGATÓRIO: é chamado automaticamente pelo FXML
    @FXML
    private void initialize() {
        System.out.println("✅ ShopController.initialize() chamado pelo FXML");

        // Inicialização básica
        if (shopTitle != null) {
            shopTitle.setText("Loja - Eternal Idle");
        }
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        System.out.println("✅ GameManager setado na loja");

        // Atualizar UI depois que GameManager estiver disponível
        updateSimpleUI();
    }

    public void setShopStage(Stage stage) {
        this.shopStage = stage;
        System.out.println("✅ Stage setado na loja");
    }

    private void updateSimpleUI() {
        System.out.println("🎪 Atualizando UI simples da loja");

        // Apenas mostra ouro básico
        if (goldLabel != null && gameManager != null) {
            goldLabel.setText("Ouro: " + gameManager.getPlayer().getGold());
        }

        // Apenas 2 itens de teste
        if (itemsGrid != null) {
            itemsGrid.getChildren().clear();

            // Item 1
            VBox item1 = createUltraSimpleItem("⚔️", 100);
            itemsGrid.add(item1, 0, 0);

            // Item 2
            VBox item2 = createUltraSimpleItem("🛡️", 80);
            itemsGrid.add(item2, 1, 0);
        }

        System.out.println("✅ UI simples atualizada");
    }

    // 🔥 ITEM ULTRA SIMPLES
    private VBox createUltraSimpleItem(String emoji, int price) {
        VBox slot = new VBox();
        slot.setStyle("-fx-background-color: #4a4a4a; -fx-border-color: #666; -fx-padding: 10; -fx-alignment: center;");
        slot.setPrefSize(60, 60);

        Label emojiLabel = new Label(emoji);
        emojiLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        Label priceLabel = new Label(price + "G");
        priceLabel.setStyle("-fx-text-fill: gold; -fx-font-size: 10;");

        // 🔥 CLIQUE DIRETO - SEM PROCESSAMENTO
        slot.setOnMouseClicked(e -> {
            System.out.println("🛒 Clicou em: " + emoji);
            showTempMessage("Clicou: " + emoji);
        });

        slot.getChildren().addAll(emojiLabel, priceLabel);
        return slot;
    }

    // 🔥 MÉTODOS FXML - devem existir e serem públicos/package-private
    @FXML
    private void handleRefresh() {
        System.out.println("🔁 Refresh simples");
        showTempMessage("Atualizado!");
        updateSimpleUI();
    }

    @FXML
    private void handleClose() {
        System.out.println("❌ Fechando loja");
        if (shopStage != null) {
            shopStage.close();
        }
    }

    // 🔥 MENSAGEM SIMPLES
    private void showTempMessage(String message) {
        if (shopTitle != null) {
            shopTitle.setText(message);

            // Volta após 1 segundo
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    javafx.application.Platform.runLater(() -> {
                        shopTitle.setText("Loja - Eternal Idle");
                    });
                } catch (InterruptedException e) {
                    // Ignora
                }
            }).start();
        }
    }
}







//package eternalidle.controller;
//
//import eternalidle.model.systems.GameManager;
//import eternalidle.model.systems.ShopSystem;
//import eternalidle.model.shop.ShopCategory;
//import eternalidle.model.shop.ShopItem;
//import eternalidle.model.items.Item;
//import eternalidle.model.items.ItemRarity;
//import eternalidle.model.items.equipment.Equipment;
//import eternalidle.model.items.equipment.Weapon;
//import eternalidle.model.items.equipment.Armor;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.Button;
//import javafx.scene.control.Tooltip;
//import javafx.scene.layout.VBox;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.GridPane;
//import javafx.scene.control.ScrollPane;
//import javafx.stage.Stage;
//import javafx.application.Platform;
//import java.util.List;
//import java.util.ArrayList;
//
//public class ShopController {
//
//    private GameManager gameManager;
//    private Stage shopStage;
//    private ShopSystem shopSystem;
//    private ShopCategory currentCategory;
//
//    // Componentes da UI
//    @FXML private Label shopTitle;
//    @FXML private Label goldLabel;
//    @FXML private Label categoryTitle;
//    @FXML private ScrollPane itemsScrollPane;
//    @FXML private GridPane itemsGrid;
//    @FXML private VBox itemPreview;
//    @FXML private Label previewName;
//    @FXML private Label previewType;
//    @FXML private Label previewRarity;
//    @FXML private Label previewLevel;
//    @FXML private Label previewStock;
//    @FXML private Label previewStats;
//    @FXML private Label previewPrice;
//    @FXML private Button buyButton;
//
//    // Botões de categoria
//    @FXML private Button weaponsButton;
//    @FXML private Button armorButton;
//    @FXML private Button potionsButton;
//    @FXML private Button materialsButton;
//    @FXML private Button upgradesButton;
//
//    // Indicador de carregamento
//    @FXML private Label loadingLabel;
//
//    // 🔥 CLASSE TEMPORÁRIA PARA ITENS DA LOJA - COM use() void
//    private static class ShopItemTemp extends Item {
//        private String customDisplayName;
//
//        public ShopItemTemp(String name, String description, int value, ItemRarity rarity, String customDisplayName) {
//            super(name, description, value, rarity);
//            this.customDisplayName = customDisplayName;
//        }
//
//        @Override
//        public String getDisplayName() {
//            return customDisplayName != null ? customDisplayName : getName();
//        }
//
//        // 🔥 CORREÇÃO: use() void (sem retorno boolean)
//        @Override
//        public void use() {
//            System.out.println("Usando item da loja: " + getName());
//            // Não precisa de return pois é void
//        }
//    }
//
//    public void setGameManager(GameManager gameManager) {
//        this.gameManager = gameManager;
//
//        // 🔥 USAR O SHOP SYSTEM REAL DO GAME MANAGER
//        if (gameManager.getShopSystem() != null) {
//            this.shopSystem = gameManager.getShopSystem();
//        } else {
//            // Fallback: criar um novo se não existir
//            this.shopSystem = new ShopSystem(gameManager.getStashSystem());
//        }
//
//        initializeShop();
//    }
//
//    public void setShopStage(Stage stage) {
//        this.shopStage = stage;
//    }
//
//    @FXML
//    private void initialize() {
//        System.out.println("✅ ShopController inicializado!");
//        setupCategoryButtons();
//
//        // Inicialização segura
//        if (loadingLabel != null) {
//            loadingLabel.setVisible(false);
//        }
//        if (itemPreview != null) {
//            itemPreview.setVisible(false);
//        }
//    }
//
//    private void initializeShop() {
//        if (gameManager == null) return;
//
//        updateGoldDisplay();
//
//        // Carregamento assíncrono da categoria padrão
//        loadCategoryAsync(ShopCategory.WEAPONS);
//
//        if (shopTitle != null) {
//            shopTitle.setText("Loja - Eternal Idle");
//        }
//    }
//
//    private void setupCategoryButtons() {
//        setupCategoryButton(weaponsButton, ShopCategory.WEAPONS);
//        setupCategoryButton(armorButton, ShopCategory.ARMOR);
//        setupCategoryButton(potionsButton, ShopCategory.POTIONS);
//        setupCategoryButton(materialsButton, ShopCategory.MATERIALS);
//        setupCategoryButton(upgradesButton, ShopCategory.UPGRADES);
//    }
//
//    private void setupCategoryButton(Button button, ShopCategory category) {
//        if (button != null) {
//            String tooltipText = getCategoryTooltip(category);
//            Tooltip tooltip = new Tooltip(tooltipText);
//            Tooltip.install(button, tooltip);
//
//            button.setOnAction(e -> selectCategory(category));
//        }
//    }
//
//    private String getCategoryTooltip(ShopCategory category) {
//        return switch(category) {
//            case WEAPONS -> "⚔️ Armas\nCompre armas para aumentar seu dano";
//            case ARMOR -> "🛡️ Armaduras\nAumente sua defesa";
//            case POTIONS -> "🧪 Poções\nRecupere saúde e ganhe bônus";
//            case MATERIALS -> "📦 Materiais\nItens para crafting";
//            case UPGRADES -> "✨ Melhorias\nExpanda seu inventário e stash";
//        };
//    }
//
//    private void selectCategory(ShopCategory category) {
//        this.currentCategory = category;
//        updateCategoryDisplay();
//        loadCategoryAsync(category);
//        clearPreview();
//    }
//
//    // 🔥 MÉTODO ASSÍNCRONO PARA CARREGAR CATEGORIA
//    private void loadCategoryAsync(ShopCategory category) {
//        showLoading(true);
//
//        new Thread(() -> {
//            try {
//                // Pequeno delay para não travar a UI
//                Thread.sleep(50);
//
//                // 🔥 AGORA USA O SHOP SYSTEM REAL
//                List<ShopItem> items = getRealShopItemsByCategory(category);
//
//                Platform.runLater(() -> {
//                    updateItemsGrid(items);
//                    showLoading(false);
//                });
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Platform.runLater(() -> {
//                    showLoading(false);
//                    showTempMessage("❌ Erro ao carregar itens: " + e.getMessage());
//                });
//            }
//        }).start();
//    }
//
//    // 🔥 MÉTODO ATUALIZADO: Usa o ShopSystem real
//    private List<ShopItem> getRealShopItemsByCategory(ShopCategory category) {
//        List<ShopItem> items = new ArrayList<>();
//
//        try {
//            // 🔥 INTEGRAÇÃO COM SEU SHOP SYSTEM REAL
//            // Se o método getItemsByCategory existir no ShopSystem, use:
//            if (shopSystem != null) {
//                // items = shopSystem.getItemsByCategory(category);
//
//                // 🔥 TEMPORÁRIO: Criar itens de exemplo usando sua estrutura real
//                items = createSampleShopItems(category);
//            }
//
//        } catch (Exception e) {
//            System.err.println("❌ Erro ao obter itens da categoria: " + e.getMessage());
//        }
//
//        return items;
//    }
//
//    // 🔥 MÉTODO PARA CRIAR ITENS DE EXEMPLO USANDO SUA ESTRUTURA
//    private List<ShopItem> createSampleShopItems(ShopCategory category) {
//        List<ShopItem> items = new ArrayList<>();
//
//        // 🔥 EXEMPLOS BASEADOS NA SUA ESTRUTURA REAL
//        switch (category) {
//            case WEAPONS:
//                items.add(new ShopItem(
//                        createSampleWeapon("Espada de Ferro", 15),
//                        100L, 5, 1, ShopCategory.WEAPONS
//                ));
//                items.add(new ShopItem(
//                        createSampleWeapon("Machado de Batalha", 25),
//                        250L, 3, 3, ShopCategory.WEAPONS
//                ));
//                items.add(new ShopItem(
//                        createSampleWeapon("Arco Longo", 20),
//                        180L, 4, 2, ShopCategory.WEAPONS
//                ));
//                break;
//
//            case ARMOR:
//                items.add(new ShopItem(
//                        createSampleArmor("Armadura de Couro", 10),
//                        80L, 5, 1, ShopCategory.ARMOR
//                ));
//                items.add(new ShopItem(
//                        createSampleArmor("Elmo de Aço", 15),
//                        150L, 3, 3, ShopCategory.ARMOR
//                ));
//                break;
//
//            case UPGRADES:
//                items.add(new ShopItem(
//                        createSampleUpgrade("Expansão de Inventário"),
//                        200L, 1, 1, ShopCategory.UPGRADES
//                ));
//                items.add(new ShopItem(
//                        createSampleUpgrade("Tab de Armas"),
//                        150L, 1, 1, ShopCategory.UPGRADES
//                ));
//                break;
//
//            default:
//                // Para outras categorias, criar itens básicos
//                for (int i = 1; i <= 4; i++) {
//                    items.add(new ShopItem(
//                            createSampleItem(category.name() + " " + i),
//                            50L * i, 10, 1, category
//                    ));
//                }
//                break;
//        }
//
//        return items;
//    }
//
//    // 🔥 MÉTODOS AUXILIARES PARA CRIAR ITENS DE EXEMPLO - CORRIGIDOS
//    private Item createSampleWeapon(String name, int damage) {
//        return new ShopItemTemp(
//                name,
//                "Arma básica para combate",
//                damage,
//                ItemRarity.COMMON, // 🔥 CORREÇÃO: Usar COMMON em vez de SPECIAL
//                name + " (Dano: " + damage + ")"
//        );
//    }
//
//    private Item createSampleArmor(String name, int defense) {
//        return new ShopItemTemp(
//                name,
//                "Proteção básica para aventuras",
//                defense,
//                ItemRarity.COMMON, // 🔥 CORREÇÃO: Usar COMMON em vez de SPECIAL
//                name + " (Defesa: " + defense + ")"
//        );
//    }
//
//    private Item createSampleUpgrade(String name) {
//        return new ShopItemTemp(
//                name,
//                "Melhoria permanente para seu personagem",
//                0,
//                ItemRarity.RARE, // 🔥 CORREÇÃO: Usar RARE ou COMMON em vez de SPECIAL
//                name
//        );
//    }
//
//    private Item createSampleItem(String name) {
//        return new ShopItemTemp(
//                name,
//                "Item útil para suas aventuras",
//                1,
//                ItemRarity.COMMON, // 🔥 CORREÇÃO: Usar COMMON em vez de SPECIAL
//                name
//        );
//    }
//
//    private void showLoading(boolean loading) {
//        if (loadingLabel != null) {
//            loadingLabel.setVisible(loading);
//        }
//        if (itemsGrid != null) {
//            itemsGrid.setVisible(!loading);
//        }
//    }
//
//    private void updateCategoryDisplay() {
//        if (categoryTitle != null) {
//            String categoryName = getCategoryDisplayName(currentCategory);
//            categoryTitle.setText(categoryName);
//        }
//        updateCategoryButtons();
//    }
//
//    private void updateCategoryButtons() {
//        String[] buttonStyles = {"category-selected"};
//
//        if (weaponsButton != null) weaponsButton.getStyleClass().removeAll(buttonStyles);
//        if (armorButton != null) armorButton.getStyleClass().removeAll(buttonStyles);
//        if (potionsButton != null) potionsButton.getStyleClass().removeAll(buttonStyles);
//        if (materialsButton != null) materialsButton.getStyleClass().removeAll(buttonStyles);
//        if (upgradesButton != null) upgradesButton.getStyleClass().removeAll(buttonStyles);
//
//        if (currentCategory != null) {
//            switch (currentCategory) {
//                case WEAPONS -> { if (weaponsButton != null) weaponsButton.getStyleClass().add("category-selected"); }
//                case ARMOR -> { if (armorButton != null) armorButton.getStyleClass().add("category-selected"); }
//                case POTIONS -> { if (potionsButton != null) potionsButton.getStyleClass().add("category-selected"); }
//                case MATERIALS -> { if (materialsButton != null) materialsButton.getStyleClass().add("category-selected"); }
//                case UPGRADES -> { if (upgradesButton != null) upgradesButton.getStyleClass().add("category-selected"); }
//            }
//        }
//    }
//
//    private String getCategoryDisplayName(ShopCategory category) {
//        return switch(category) {
//            case WEAPONS -> "⚔️ Armas";
//            case ARMOR -> "🛡️ Armaduras";
//            case POTIONS -> "🧪 Poções";
//            case MATERIALS -> "📦 Materiais";
//            case UPGRADES -> "✨ Melhorias";
//        };
//    }
//
//    private void updateItemsGrid(List<ShopItem> categoryItems) {
//        if (itemsGrid == null) return;
//
//        itemsGrid.getChildren().clear();
//
//        if (categoryItems == null || categoryItems.isEmpty()) {
//            Label emptyLabel = new Label("Nenhum item disponível nesta categoria");
//            emptyLabel.setStyle("-fx-text-fill: #777; -fx-font-size: 14;");
//            itemsGrid.add(emptyLabel, 0, 0);
//            return;
//        }
//
//        int col = 0;
//        int row = 0;
//        int maxCols = 4;
//
//        for (int i = 0; i < categoryItems.size(); i++) {
//            ShopItem shopItem = categoryItems.get(i);
//            VBox itemSlot = createShopItemSlot(shopItem, i);
//            itemsGrid.add(itemSlot, col, row);
//
//            col++;
//            if (col >= maxCols) {
//                col = 0;
//                row++;
//            }
//        }
//    }
//
//    private VBox createShopItemSlot(ShopItem shopItem, int index) {
//        VBox slot = new VBox();
//        slot.getStyleClass().add("shop-item-slot");
//        slot.setPrefSize(80, 80);
//        slot.setStyle("-fx-background-color: #4a4a4a; -fx-border-color: #666; -fx-border-radius: 5; -fx-padding: 10; -fx-alignment: center;");
//
//        Item item = shopItem.getItem();
//        Label itemLabel = new Label(getItemEmoji(item));
//        itemLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
//
//        Label priceLabel = new Label(shopItem.getPrice() + "💰");
//        priceLabel.setStyle("-fx-text-fill: gold; -fx-font-size: 10;");
//
//        // 🔥 TOOLTIP MELHORADA com suas informações reais
//        Tooltip tooltip = new Tooltip(getShopItemTooltip(shopItem));
//        Tooltip.install(slot, tooltip);
//
//        // Evento de clique para preview
//        slot.setOnMouseClicked(e -> showItemPreview(shopItem));
//
//        slot.getChildren().addAll(itemLabel, priceLabel);
//        return slot;
//    }
//
//    private String getItemEmoji(Item item) {
//        return switch(currentCategory) {
//            case WEAPONS -> "⚔️";
//            case ARMOR -> "🛡️";
//            case POTIONS -> "🧪";
//            case MATERIALS -> "📦";
//            case UPGRADES -> "✨";
//        };
//    }
//
//    // 🔥 TOOLTIP ATUALIZADA com suas informações reais
//    private String getShopItemTooltip(ShopItem shopItem) {
//        StringBuilder tooltip = new StringBuilder();
//
//        tooltip.append("🏷️ ").append(shopItem.getItem().getDisplayName()).append("\n");
//        tooltip.append("💰 Preço: ").append(shopItem.getPrice()).append(" ouro\n");
//        tooltip.append("📦 Estoque: ").append(shopItem.getStock() == -1 ? "Ilimitado" : shopItem.getStock()).append("\n");
//        tooltip.append("⭐ Nível req: ").append(shopItem.getPlayerLevelRequired()).append("\n");
//        tooltip.append("📋 ").append(shopItem.getItem().getDescription());
//
//        return tooltip.toString();
//    }
//
//    // 🔥 PREVIEW ATUALIZADO com suas informações reais
//    private void showItemPreview(ShopItem shopItem) {
//        if (itemPreview == null) return;
//
//        Item item = shopItem.getItem();
//
//        previewName.setText(item.getDisplayName());
//        previewType.setText("Categoria: " + shopItem.getCategory());
//        previewRarity.setText("Raridade: " + item.getRarity());
//        previewLevel.setText("Nível req: " + shopItem.getPlayerLevelRequired());
//        previewStock.setText("Estoque: " + (shopItem.getStock() == -1 ? "Ilimitado" : shopItem.getStock()));
//        previewPrice.setText("Preço: " + shopItem.getPrice() + " 💰");
//        previewStats.setText(item.getDescription());
//
//        // 🔥 VERIFICAÇÃO DE COMPRA ATUALIZADA
//        boolean canBuy = canPurchaseItem(shopItem);
//
//        buyButton.setDisable(!canBuy);
//        buyButton.setOnAction(e -> purchaseItem(shopItem));
//
//        itemPreview.setVisible(true);
//    }
//
//    // 🔥 VERIFICAÇÃO DE COMPRA ATUALIZADA
//    private boolean canPurchaseItem(ShopItem shopItem) {
//        if (gameManager == null) return false;
//
//        return shopItem.canPurchase(
//                gameManager.getPlayer().getLevel(),
//                gameManager.getPlayer().getGold()
//        );
//    }
//
//    private void clearPreview() {
//        if (itemPreview != null) {
//            itemPreview.setVisible(false);
//        }
//    }
//
//    // 🔥 COMPRA ATUALIZADA para usar seu sistema real
//    private void purchaseItem(ShopItem shopItem) {
//        if (gameManager == null || shopSystem == null) return;
//
//        new Thread(() -> {
//            try {
//                boolean success = false;
//
//                // 🔥 AGORA USA O MÉTODO REAL DO SEU SHOP SYSTEM
//                // success = shopSystem.quickPurchase(
//                //     gameManager.getPlayer(),
//                //     gameManager.getPlayerInventory(),
//                //     shopItem.getItem().getName()
//                // );
//
//                // 🔥 TEMPORÁRIO: Lógica de compra simplificada
//                if (canPurchaseItem(shopItem)) {
//                    // Deduz ouro
//                    gameManager.getPlayer().addGold(-shopItem.getPrice());
//
//                    // Adiciona ao inventário
//                    boolean added = gameManager.getPlayerInventory().addItem(shopItem.getItem());
//
//                    if (added) {
//                        shopItem.purchase(); // Atualiza estoque
//                        success = true;
//                    }
//                }
//
//                final boolean finalSuccess = success;
//
//                Platform.runLater(() -> {
//                    if (finalSuccess) {
//                        updateGoldDisplay();
//                        showTempMessage("✅ " + shopItem.getItem().getDisplayName() + " comprado!");
//                        // Atualiza o preview se ainda estiver visível
//                        showItemPreview(shopItem);
//                    } else {
//                        showTempMessage("❌ Não foi possível completar a compra!");
//                    }
//                });
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Platform.runLater(() -> {
//                    showTempMessage("❌ Erro durante a compra: " + e.getMessage());
//                });
//            }
//        }).start();
//    }
//
//    private void updateGoldDisplay() {
//        if (goldLabel != null && gameManager != null) {
//            Platform.runLater(() -> {
//                goldLabel.setText(gameManager.getPlayer().getGold() + " 💰");
//            });
//        }
//    }
//
//    @FXML
//    private void handleRefresh() {
//        updateGoldDisplay();
//        if (currentCategory != null) {
//            loadCategoryAsync(currentCategory);
//        }
//        showTempMessage("🔄 Loja atualizada");
//    }
//
//    @FXML
//    private void handleClose() {
//        if (shopStage != null) {
//            shopStage.close();
//        }
//        System.out.println("🏪 Loja fechada");
//    }
//
//    private void showTempMessage(String message) {
//        if (shopTitle != null) {
//            String originalText = shopTitle.getText();
//            shopTitle.setText(message);
//
//            new Thread(() -> {
//                try {
//                    Thread.sleep(2000);
//                    Platform.runLater(() -> {
//                        shopTitle.setText(originalText);
//                    });
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }).start();
//        }
//    }
//}