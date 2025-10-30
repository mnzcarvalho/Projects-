package EternalIdle.controller;

import EternalIdle.model.systems.GameManager;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class GameController {
    private GameManager gameManager;
    private AnimationTimer gameTimer;

    // Referências para os componentes da UI
    private Label playerNameLabel;
    private Label playerLevelLabel;
    private Label goldLabel;
    private Label monstersDefeatedLabel;
    private Label currentMonsterLabel;
    private Label monsterHealthLabel;
    private ProgressBar expProgressBar;
    private Label expLabel;
    private TextArea combatLog;

    public GameController() {
        setupGameManager();
    }

    public void setUIComponents(Label playerNameLabel, Label playerLevelLabel, Label goldLabel,
                                Label monstersDefeatedLabel, Label currentMonsterLabel,
                                Label monsterHealthLabel, ProgressBar expProgressBar,
                                Label expLabel, TextArea combatLog) {
        this.playerNameLabel = playerNameLabel;
        this.playerLevelLabel = playerLevelLabel;
        this.goldLabel = goldLabel;
        this.monstersDefeatedLabel = monstersDefeatedLabel;
        this.currentMonsterLabel = currentMonsterLabel;
        this.monsterHealthLabel = monsterHealthLabel;
        this.expProgressBar = expProgressBar;
        this.expLabel = expLabel;
        this.combatLog = combatLog;

        startGameLoop();
        updateUI();
        logToCombat("🎮 Eternal Idle iniciado!");
        logToCombat("Bem-vindo, " + gameManager.getPlayer().getName() + "!");
    }

    private void setupGameManager() {
        gameManager = new GameManager();
        System.out.println("✅ GameController conectado com GameManager!");
    }

    private void startGameLoop() {
        gameTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 2_000_000_000) { // 2 segundos
                    updateGameState();
                    lastUpdate = now;
                }
            }
        };
        gameTimer.start();
    }

    private void updateGameState() {
        // 🔥 AGORA USA O MÉTODO updateGame() DO GAMEMANAGER
        gameManager.updateGame();

        Platform.runLater(() -> {
            updateUI();
        });
    }

    private void updateUI() {
        try {
            // 🔥 CORRETO: Usar gameManager.getPlayer() em vez de player diretamente
            var player = gameManager.getPlayer();

            // Atualizar interface com dados reais
            if (playerNameLabel != null) playerNameLabel.setText(player.getName());
            if (playerLevelLabel != null) playerLevelLabel.setText("Level " + player.getLevel() + " ⭐");
            if (goldLabel != null) goldLabel.setText(player.getGold() + " 💰");
            if (monstersDefeatedLabel != null) monstersDefeatedLabel.setText(gameManager.getMonstersDefeated() + " defeated");
            if (currentMonsterLabel != null) currentMonsterLabel.setText(gameManager.getCurrentMonsterName());
            if (monsterHealthLabel != null) monsterHealthLabel.setText(gameManager.getMonsterHealthInfo());
            if (expProgressBar != null) expProgressBar.setProgress(player.getExpProgress());
            if (expLabel != null) expLabel.setText(player.getCurrentExp() + "/" + player.getExpToNextLevel() + " EXP");

        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar UI: " + e.getMessage());
        }
    }

    private void logToCombat(String message) {
        if (combatLog != null) {
            combatLog.appendText(message + "\n");
            combatLog.setScrollTop(Double.MAX_VALUE);
        }
    }

    // === MÉTODOS PÚBLICOS PARA OS BOTÕES ===
    public void manualAttack() {
        logToCombat("⚔️ Ataque manual executado!");
        // 🔥 CORRETO: Usar gameManager.simulateCombat()
        gameManager.simulateCombat();
        updateUI(); // Atualizar UI imediatamente após o ataque
    }

    public void openInventory() {
        logToCombat("🎒 Abrindo inventário...");
        gameManager.showInventory();
    }

    public void openSkills() {
        logToCombat("🌳 Abrindo habilidades...");
        gameManager.showSkills();
    }

    public void openShop() {
        logToCombat("🏪 Abrindo loja...");
        gameManager.openShop();
    }

    public void openStash() {
        logToCombat("🏠 Abrindo stash...");
        gameManager.openStash();
    }

    public void openCrafting() {
        logToCombat("🔨 Abrindo crafting...");
        gameManager.openCrafting();
    }

    public void openBosses() {
        logToCombat("🐉 Abrindo bosses...");
        gameManager.showBosses();
    }

    public void saveGame() {
        logToCombat("💾 Salvando jogo...");
        gameManager.saveGame("manual_save");
    }

    public void stopGame() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        logToCombat("⏹️ Encerrando jogo...");
        gameManager.stopGame();
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}