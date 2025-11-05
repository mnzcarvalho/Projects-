package eternalidle.controller;

import eternalidle.model.systems.GameManager;
import eternalidle.model.inventory.Inventory;
import eternalidle.model.items.Item;
import eternalidle.model.items.ItemRarity; // 🔥 IMPORT ADICIONADO
import eternalidle.model.items.equipment.Equipment;
import eternalidle.model.items.equipment.Weapon;
import eternalidle.model.items.equipment.Armor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

// 🔥 IMPORTS PARA ALERTAS E COLETORES
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class InventoryController {

    private GameManager gameManager;
    private Stage inventoryStage;
    private Item selectedItem; // 🔥 CORRIGIDO: selectedItem (não selectedltem)

    // Componentes da UI do inventário
    @FXML private Label inventoryTitle;
    @FXML private Label capacityLabel;
    @FXML private GridPane inventoryGrid;
    @FXML private VBox equipmentSlots;
    @FXML private Button closeButton;

    // Slots de equipamento
    @FXML private VBox weaponSlot;
    @FXML private VBox helmetSlot;
    @FXML private VBox chestSlot;
    @FXML private VBox glovesSlot;
    @FXML private VBox bootsSlot;

    // Labels de equipamento
    @FXML private Label weaponLabel;
    @FXML private Label helmetLabel;
    @FXML private Label chestLabel;
    @FXML private Label glovesLabel;
    @FXML private Label bootsLabel;

    // Botões de desequipar
    @FXML private Button unequipWeaponButton;
    @FXML private Button unequipHelmetButton;
    @FXML private Button unequipChestButton;
    @FXML private Button unequipGlovesButton;
    @FXML private Button unequipBootsButton;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        initializeInventory();
    }

    public void setInventoryStage(Stage stage) {
        this.inventoryStage = stage;
    }

    @FXML
    private void initialize() {
        System.out.println("✅ InventoryController inicializado!");
    }

    private void initializeInventory() {
        if (gameManager == null) return;

        updateInventoryDisplay();
        setupEquipmentSlots();

        if (inventoryTitle != null) {
            inventoryTitle.setText("Inventário de " + gameManager.getPlayer().getName());
        }
    }

    // 🔥 ATUALIZE O MÉTODO updateInventoryDisplay
    private void updateInventoryDisplay() {
        if (gameManager == null) return;

        Inventory inventory = gameManager.getPlayerInventory();

        if (inventory == null) {
            System.out.println("❌ Inventory não encontrado");
            return;
        }

        // 🔥 ATUALIZAR CAPACIDADE COM CORES
        if (capacityLabel != null) {
            int currentSize = inventory.getItems().size();
            int capacity = inventory.getCapacity();
            capacityLabel.setText("Capacidade: " + currentSize + "/" + capacity);

            // Mudar cor baseado na capacidade
            if (currentSize >= capacity) {
                capacityLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14; -fx-font-weight: bold;");
            } else if (currentSize >= capacity * 0.8) {
                capacityLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-size: 14; -fx-font-weight: bold;");
            } else {
                capacityLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 14;");
            }
        }

        // 🔥 ATUALIZAR INFO DO ITEM SELECIONADO
        updateSelectedItemInfo();

        // Limpar grid
        inventoryGrid.getChildren().clear();

        // 🔥 VERIFICAR SE INVENTÁRIO ESTÁ CHEIO
        if (inventory.isFull()) {
            showInventoryFullWarning();
        }

        // Adicionar itens ao grid
        int col = 0;
        int row = 0;
        int maxCols = 5;

        for (int i = 0; i < inventory.getItems().size(); i++) {
            Item item = inventory.getItems().get(i);
            VBox itemSlot = createItemSlot(item, i);
            inventoryGrid.add(itemSlot, col, row);

            col++;
            if (col >= maxCols) {
                col = 0;
                row++;
            }

            // 🔥 LIMITAR VISUALIZAÇÃO MESMO QUE TENHA MAIS ITENS
            if (row >= 4) break; // Mostra apenas 20 itens (5x4)
        }

        // 🔥 MOSTRAR AVISO SE HÁ MAIS ITENS DO QUE CABE NA TELA
        if (inventory.getItems().size() > 20) {
            showExtraItemsWarning(inventory.getItems().size() - 20);
        }

        updateEquipmentSlots();
    }

    // 🔥 MÉTODO NOVO: Aviso de inventário cheio
    private void showInventoryFullWarning() {
        Label warningLabel = new Label("⚠️ INVENTÁRIO CHEIO! Venda ou armazene itens.");
        warningLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        warningLabel.setPrefWidth(400);

        // Adicionar no final do grid
        inventoryGrid.add(warningLabel, 0, 4, 5, 1); // Ocupa 5 colunas na linha 4
    }

    // 🔥 MÉTODO NOVO: Aviso de itens extras não mostrados
    private void showExtraItemsWarning(int extraItems) {
        Label extraLabel = new Label("... e mais " + extraItems + " itens não mostrados");
        extraLabel.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 12; -fx-alignment: center;");
        extraLabel.setPrefWidth(400);

        // Adicionar após os slots
        inventoryGrid.add(extraLabel, 0, 5, 5, 1); // Ocupa 5 colunas na linha 5
    }
    @FXML private Label selectedItemInfo;

    // 🔥 ATUALIZE O MÉTODO updateSelectedItemInfo
    private void updateSelectedItemInfo() {
        if (selectedItemInfo != null) {
            if (selectedItem != null) {
                String equipableText = (selectedItem instanceof Equipment) ? " (Equipável)" : " (Não equipável)";
                selectedItemInfo.setText("📌 Selecionado: " + selectedItem.getName() + equipableText);

                // Mudar cor baseado se é equipável
                if (selectedItem instanceof Equipment) {
                    selectedItemInfo.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 12; -fx-font-weight: bold;");
                } else {
                    selectedItemInfo.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12; -fx-font-weight: bold;");
                }
            } else {
                selectedItemInfo.setText("Nenhum item selecionado");
                selectedItemInfo.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 12; -fx-font-weight: bold;");
            }
        }
    }

    // 🔥 MÉTODO ATUALIZADO: createItemSlot com melhor visual para inventário cheio
    private VBox createItemSlot(Item item, int index) {
        VBox slot = new VBox();

        // 🔥 CORES DIFERENTES BASEADAS NO TIPO DE ITEM
        String backgroundColor = "#4a4a4a";
        String borderColor = "#666";

        if (item.equals(selectedItem)) {
            backgroundColor = "#5a5a5a";
            borderColor = "#ffd700";
        } else if (item instanceof Weapon) {
            borderColor = "#e74c3c"; // Vermelho para armas
        } else if (item instanceof Armor) {
            borderColor = "#3498db"; // Azul para armaduras
        }

        slot.setStyle("-fx-background-color: " + backgroundColor + "; " +
                "-fx-border-color: " + borderColor + "; " +
                "-fx-border-width: " + (item.equals(selectedItem) ? "2" : "1") + "; " +
                "-fx-border-radius: 5; -fx-padding: 5;");

        slot.setPrefSize(60, 60);
        slot.setMaxSize(60, 60); // 🔥 GARANTIR TAMANHO FIXO

        Label itemLabel = new Label(getItemAbbreviation(item));
        itemLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        Tooltip tooltip = new Tooltip(getItemTooltip(item));
        Tooltip.install(slot, tooltip);

        slot.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectedItem = item;
                updateInventoryDisplay();
                showTempMessage("✅ " + item.getName() + " selecionado");
            }
            else if (event.getClickCount() == 2 && item instanceof Equipment) {
                Equipment equipment = (Equipment) item;
                boolean equipped = gameManager.getPlayer().equipItem(equipment);
                if (equipped) {
                    gameManager.getPlayerInventory().removeItem(item);
                    selectedItem = null;
                    showTempMessage("✅ " + equipment.getName() + " equipado!");
                    updateInventoryDisplay();
                }
            }
        });

        slot.getChildren().add(itemLabel);
        return slot;
    }

    // 🔥 MÉTODO CORRIGIDO: handleSellSelected com funcionalidade real
    @FXML
    private void handleSellSelected() {
        if (selectedItem == null) {
            showTempMessage("❌ Nenhum item selecionado para venda!");
            return;
        }

        if (gameManager == null) return;

        int sellValue = selectedItem.getSellValue();
        String itemName = selectedItem.getName();

        // Confirmar venda
        showSellConfirmation(selectedItem, sellValue);
    }

    // 🔥 MÉTODO CORRIGIDO: Confirmação de venda
    private void showSellConfirmation(Item item, int sellValue) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar Venda");
        confirmation.setHeaderText("Vender " + item.getName() + "?");
        confirmation.setContentText("Valor da venda: " + sellValue + " 💰\n\n" +
                "Esta ação não pode ser desfeita!");

        ButtonType yesButton = new ButtonType("Vender", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(yesButton, noButton);

        confirmation.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                sellItem(item, sellValue);
            }
        });
    }

    // 🔥 MÉTODO CORRIGIDO: Executar venda
    private void sellItem(Item item, int sellValue) {
        // Remover do inventário
        boolean removed = gameManager.getPlayerInventory().removeItem(item);

        if (removed) {
            // Adicionar ouro ao jogador
            gameManager.getPlayer().addGold(sellValue);

            showTempMessage("💰 Vendido: " + item.getName() + " por " + sellValue + " ouro!");
            selectedItem = null; // Limpar seleção
            updateInventoryDisplay();

            // 🔥 CORREÇÃO: Usar log direto em vez de gameController
            System.out.println("💰 Vendido: " + item.getName() + " por " + sellValue + " ouro!");
        } else {
            showTempMessage("❌ Erro ao vender item!");
        }
    }

    // 🔥 MÉTODO CORRIGIDO: Venda rápida de todos os itens comuns
    @FXML
    private void handleSellAllCommon() {
        if (gameManager == null) return;

        var inventory = gameManager.getPlayerInventory();
        List<Item> commonItems = inventory.getItems().stream()
                .filter(item -> item.getRarity() == ItemRarity.COMMON)
                .collect(Collectors.toList());

        if (commonItems.isEmpty()) {
            showTempMessage("❌ Nenhum item comum para vender!");
            return;
        }

        int totalValue = commonItems.stream()
                .mapToInt(Item::getSellValue)
                .sum();

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Venda em Lote");
        confirmation.setHeaderText("Vender todos os itens comuns?");
        confirmation.setContentText("Itens: " + commonItems.size() + "\n" +
                "Valor total: " + totalValue + " 💰");

        confirmation.showAndWait().ifPresent(response -> {
            if (response.getButtonData() == ButtonBar.ButtonData.YES) {
                // Remover todos os itens comuns
                commonItems.forEach(inventory::removeItem);
                gameManager.getPlayer().addGold(totalValue);

                showTempMessage("💰 Vendidos " + commonItems.size() + " itens por " + totalValue + " ouro!");
                updateInventoryDisplay();
            }
        });
    }

    // 🔥 MÉTODOS DE DESEQUIPAR - APENAS ARMA FUNCIONA
    @FXML
    private void handleUnequipWeapon() {
        unequipWeapon();
    }
    @FXML
    private void handleUnequipHelmet() {
        unequipHelmet();
    }

    @FXML
    private void handleUnequipChest() {
        unequipChest();
    }


    @FXML
    private void handleUnequipGloves() {
        unequipGloves(); // ✅ JÁ IMPLEMENTADO
    }

    @FXML
    private void handleUnequipBoots() {
        unequipBoots(); // ✅ JÁ IMPLEMENTADO
    }

    // 🔥 MÉTODOS DE DESEQUIPAR ATUALIZADOS
    private void unequipWeapon() {
        if (gameManager == null) return;

        var player = gameManager.getPlayer();

        if (player.hasWeapon()) {
            Equipment weapon = player.getCurrentWeapon();
            boolean success = player.unequipWeapon();

            if (success) {
                gameManager.getPlayerInventory().addItem(weapon);
                showTempMessage("✅ " + weapon.getName() + " desequipado!");
                updateInventoryDisplay();
            }
        } else {
            showTempMessage("❌ Nenhuma arma equipada");
        }
    }

    private void setupEquipmentSlots() {
        // 🔥 ATUALIZAR TOOLTIPS PARA FUNCIONAIS
        setupEquipmentSlotTooltip(weaponSlot, "Slot de Arma\nClique duplo em uma arma para equipar");
        setupEquipmentSlotTooltip(helmetSlot, "Slot de Capacete\nClique duplo em um capacete para equipar");
        setupEquipmentSlotTooltip(chestSlot, "Slot de Armadura\nClique duplo em uma armadura para equipar");
        setupEquipmentSlotTooltip(glovesSlot, "Slot de Luvas\nClique duplo em luvas para equipar"); // ✅ FUNCIONAL
        setupEquipmentSlotTooltip(bootsSlot, "Slot de Botas\nClique duplo em botas para equipar"); // ✅ FUNCIONAL
    }

    private void setupEquipmentSlotTooltip(VBox slot, String text) {
        if (slot != null) {
            Tooltip tooltip = new Tooltip(text);
            Tooltip.install(slot, tooltip);
        }
    }

    // 🔥 MÉTODO ATUALIZADO: updateEquipmentSlots
    private void updateEquipmentSlots() {
        if (gameManager == null) return;

        var player = gameManager.getPlayer();

        // ARMA
        if (weaponLabel != null) {
            if (player.hasWeapon()) {
                Weapon weapon = player.getCurrentWeapon();
                weaponLabel.setText(weapon.getName());
                if (unequipWeaponButton != null) {
                    unequipWeaponButton.setVisible(true);
                }
            } else {
                weaponLabel.setText("Nenhuma");
                if (unequipWeaponButton != null) {
                    unequipWeaponButton.setVisible(false);
                }
            }
        }

        // CAPACETE
        if (helmetLabel != null) {
            if (player.hasHelmet()) {
                Armor helmet = player.getCurrentHelmet();
                helmetLabel.setText(helmet.getName());
                if (unequipHelmetButton != null) {
                    unequipHelmetButton.setVisible(true);
                }
            } else {
                helmetLabel.setText("Nenhum");
                if (unequipHelmetButton != null) {
                    unequipHelmetButton.setVisible(false);
                }
            }
        }

        // ARMADURA
        if (chestLabel != null) {
            if (player.hasChest()) {
                Armor chest = player.getCurrentChest();
                chestLabel.setText(chest.getName());
                if (unequipChestButton != null) {
                    unequipChestButton.setVisible(true);
                }
            } else {
                chestLabel.setText("Nenhuma");
                if (unequipChestButton != null) {
                    unequipChestButton.setVisible(false);
                }
            }
        }

        // LUVAS
        if (glovesLabel != null) {
            if (player.hasGloves()) {
                Armor gloves = player.getCurrentGloves();
                glovesLabel.setText(gloves.getName());
                if (unequipGlovesButton != null) {
                    unequipGlovesButton.setVisible(true);
                }
            } else {
                glovesLabel.setText("Nenhuma");
                if (unequipGlovesButton != null) {
                    unequipGlovesButton.setVisible(false);
                }
            }
        }

        // BOTAS
        if (bootsLabel != null) {
            if (player.hasBoots()) {
                Armor boots = player.getCurrentBoots();
                bootsLabel.setText(boots.getName());
                if (unequipBootsButton != null) {
                    unequipBootsButton.setVisible(true);
                }
            } else {
                bootsLabel.setText("Nenhum");
                if (unequipBootsButton != null) {
                    unequipBootsButton.setVisible(false);
                }
            }
        }
    }



    // 🔥 AÇÕES RÁPIDAS
    @FXML
    private void handleStoreSelected() {
        System.out.println("📦 Função de armazenar em desenvolvimento...");
        showTempMessage("📦 Armazenar em desenvolvimento...");
    }

    @FXML
    private void handleRefresh() {
        updateInventoryDisplay();
        System.out.println("🔄 Inventário atualizado");
        showTempMessage("🔄 Inventário atualizado");
    }

    @FXML
    private void handleClose() {
        if (inventoryStage != null) {
            inventoryStage.close();
        }
        System.out.println("🎒 Inventário fechado");
    }

    private void showTempMessage(String message) {
        if (inventoryTitle != null) {
            String originalText = inventoryTitle.getText();
            inventoryTitle.setText(message);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    javafx.application.Platform.runLater(() -> {
                        inventoryTitle.setText(originalText);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private String getItemAbbreviation(Item item) {
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

        tooltip.append("\n\n🔧 Clique duplo para equipar");

        return tooltip.toString();
    }

    private void unequipHelmet() {
        if (gameManager == null) return;

        var player = gameManager.getPlayer();

        if (player.hasHelmet()) {
            Equipment helmet = player.getCurrentHelmet();
            boolean success = player.unequipHelmet();

            if (success) {
                gameManager.getPlayerInventory().addItem(helmet);
                showTempMessage("✅ " + helmet.getName() + " desequipado!");
                updateInventoryDisplay();
            }
        } else {
            showTempMessage("❌ Nenhum capacete equipado");
        }
    }

    private void unequipChest() {
        if (gameManager == null) return;

        var player = gameManager.getPlayer();

        if (player.hasChest()) {
            Equipment chest = player.getCurrentChest();
            boolean success = player.unequipChest();

            if (success) {
                gameManager.getPlayerInventory().addItem(chest);
                showTempMessage("✅ " + chest.getName() + " desequipado!");
                updateInventoryDisplay();
            }
        } else {
            showTempMessage("❌ Nenhuma armadura equipada");
        }
    }

    private void unequipGloves() {
        if (gameManager == null) return;

        var player = gameManager.getPlayer();

        if (player.hasGloves()) {
            Equipment gloves = player.getCurrentGloves();
            boolean success = player.unequipGloves();

            if (success) {
                gameManager.getPlayerInventory().addItem(gloves);
                showTempMessage("✅ " + gloves.getName() + " desequipado!");
                updateInventoryDisplay();
            }
        } else {
            showTempMessage("❌ Nenhuma luva equipada");
        }
    }

    private void unequipBoots() {
        if (gameManager == null) return;

        var player = gameManager.getPlayer();

        if (player.hasBoots()) {
            Equipment boots = player.getCurrentBoots();
            boolean success = player.unequipBoots();

            if (success) {
                gameManager.getPlayerInventory().addItem(boots);
                showTempMessage("✅ " + boots.getName() + " desequipado!");
                updateInventoryDisplay();
            }
        } else {
            showTempMessage("❌ Nenhuma bota equipada");
        }
    }
    // 🔥 ADICIONE ESTE MÉTODO NO InventoryController.java
    @FXML
    private void handleEquipSelected() {
        if (selectedItem == null) {
            showTempMessage("❌ Nenhum item selecionado para equipar!");
            return;
        }

        if (!(selectedItem instanceof Equipment)) {
            showTempMessage("❌ Este item não pode ser equipado!");
            return;
        }

        Equipment equipment = (Equipment) selectedItem;
        boolean success = gameManager.getPlayer().equipItem(equipment);

        if (success) {
            showTempMessage("✅ " + equipment.getName() + " equipado!");

            // Remover do inventário se equipado com sucesso
            gameManager.getPlayerInventory().removeItem(selectedItem);
            selectedItem = null; // Limpar seleção
            updateInventoryDisplay();
        } else {
            showTempMessage("❌ Não foi possível equipar " + equipment.getName());
        }
    }

    // 🔥 MÉTODO ADICIONAL: Verificar se o item selecionado é equipável
    private boolean isSelectedItemEquippable() {
        return selectedItem != null && selectedItem instanceof Equipment;
    }
}