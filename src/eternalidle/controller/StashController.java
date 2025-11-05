package eternalidle.controller;

import eternalidle.model.systems.GameManager;
import eternalidle.model.inventory.StashManager;
import eternalidle.model.inventory.StashTab;
import eternalidle.model.inventory.TabType;
import eternalidle.model.items.Item;
import eternalidle.model.items.equipment.Equipment;
import eternalidle.model.items.equipment.Weapon;
import eternalidle.model.items.equipment.Armor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

// 🔥 IMPORTS ADICIONADOS
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

public class StashController {

    private GameManager gameManager;
    private Stage stashStage;
    private StashManager stashManager;
    private Item selectedItem; // 🔥 VARIÁVEL ADICIONADA

    // Componentes da UI
    @FXML private Label stashTitle;
    @FXML private Label capacityLabel;
    @FXML private TabPane stashTabPane;
    @FXML private GridPane weaponsGrid;
    @FXML private GridPane armorGrid;
    @FXML private GridPane materialsGrid;

    // Abas
    @FXML private Tab weaponsTab;
    @FXML private Tab armorTab;
    @FXML private Tab materialsTab;

    @FXML
    private void initialize() {
        System.out.println("✅ StashController inicializado!");
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        if (gameManager != null && gameManager.getStashSystem() != null) {
            this.stashManager = gameManager.getStashSystem().getStashManager();
        }
        initializeStash();
    }

    public void setStashStage(Stage stage) {
        this.stashStage = stage;
    }

    private void initializeStash() {
        if (gameManager == null) return;

        if (stashTitle != null) {
            stashTitle.setText("Stash - " + gameManager.getPlayer().getName());
        }

        updateStashDisplay();
        setupTabListeners();
        System.out.println("✅ Stash visual inicializado!");
    }

    private void setupTabListeners() {
        if (stashTabPane != null) {
            stashTabPane.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldTab, newTab) -> {
                        if (newTab == weaponsTab) {
                            updateWeaponsTab();
                        } else if (newTab == armorTab) {
                            updateArmorTab();
                        } else if (newTab == materialsTab) {
                            updateMaterialsTab();
                        }
                    }
            );
        }
    }

    private void updateStashDisplay() {
        updateCapacityDisplay();
        updateWeaponsTab();
        updateArmorTab();
        updateMaterialsTab();
    }

    private void updateCapacityDisplay() {
        if (capacityLabel != null) {
            if (stashManager == null) {
                capacityLabel.setText("Capacidade: 0/60");
                capacityLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 14;");
                return;
            }

            int totalItems = getSafeTotalItemCount();
            int totalCapacity = getSafeTotalCapacity();
            capacityLabel.setText("Capacidade: " + totalItems + "/" + totalCapacity);

            if (totalItems >= totalCapacity) {
                capacityLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14;");
            } else if (totalItems >= totalCapacity * 0.8) {
                capacityLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-size: 14;");
            } else {
                capacityLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 14;");
            }
        }
    }

    // 🔥 MÉTODOS SEGUROS COM FALLBACK
    private int getSafeTotalItemCount() {
        if (stashManager == null) return 0;

        try {
            return stashManager.getTotalItemCount();
        } catch (Exception e) {
            int total = 0;
            if (stashManager.getTabs() != null) {
                for (StashTab tab : stashManager.getTabs()) {
                    if (tab != null && tab.getItems() != null) {
                        total += tab.getItems().size();
                    }
                }
            }
            return total;
        }
    }

    private int getSafeTotalCapacity() {
        if (stashManager == null) return 60;

        try {
            return stashManager.getTotalCapacity();
        } catch (Exception e) {
            return 60;
        }
    }

    private boolean isStashFull() {
        if (stashManager == null) return false;

        try {
            return stashManager.isFull();
        } catch (Exception e) {
            return getSafeTotalItemCount() >= getSafeTotalCapacity();
        }
    }

    private void updateWeaponsTab() {
        if (weaponsGrid == null) return;
        weaponsGrid.getChildren().clear();

        StashTab weaponsTab = getSafeTabByType(TabType.WEAPONS);
        if (weaponsTab == null || weaponsTab.getItems() == null || weaponsTab.getItems().isEmpty()) {
            showEmptyTabMessage(weaponsGrid, "Nenhuma arma armazenada");
            return;
        }

        displayItemsInGrid(weaponsTab.getItems(), weaponsGrid, TabType.WEAPONS);
    }

    private void updateArmorTab() {
        if (armorGrid == null) return;
        armorGrid.getChildren().clear();

        StashTab armorTab = getSafeTabByType(TabType.ARMOR);
        if (armorTab == null || armorTab.getItems() == null || armorTab.getItems().isEmpty()) {
            showEmptyTabMessage(armorGrid, "Nenhuma armadura armazenada");
            return;
        }

        displayItemsInGrid(armorTab.getItems(), armorGrid, TabType.ARMOR);
    }

    private void updateMaterialsTab() {
        if (materialsGrid == null) return;
        materialsGrid.getChildren().clear();

        StashTab materialsTab = getSafeTabByType(TabType.MATERIALS);
        if (materialsTab == null || materialsTab.getItems() == null || materialsTab.getItems().isEmpty()) {
            showEmptyTabMessage(materialsGrid, "Nenhum material armazenado");
            return;
        }

        displayItemsInGrid(materialsTab.getItems(), materialsGrid, TabType.MATERIALS);
    }

    private StashTab getSafeTabByType(TabType tabType) {
        if (stashManager == null) return null;

        try {
            return stashManager.getTabByType(tabType);
        } catch (Exception e) {
            return null;
        }
    }

    private void showEmptyTabMessage(GridPane grid, String message) {
        Label emptyLabel = new Label(message);
        emptyLabel.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 14; -fx-alignment: center;");
        grid.add(emptyLabel, 0, 0);
    }

    private void displayItemsInGrid(java.util.List<Item> items, GridPane grid, TabType tabType) {
        int col = 0;
        int row = 0;
        int maxCols = 5;

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            VBox itemSlot = createStashItemSlot(item, tabType);
            grid.add(itemSlot, col, row);

            col++;
            if (col >= maxCols) {
                col = 0;
                row++;
            }
        }
    }

    // 🔥 MÉTODO CORRIGIDO: createStashItemSlot com seleção
    private VBox createStashItemSlot(Item item, TabType tabType) {
        VBox slot = new VBox();

        // 🔥 DESTAQUE PARA ITEM SELECIONADO
        if (item.equals(selectedItem)) {
            slot.setStyle("-fx-background-color: #5a5a5a; -fx-border-color: #ffd700; -fx-border-width: 2; -fx-border-radius: 5; -fx-padding: 5;");
        } else {
            slot.setStyle("-fx-background-color: #4a4a4a; -fx-border-color: #666; -fx-border-radius: 5; -fx-padding: 5;");
        }

        slot.setPrefSize(60, 60);

        Label itemLabel = new Label(getItemEmoji(item));
        itemLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        Tooltip tooltip = new Tooltip(getItemTooltip(item) + "\n💰 Valor de venda: " + item.getSellValue() + " ouro");
        Tooltip.install(slot, tooltip);

        // 🔥 CLIQUE SIMPLES: Seleciona para venda
        slot.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectedItem = item;
                updateStashDisplay();
                showTempMessage("✅ " + item.getName() + " selecionado para venda");
            }
            // Clique duplo: retirar do stash (funcionalidade existente)
            else if (event.getClickCount() == 2) {
                withdrawItemFromStash(item, tabType);
            }
        });

        slot.getChildren().add(itemLabel);
        return slot;
    }

    private void onStashItemClicked(Item item, TabType tabType, MouseEvent event) {
        if (event.getClickCount() == 2) {
            withdrawItemFromStash(item, tabType);
        }
    }

    private void withdrawItemFromStash(Item item, TabType tabType) {
        if (gameManager == null) return;

        // Verificar se há espaço no inventário
        if (gameManager.getPlayerInventory().isFull()) {
            showTempMessage("❌ Inventário cheio! Não é possível retirar o item.");
            return;
        }

        // 🔥 REMOÇÃO SEGURA DO STASH
        boolean removed = false;
        if (stashManager != null) {
            try {
                removed = stashManager.removeItemFromTab(item, tabType);
            } catch (Exception e) {
                // Fallback: remoção manual
                StashTab tab = getSafeTabByType(tabType);
                if (tab != null && tab.getItems() != null) {
                    removed = tab.getItems().remove(item);
                }
            }
        }

        if (removed) {
            // Adicionar ao inventário
            boolean added = gameManager.getPlayerInventory().addItem(item);

            if (added) {
                showTempMessage("✅ " + item.getName() + " retirado do stash!");
                updateStashDisplay();
            } else {
                // Se não conseguiu adicionar ao inventário, tentar devolver ao stash
                if (stashManager != null) {
                    try {
                        stashManager.addItemToAppropriateTab(item);
                    } catch (Exception e) {
                        // Ignorar erro de devolução
                    }
                }
                showTempMessage("❌ Erro ao retirar item do stash.");
            }
        } else {
            showTempMessage("❌ Não foi possível retirar o item do stash.");
        }
    }

    // 🔥 MÉTODO NOVO: Vender item do stash
    @FXML
    private void handleSellFromStash() {
        if (selectedItem == null) {
            showTempMessage("❌ Nenhum item selecionado para venda!");
            return;
        }

        int sellValue = selectedItem.getSellValue();
        showSellConfirmation(selectedItem, sellValue);
    }

    // 🔥 MÉTODO NOVO: Confirmação de venda do stash
    private void showSellConfirmation(Item item, int sellValue) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Vender do Stash");
        confirmation.setHeaderText("Vender " + item.getName() + " do stash?");
        confirmation.setContentText("Valor da venda: " + sellValue + " 💰\n\n" +
                "Item será removido permanentemente!");

        ButtonType yesButton = new ButtonType("Vender", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(yesButton, noButton);

        confirmation.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                sellItemFromStash(item, sellValue);
            }
        });
    }

    // 🔥 MÉTODO NOVO: Executar venda do stash
    private void sellItemFromStash(Item item, int sellValue) {
        // Encontrar em qual tab está o item
        TabType itemTabType = determineTabType(item);

        // Remover do stash
        boolean removed = stashManager.removeItemFromTab(item, itemTabType);

        if (removed) {
            // Adicionar ouro ao jogador
            gameManager.getPlayer().addGold(sellValue);

            showTempMessage("💰 Vendido do stash: " + item.getName() + " por " + sellValue + " ouro!");
            selectedItem = null; // 🔥 CORRIGIDO: Limpar seleção
            updateStashDisplay();
        } else {
            showTempMessage("❌ Erro ao vender item do stash!");
        }
    }

    // 🔥 MÉTODO AUXILIAR: Determinar tab type do item
    private TabType determineTabType(Item item) {
        if (item instanceof Weapon) {
            return TabType.WEAPONS;
        } else if (item instanceof Armor) {
            return TabType.ARMOR;
        } else {
            return TabType.MATERIALS;
        }
    }

    @FXML
    private void handleDepositFromInventory() {
        if (gameManager == null) return;

        // Implementação básica - depositar primeiro item do inventário
        var inventory = gameManager.getPlayerInventory();
        if (inventory != null && !inventory.getItems().isEmpty()) {
            Item itemToDeposit = inventory.getItems().get(0);
            depositItemToStash(itemToDeposit);
        } else {
            showTempMessage("❌ Inventário vazio!");
        }
    }

    private void depositItemToStash(Item item) {
        if (isStashFull()) {
            showTempMessage("❌ Stash cheio! Não é possível depositar o item.");
            return;
        }

        // Remover do inventário
        boolean removedFromInventory = gameManager.getPlayerInventory().removeItem(item);

        if (removedFromInventory) {
            // Adicionar ao stash
            boolean addedToStash = false;
            if (stashManager != null) {
                try {
                    addedToStash = stashManager.addItemToAppropriateTab(item);
                } catch (Exception e) {
                    // Fallback: adicionar manualmente à tab apropriada
                    addedToStash = addItemToAppropriateTabManual(item);
                }
            }

            if (addedToStash) {
                showTempMessage("✅ " + item.getName() + " depositado no stash!");
                updateStashDisplay();
            } else {
                // Se não conseguiu adicionar ao stash, devolver ao inventário
                gameManager.getPlayerInventory().addItem(item);
                showTempMessage("❌ Erro ao depositar item no stash.");
            }
        }
    }

    // 🔥 FALLBACK: Adicionar item manualmente
    private boolean addItemToAppropriateTabManual(Item item) {
        if (stashManager == null || stashManager.getTabs() == null) return false;

        TabType tabType = determineTabType(item);
        for (StashTab tab : stashManager.getTabs()) {
            if (tab.getTabType() == tabType && !tab.isFull()) {
                return tab.getItems().add(item);
            }
        }
        return false;
    }

    @FXML
    private void handleOrganize() {
        if (stashManager != null) {
            try {
                stashManager.organizeAllTabs();
                showTempMessage("🔀 Stash organizado!");
            } catch (Exception e) {
                showTempMessage("🔀 Organização básica aplicada!");
            }
            updateStashDisplay();
        }
    }

    @FXML
    private void handleRefresh() {
        updateStashDisplay();
        showTempMessage("🔄 Stash atualizado");
    }

    @FXML
    private void handleClose() {
        if (stashStage != null) {
            stashStage.close();
        }
    }

    private void showTempMessage(String message) {
        if (stashTitle != null) {
            String originalText = stashTitle.getText();
            stashTitle.setText(message);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    javafx.application.Platform.runLater(() -> {
                        stashTitle.setText(originalText);
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    private String getItemEmoji(Item item) {
        if (item instanceof Weapon) {
            return "⚔️";
        } else if (item instanceof Armor) {
            return "🛡️";
        } else {
            return "📦";
        }
    }

    private String getItemTooltip(Item item) {
        StringBuilder tooltip = new StringBuilder();
        tooltip.append(item.getName()).append("\n");
        tooltip.append("Tipo: ").append(item.getClass().getSimpleName()).append("\n");
        tooltip.append("Raridade: ").append(item.getRarity()).append("\n");

        if (item instanceof Equipment) {
            Equipment equip = (Equipment) item;
            tooltip.append("Nível req: ").append(equip.getRequiredLevel()).append("\n");

            if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;
                tooltip.append("Dano: ").append(weapon.getDamage()).append("\n");
                tooltip.append("DPS: ").append(String.format("%.1f", weapon.getDPS()));
            } else if (item instanceof Armor) {
                Armor armor = (Armor) item;
                tooltip.append("Defesa: ").append(armor.getDefense());
            }
        }

        tooltip.append("\n\n🔧 Clique duplo para retirar");
        return tooltip.toString();
    }
}