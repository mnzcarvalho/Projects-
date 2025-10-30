package eternalidle;

import eternalidle.controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EternalIdleApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("🎮 Iniciando Eternal Idle com JavaFX...");

        // Carregar o FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/game.fxml"));
        Parent root = loader.load();

        // Obter o controller
        GameController controller = loader.getController();

        // 🔥 CONECTAR OS COMPONENTES DA UI (MÉTODO SIMPLIFICADO)
        controller.initializeUI(root);

        // 🔥 CONECTAR BOTÕES COM AÇÕES
        connectButtons(controller, root);

        primaryStage.setTitle("Eternal Idle 🎮 - JavaFX");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        System.out.println("✅ Interface JavaFX carregada com sucesso!");
    }

    // 🔥 MÉTODO SIMPLIFICADO APENAS PARA BOTÕES
    private void connectButtons(GameController controller, Parent root) {
        try {
            // Encontrar e conectar botões
            javafx.scene.control.Button attackButton = (javafx.scene.control.Button) root.lookup("#attackButton");
            javafx.scene.control.Button inventoryButton = (javafx.scene.control.Button) root.lookup("#inventoryButton");
            javafx.scene.control.Button skillsButton = (javafx.scene.control.Button) root.lookup("#skillsButton");
            javafx.scene.control.Button shopButton = (javafx.scene.control.Button) root.lookup("#shopButton");
            javafx.scene.control.Button stashButton = (javafx.scene.control.Button) root.lookup("#stashButton");
            javafx.scene.control.Button craftingButton = (javafx.scene.control.Button) root.lookup("#craftingButton");
            javafx.scene.control.Button bossesButton = (javafx.scene.control.Button) root.lookup("#bossesButton");
            javafx.scene.control.Button saveButton = (javafx.scene.control.Button) root.lookup("#saveButton");

            // Conectar ações
            if (attackButton != null) attackButton.setOnAction(e -> controller.manualAttack());
            if (inventoryButton != null) inventoryButton.setOnAction(e -> controller.openInventory());
            if (skillsButton != null) skillsButton.setOnAction(e -> controller.openSkills());
            if (shopButton != null) shopButton.setOnAction(e -> controller.openShop());
            if (stashButton != null) stashButton.setOnAction(e -> controller.openStash());
            if (craftingButton != null) craftingButton.setOnAction(e -> controller.openCrafting());
            if (bossesButton != null) bossesButton.setOnAction(e -> controller.openBosses());
            if (saveButton != null) saveButton.setOnAction(e -> controller.saveGame());

            System.out.println("✅ Botões conectados com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar botões: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}