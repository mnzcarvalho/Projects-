package eternalidle.controller;

import eternalidle.model.systems.GameManager;
import eternalidle.model.inventory.Inventory;
import eternalidle.model.items.Item;
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
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class InventoryController {

    private GameManager gameManager;
    private Stage inventoryStage;

    // 🔥 VARIÁVEIS PARA DRAG & DROP
    private Item draggedItem = null;
    private VBox draggedSlot = null;

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

    private void updateInventoryDisplay() {
        if (gameManager == null) return;

        Inventory inventory = gameManager.getPlayerInventory();

        if (inventory == null) {
            System.out.println("❌ Inventory não encontrado");
            return;
        }

        // Atualizar capacidade
        if (capacityLabel != null) {
            capacityLabel.setText("Capacidade: " + inventory.getItems().size() + "/" + inventory.getCapacity());
        }

        // Limpar grid
        inventoryGrid.getChildren().clear();

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

            if (row >= 4) break;
        }

        updateEquipmentSlots();
    }

    private VBox createItemSlot(Item item, int index) {
        VBox slot = new VBox();
        slot.setStyle("-fx-background-color: #4a4a4a; -fx-border-color: #666; -fx-border-radius: 5; -fx-padding: 5;");
        slot.setPrefSize(60, 60);

        Label itemLabel = new Label(getItemAbbreviation(item));
        itemLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        Tooltip tooltip = new Tooltip(getItemTooltip(item));
        Tooltip.install(slot, tooltip);

        // 🔥 DRAG & DROP
        slot.setOnDragDetected(event -> {
            if (item instanceof Equipment) {
                draggedItem = item;
                draggedSlot = slot;
                slot.setStyle("-fx-background-color: #5a5a5a; -fx-border-color: #3498db; -fx-border-radius: 5; -fx-padding: 5;");
                System.out.println("🚀 Iniciando drag: " + item.getName());
            }
            event.consume();
        });

        // Configurar slots de equipamento para receber drop
        setupEquipmentSlotForDrop(weaponSlot, "weapon");

        slot.setOnMouseClicked(event -> onItemClicked(item, event));
        slot.getChildren().add(itemLabel);
        return slot;
    }

    private void setupEquipmentSlotForDrop(VBox equipmentSlot, String slotType) {
        if (equipmentSlot == null) return;

        equipmentSlot.setOnDragOver(event -> {
            if (draggedItem != null && draggedItem instanceof Equipment) {
                Equipment equip = (Equipment) draggedItem;
                if (canEquipInSlot(equip, slotType)) {
                    event.acceptTransferModes(TransferMode.MOVE);
                    equipmentSlot.setStyle("-fx-background-color: #27ae60; -fx-border-color: #2ecc71; -fx-border-radius: 5; -fx-padding: 10;");
                }
            }
            event.consume();
        });

        equipmentSlot.setOnDragExited(event -> {
            equipmentSlot.setStyle("-fx-background-color: #4a4a4a; -fx-border-color: #666; -fx-border-radius: 5; -fx-padding: 10;");
            event.consume();
        });

        equipmentSlot.setOnDragDropped(event -> {
            if (draggedItem != null && draggedItem instanceof Equipment) {
                Equipment equip = (Equipment) draggedItem;
                if (canEquipInSlot(equip, slotType)) {
                    boolean equipped = gameManager.getPlayer().equipItem(equip);
                    if (equipped) {
                        System.out.println("✅ " + equip.getName() + " equipado via drag & drop!");
                        updateInventoryDisplay();
                        if (draggedSlot != null) {
                            draggedSlot.setStyle("-fx-background-color: #4a4a4a; -fx-border-color: #666; -fx-border-radius: 5; -fx-padding: 5;");
                        }
                    }
                }
            }
            event.setDropCompleted(true);
            event.consume();
        });
    }

    private boolean canEquipInSlot(Equipment equip, String slotType) {
        if (equip instanceof Weapon && slotType.equals("weapon")) {
            return true;
        }
        return false;
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

        return tooltip.toString();
    }

    private void onItemClicked(Item item, MouseEvent event) {
        if (item instanceof Equipment) {
            Equipment equipment = (Equipment) item;
            boolean equipped = gameManager.getPlayer().equipItem(equipment);

            if (equipped) {
                System.out.println("✅ " + equipment.getName() + " equipado!");
                showTempMessage("✅ " + equipment.getName() + " equipado!");
                updateInventoryDisplay();
            } else {
                System.out.println("❌ Não foi possível equipar " + equipment.getName());
                showTempMessage("❌ Não foi possível equipar " + equipment.getName());
            }
        }
    }

    // 🔥 MÉTODOS DE DESEQUIPAR
    @FXML
    private void handleUnequipWeapon() {
        unequipItem("weapon");
    }

    @FXML
    private void handleUnequipHelmet() {
        unequipItem("helmet");
    }

    @FXML
    private void handleUnequipChest() {
        unequipItem("chest");
    }

    @FXML
    private void handleUnequipGloves() {
        unequipItem("gloves");
    }

    @FXML
    private void handleUnequipBoots() {
        unequipItem("boots");
    }

    private void unequipItem(String slotType) {
        if (gameManager == null) return;

        var equipmentManager = gameManager.getPlayer().getEquipmentManager();
        boolean success = false;

        switch (slotType) {
            case "weapon":
                if (equipmentManager.hasWeapon()) {
                    Equipment weapon = equipmentManager.getCurrentWeapon();
                    success = equipmentManager.unequipWeapon();
                    if (success) {
                        System.out.println("✅ " + weapon.getName() + " desequipado!");
                        showTempMessage("✅ " + weapon.getName() + " desequipado!");
                    }
                }
                break;
        }

        if (success) {
            updateInventoryDisplay();
        } else {
            System.out.println("❌ Nada para desequipar no slot: " + slotType);
            showTempMessage("❌ Nada para desequipar");
        }
    }

    private void setupEquipmentSlots() {
        setupEquipmentSlotTooltip(weaponSlot, "Slot de Arma\nEquipe armas para aumentar seu dano");
        setupEquipmentSlotTooltip(helmetSlot, "Slot de Capacete\nAumenta sua defesa");
        setupEquipmentSlotTooltip(chestSlot, "Slot de Armadura\nProteção para o torso");
        setupEquipmentSlotTooltip(glovesSlot, "Slot de Luvas\nProteção para as mãos");
        setupEquipmentSlotTooltip(bootsSlot, "Slot de Botas\nProteção para os pés");
    }

    private void setupEquipmentSlotTooltip(VBox slot, String text) {
        if (slot != null) {
            Tooltip tooltip = new Tooltip(text);
            Tooltip.install(slot, tooltip);
        }
    }

    private void updateEquipmentSlots() {
        if (gameManager == null) return;

        var equipmentManager = gameManager.getPlayer().getEquipmentManager();

        if (weaponLabel != null) {
            if (equipmentManager.hasWeapon()) {
                Weapon weapon = equipmentManager.getCurrentWeapon();
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

        if (unequipHelmetButton != null) unequipHelmetButton.setVisible(false);
        if (unequipChestButton != null) unequipChestButton.setVisible(false);
        if (unequipGlovesButton != null) unequipGlovesButton.setVisible(false);
        if (unequipBootsButton != null) unequipBootsButton.setVisible(false);
    }

    // 🔥 AÇÕES RÁPIDAS
    @FXML
    private void handleSellSelected() {
        System.out.println("💰 Função de vender em desenvolvimento...");
        showTempMessage("💰 Vender em desenvolvimento...");
    }

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
}