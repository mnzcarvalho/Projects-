package eternalidle.controller;

import eternalidle.model.systems.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StashController {

    private GameManager gameManager;
    private Stage stashStage;

    @FXML private Label stashTitle;
    @FXML private Label capacityLabel;
    @FXML private GridPane stashGrid;
    @FXML private Button closeButton;

    @FXML
    private void initialize() {
        System.out.println("✅ StashController inicializado!");
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        initializeStash();
    }

    public void setStashStage(Stage stage) {
        this.stashStage = stage;
    }

    private void initializeStash() {
        if (gameManager == null) return;

        if (stashTitle != null) {
            stashTitle.setText("Stash - Eternal Idle");
        }

        updateStashDisplay();
        System.out.println("✅ Stash visual inicializado!");
    }

    private void updateStashDisplay() {
        if (stashGrid == null) return;

        stashGrid.getChildren().clear();

        Label tempLabel = new Label("🏠 Sistema de Stash Visual\n\n" +
                "• Integração com StashSystem existente\n" +
                "• Abas por tipo de item\n" +
                "• Drag & drop do inventário\n\n" +
                "📦 Aguardando estrutura real...");
        tempLabel.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 14; -fx-alignment: center;");
        tempLabel.setPrefSize(400, 200);
        stashGrid.add(tempLabel, 0, 0);
    }

    // 🔥 MÉTODO CORRIGIDO: handleOrganize
    @FXML
    private void handleOrganize() {
        System.out.println("🔀 Organizando stash...");
        showTempMessage("🔀 Stash organizado!");
        // Aqui virá a lógica real de organização quando tivermos os arquivos
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
        System.out.println("🏠 Stash fechado");
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
                    e.printStackTrace();
                }
            }).start();
        }
    }
}