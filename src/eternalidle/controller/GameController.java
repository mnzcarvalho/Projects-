package eternalidle.controller;

import eternalidle.model.systems.GameManager;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import eternalidle.controller.InventoryController; // 🔥 NOVO IMPORT

public class GameController {
    private GameManager gameManager;
    private AnimationTimer gameTimer;

    // Componentes da UI - vamos inicializar com null
    private Label playerNameLabel;
    private Label playerLevelLabel;
    private Label goldLabel;
    private Label monstersDefeatedLabel;
    private Label currentMonsterLabel;
    private Label monsterHealthLabel;
    private ProgressBar expProgressBar;
    private Label expLabel;
    private TextArea combatLog;
    private Label dpsLabel;
    private Label tierLabel;
    private ProgressBar monsterHealthBar;

    public GameController() {
        setupGameManager();
    }

    // 🔥 MÉTODO SIMPLIFICADO - Só recebe o root e encontra os componentes internamente
    public void initializeUI(Parent root) {
        try {
            // Encontrar todos os componentes pelo ID
            findUIComponents(root);

            // Iniciar o jogo
            startGameLoop();
            updateUI();
            logToCombat("🎮 Eternal Idle iniciado!");
            logToCombat("Bem-vindo, " + gameManager.getPlayer().getName() + "!");

            // Configurar tooltips
            setupTooltips();

            System.out.println("✅ Todos os componentes UI conectados no GameController!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao inicializar UI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 🔥 NOVO MÉTODO: Encontrar componentes pelo ID
    private void findUIComponents(Parent root) {
        playerNameLabel = (Label) root.lookup("#playerNameLabel");
        playerLevelLabel = (Label) root.lookup("#playerLevelLabel");
        goldLabel = (Label) root.lookup("#goldLabel");
        monstersDefeatedLabel = (Label) root.lookup("#monstersDefeatedLabel");
        currentMonsterLabel = (Label) root.lookup("#currentMonsterLabel");
        monsterHealthLabel = (Label) root.lookup("#monsterHealthLabel");
        expProgressBar = (ProgressBar) root.lookup("#expProgressBar");
        expLabel = (Label) root.lookup("#expLabel");
        combatLog = (TextArea) root.lookup("#combatLog");
        dpsLabel = (Label) root.lookup("#dpsLabel");
        tierLabel = (Label) root.lookup("#tierLabel");
        monsterHealthBar = (ProgressBar) root.lookup("#monsterHealthBar");

        System.out.println("🔍 Componentes encontrados:");
        System.out.println("   - playerNameLabel: " + (playerNameLabel != null));
        System.out.println("   - playerLevelLabel: " + (playerLevelLabel != null));
        System.out.println("   - goldLabel: " + (goldLabel != null));
        System.out.println("   - combatLog: " + (combatLog != null));
        // ... outros componentes
    }

    private void setupGameManager() {
        gameManager = new GameManager();
        System.out.println("✅ GameController conectado com GameManager!");
    }

    // 🔥 MÉTODO setupTooltips ATUALIZADO (sem referência a botões)
    private void setupTooltips() {
        try {
            // Tooltips para estatísticas
            if (dpsLabel != null) {
                Tooltip dpsTooltip = new Tooltip("Dano Por Segundo (DPS)\n" +
                        "• Baseado na sua arma equipada\n" +
                        "• Aumenta com equipamentos melhores\n" +
                        "• Bônus de habilidades aplicados");
                Tooltip.install(dpsLabel, dpsTooltip);
            }

            if (tierLabel != null) {
                Tooltip tierTooltip = new Tooltip("Tier de Monstros Atual\n" +
                        "• A cada 3 monstros derrotados sobe de tier\n" +
                        "• Monstros de tier mais alto dão mais EXP e Ouro\n" +
                        "• Tier máximo: 16 (Bosses)");
                Tooltip.install(tierLabel, tierTooltip);
            }

            if (goldLabel != null) {
                Tooltip goldTooltip = new Tooltip("Ouro Disponível\n" +
                        "• Ganhe ouro derrotando monstros\n" +
                        "• Use na loja para comprar equipamentos\n" +
                        "• Bônus de habilidades aumenta o ouro ganho");
                Tooltip.install(goldLabel, goldTooltip);
            }

            if (expProgressBar != null) {
                Tooltip expTooltip = new Tooltip("Progresso de Experiência\n" +
                        "• Ganhe EXP derrotando monstros\n" +
                        "• Ao encher a barra, sobe de nível\n" +
                        "• Cada nível dá 1 ponto de habilidade");
                Tooltip.install(expProgressBar, expTooltip);
            }

            if (monsterHealthBar != null) {
                Tooltip healthTooltip = new Tooltip("Vida do Monstro Atual\n" +
                        "• Verde: Vida alta (>60%)\n" +
                        "• Laranja: Vida média (30%-60%)\n" +
                        "• Vermelho: Vida baixa (<30%)");
                Tooltip.install(monsterHealthBar, healthTooltip);
            }

            System.out.println("✅ Tooltips configurados!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao configurar tooltips: " + e.getMessage());
        }
    }

    // 🔥 GAME LOOP E ATUALIZAÇÃO DE UI
    void startGameLoop() {
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
        System.out.println("✅ Game loop iniciado!");
    }

    private void updateGameState() {
        gameManager.updateGame();
        Platform.runLater(() -> {
            updateUI();
        });
    }

    void updateUI() {
        try {
            var player = gameManager.getPlayer();
            var monster = gameManager.getCurrentMonster();

            // Atualizar interface com dados reais - COM VERIFICAÇÕES
            if (playerNameLabel != null) playerNameLabel.setText(player.getName());
            if (playerLevelLabel != null) playerLevelLabel.setText("Level " + player.getLevel() + " ⭐");
            if (goldLabel != null) goldLabel.setText(player.getGold() + " 💰");
            if (monstersDefeatedLabel != null) monstersDefeatedLabel.setText(gameManager.getMonstersDefeated() + " defeated");
            if (currentMonsterLabel != null) currentMonsterLabel.setText(gameManager.getCurrentMonsterName());
            if (monsterHealthLabel != null) monsterHealthLabel.setText(gameManager.getMonsterHealthInfo());
            if (expProgressBar != null) expProgressBar.setProgress(player.getExpProgress());
            if (expLabel != null) expLabel.setText(player.getCurrentExp() + "/" + player.getExpToNextLevel() + " EXP");

            // 🔥 NOVOS COMPONENTES
            if (dpsLabel != null) {
                double dps = player.getWeaponDPS();
                dpsLabel.setText(String.format("%.1f ⚡", dps));
            }

            if (tierLabel != null) {
                int tier = calculateCurrentTier() + 1;
                tierLabel.setText("Tier " + tier + " " + getTierEmoji(tier));
            }

            if (monsterHealthBar != null && monster != null) {
                double healthProgress = monster.getHealth().doubleValue() / monster.getMaxHealth().doubleValue();
                monsterHealthBar.setProgress(healthProgress);

                // 🔥 ATUALIZAR CLASSES CSS DINAMICAMENTE
                monsterHealthBar.getStyleClass().removeAll("monster-health-bar-low", "monster-health-bar-medium", "monster-health-bar-high");

                if (healthProgress < 0.3) {
                    monsterHealthBar.getStyleClass().add("monster-health-bar-low");
                } else if (healthProgress < 0.6) {
                    monsterHealthBar.getStyleClass().add("monster-health-bar-medium");
                } else {
                    monsterHealthBar.getStyleClass().add("monster-health-bar-high");
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar UI: " + e.getMessage());
        }
    }

    void logToCombat(String message) {
        if (combatLog != null) {
            Platform.runLater(() -> {
                combatLog.appendText(message + "\n");
                combatLog.setScrollTop(Double.MAX_VALUE);
            });
        } else {
            System.out.println("📜 " + message); // Fallback para console
        }
    }

    // === MÉTODOS PÚBLICOS PARA OS BOTÕES ===
    public void manualAttack() {
        logToCombat("⚔️ Ataque manual executado!");
        gameManager.simulateCombat();
        updateUI(); // Atualizar UI imediatamente após o ataque
    }

    public void openInventory() {
        logToCombat("🎒 Abrindo inventário...");

        try {
            // Carregar o FXML do inventário
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eternalidle/view/inventory.fxml"));
            Parent inventoryRoot = loader.load();

            // Obter o controller do inventário
            InventoryController inventoryController = loader.getController();

            // Passar o GameManager para o inventário
            inventoryController.setGameManager(gameManager);

            // Criar nova janela
            Stage inventoryStage = new Stage();
            inventoryController.setInventoryStage(inventoryStage);

            // Configurar a janela
            inventoryStage.setTitle("Inventário - Eternal Idle");
            inventoryStage.setScene(new Scene(inventoryRoot, 600, 500));
            inventoryStage.setResizable(false);

            // Mostrar a janela
            inventoryStage.show();

            logToCombat("📋 Inventário visual aberto!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao abrir inventário: " + e.getMessage());
            e.printStackTrace();
            logToCombat("❌ Erro ao abrir inventário visual");
            // Fallback para o console
            gameManager.showInventory();
        }
    }

    public void openSkills() {
        logToCombat("🌳 Abrindo árvore de habilidades...");
        gameManager.showSkills();
        logToCombat("🌿 Sistema de habilidades visual em desenvolvimento...");
    }

    public void openShop() {
        logToCombat("🏪 Abrindo loja...");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eternalidle/view/shop.fxml"));
            Parent shopRoot = loader.load();

            ShopController shopController = loader.getController();
            shopController.setGameManager(gameManager);

            Stage shopStage = new Stage();
            shopController.setShopStage(shopStage);

            shopStage.setTitle("Loja - Eternal Idle");
            shopStage.setScene(new Scene(shopRoot, 700, 500));
            shopStage.setResizable(false);
            shopStage.show();

            logToCombat("🛒 Loja visual aberta!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao abrir loja: " + e.getMessage());
            e.printStackTrace();
            logToCombat("❌ Erro ao abrir loja visual");
            // Fallback para o console
            gameManager.openShop();
        }
    }

    public void openStash() {
        logToCombat("🏠 Acessando stash...");
        gameManager.openStash();
        logToCombat("📦 Sistema de stash visual em desenvolvimento...");
    }

    public void openCrafting() {
        logToCombat("🔨 Acessando sistema de crafting...");
        gameManager.openCrafting();
        logToCombat("🛠️ Sistema de crafting visual em desenvolvimento...");
    }

    public void openBosses() {
        logToCombat("🐉 Consultando bosses disponíveis...");
        gameManager.showBosses();
        logToCombat("👹 Sistema de bosses visual em desenvolvimento...");
    }

    public void saveGame() {
        logToCombat("💾 Salvando progresso do jogo...");
        gameManager.saveGame("manual_save");
        logToCombat("✅ Jogo salvo com sucesso!");
    }

    public void stopGame() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        logToCombat("⏹️ Encerrando jogo...");
    }

    // 🔥 MÉTODOS AUXILIARES
    private int calculateCurrentTier() {
        return Math.min(gameManager.getMonstersDefeated() / 3, 15);
    }

    private String getTierEmoji(int tier) {
        if (tier < 3) return "🐀";
        if (tier < 6) return "👺";
        if (tier < 9) return "👻";
        if (tier < 12) return "🦅";
        if (tier < 15) return "🪨";
        return "🐉";
    }

    public GameManager getGameManager() {
        return gameManager;
    }


}