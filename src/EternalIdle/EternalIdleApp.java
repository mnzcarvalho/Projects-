package eternalidle;

import EternalIdle.model.systems.GameManager;
import EternalIdle.model.entity.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class EternalIdleApp extends Application {

    private GameManager gameManager;
    private Label levelLabel, goldLabel, monsterLabel, monsterHealthLabel, expLabel;
    private ProgressBar expBar;
    private TextArea combatLog;

    @Override
    public void start(Stage primaryStage) {
        System.out.println("🎮 Iniciando Eternal Idle...");

        try {
            gameManager = new GameManager();
            System.out.println("✅ GameManager criado com sucesso!");

            VBox root = createInterface();

            primaryStage.setTitle("Eternal Idle 🎮");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();

            startUILoop();

            log("🎮 Eternal Idle iniciado!");
            log("Bem-vindo, " + gameManager.getPlayer().getName() + "!");

        } catch (Exception e) {
            System.err.println("❌ Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private VBox createInterface() {
        VBox main = new VBox(10);
        main.setPadding(new Insets(15));
        main.setStyle("-fx-background-color: #1e1e1e;");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        levelLabel = createLabel("Level: 1", "#ffd700", 16);
        goldLabel = createLabel("Gold: 0", "#ffd700", 16);
        expLabel = createLabel("EXP: 0/100", "#00ff00", 14);

        header.getChildren().addAll(levelLabel, goldLabel, expLabel);

        // Combat Area
        VBox combatArea = new VBox(10);
        combatArea.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 15; -fx-background-radius: 8;");
        combatArea.setAlignment(Pos.CENTER);

        monsterLabel = createLabel("Monstro", "#ff6b6b", 18);
        monsterHealthLabel = createLabel("HP: ???/???", "#ffffff", 14);

        Button attackBtn = new Button("⚔️ Attack");
        attackBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        attackBtn.setOnAction(e -> {
            log("⚔️ Ataque manual!");
            gameManager.simulateCombat(); // 🔥 AGORA DEVE FUNCIONAR
            updateUI();
        });

        combatArea.getChildren().addAll(monsterLabel, monsterHealthLabel, attackBtn);

        // Log
        combatLog = new TextArea();
        combatLog.setPrefHeight(200);
        combatLog.setEditable(false);
        combatLog.setStyle("-fx-control-inner-background: #1a1a1a; -fx-text-fill: #00ff00;");

        main.getChildren().addAll(header, combatArea, combatLog);
        return main;
    }

    private Label createLabel(String text, String color, int size) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: " + color + "; -fx-font-size: " + size + "px; -fx-font-weight: bold;");
        return label;
    }

    private void startUILoop() {
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000) {
                    updateUI();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    private void updateUI() {
        try {
            Player player = gameManager.getPlayer();

            levelLabel.setText("Level: " + player.getLevel());
            goldLabel.setText("Gold: " + player.getGold());
            expLabel.setText("EXP: " + player.getCurrentExp() + "/" + player.getExpToNextLevel());

            monsterLabel.setText(gameManager.getCurrentMonsterName());
            monsterHealthLabel.setText("HP: " + gameManager.getMonsterHealthInfo());

        } catch (Exception e) {
            System.err.println("Erro UI: " + e.getMessage());
        }
    }

    private void log(String message) {
        combatLog.appendText(message + "\n");
        combatLog.setScrollTop(Double.MAX_VALUE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}