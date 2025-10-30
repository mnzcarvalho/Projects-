package EternalIdle.model.systems;

import EternalIdle.model.entity.Monster;
import EternalIdle.model.entity.MonsterFactory;
import EternalIdle.model.entity.Player;
import java.math.BigDecimal;
import java.util.List;
import EternalIdle.model.items.Item;
import EternalIdle.model.inventory.Inventory;
import EternalIdle.model.items.ItemRarity;
import EternalIdle.model.items.equipment.Equipment;
import EternalIdle.model.items.equipment.Weapon;
import EternalIdle.model.persistence.SaveManager;
import EternalIdle.model.statistics.PlayerStatistics;
import EternalIdle.model.statistics.GameStatistics;

public class GameManager {
    private Player player;
    private Monster currentMonster;
    private boolean gameRunning;
    private int monstersDefeated;
    private Thread gameThread;
    private List<Monster> allMonsters;
    private Inventory playerInventory;
    private DropSystem dropSystem;
    private ShopSystem shopSystem;
    private StashSystem stashSystem;
    private SaveManager saveManager;
    private PlayerStatistics playerStatistics;
    private GameStatistics gameStatistics;
    private long lastAutosaveTime;
    private CraftingSystem craftingSystem;
    private BossManager bossManager;

    // 🔥 CONFIGURAÇÃO DE DANO PARA TESTE
    private static final int PLAYER_BASE_DAMAGE = 50;

    public GameManager() {
        this.player = new Player("Herói");
        this.monstersDefeated = 0;
        this.gameRunning = true;
        this.allMonsters = MonsterFactory.createAllMonsters();
        this.playerInventory = new Inventory(20);
        this.dropSystem = new DropSystem();
        this.stashSystem = new StashSystem();

        // 🔥 INICIALIZAR ESTATÍSTICAS PRIMEIRO
        this.playerStatistics = new PlayerStatistics();
        this.gameStatistics = new GameStatistics();

        // 🔥 AGORA PASSAR AS ESTATÍSTICAS PARA O SHOP SYSTEM
        this.shopSystem = new ShopSystem(stashSystem, playerStatistics);

        this.craftingSystem = new CraftingSystem();
        this.bossManager = new BossManager();
        this.saveManager = new SaveManager();
        this.lastAutosaveTime = System.currentTimeMillis();

        spawnMonsterByTier();

        // 🔥 COMENTE O LOOP AUTOMÁTICO PARA JAVAFX
        // startGame();
    }

    public void startGame() {
        System.out.println("🎮 === IDLE GAME INICIADO === 🎮");
        System.out.println("Bem-vindo, " + player.getName() + "!");
        System.out.println("Progressão automática ativada...");

        gameThread = new Thread(this::gameLoop);
        gameThread.start();
    }

    private void gameLoop() {
        while (gameRunning && monstersDefeated < 50) {
            try {
                updateGame();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Jogo interrompido");
                break;
            }
        }

        if (monstersDefeated >= 50) {
            System.out.println("🎊 PARABÉNS! Você completou a demonstração!");
            System.out.println("Monstros derrotados: " + monstersDefeated);
            player.displayStatus();
        }
    }

    // 🔥 MÉTODO PÚBLICO PARA JAVAFX
    public void updateGame() {
        if (currentMonster == null || !currentMonster.isAlive()) {
            defeatMonster();
            spawnMonsterByTier();
        } else {
            simulateCombat();
        }
    }

    // 🔥 MÉTODO PÚBLICO PARA JAVAFX
    public void simulateCombat() {
        BigDecimal playerDamage = BigDecimal.valueOf(PLAYER_BASE_DAMAGE + player.getWeaponDamage());
        boolean monsterDied = currentMonster.takeDamage(playerDamage);

        if (monsterDied) {
            defeatMonster();
            spawnMonsterByTier();
        }
    }

    private void spawnMonsterByTier() {
        int tier = calculateCurrentTier();
        currentMonster = MonsterFactory.getMonsterByTier(tier);
        System.out.println("✨ " + getTierEmoji(tier) + " " + currentMonster.getName() +
                " (Nível " + currentMonster.getLevel() + ") apareceu!");
    }

    private int calculateCurrentTier() {
        return Math.min(monstersDefeated / 3, allMonsters.size() - 1);
    }

    private String getTierEmoji(int tier) {
        if (tier < 3) return "🐀";
        if (tier < 6) return "👺";
        if (tier < 9) return "👻";
        if (tier < 12) return "🦅";
        if (tier < 15) return "🪨";
        return "🐉";
    }

    private void defeatMonster() {
        if (currentMonster != null && !currentMonster.isAlive()) {
            double goldBonus = 1.0 + player.getSkillTree().getTotalGoldBonus();
            long bonusGold = (long) (currentMonster.getGoldReward() * goldBonus);

            player.addExperience(currentMonster.getExpReward());
            player.addGold(bonusGold);
            monstersDefeated++;

            System.out.println("💀 " + currentMonster.getName() + " foi derrotado!");
            System.out.println("   +" + currentMonster.getExpReward() + " EXP");
            System.out.println("   +" + bonusGold + " Ouro (+" +
                    String.format("%.0f", (goldBonus - 1.0) * 100) + "% bônus)");

            playerStatistics.addMonsterKilled();
            playerStatistics.addGoldEarned(currentMonster.getGoldReward());
            playerStatistics.addDamageDealt(currentMonster.getMaxHealth().longValue());
            gameStatistics.addMonsterKill(currentMonster.getName());
            checkAutosave();

            // Sistema de Drops
            List<Item> drops = dropSystem.generateMonsterDrops(
                    currentMonster.getName(), currentMonster.getLevel());

            for (Item drop : drops) {
                if (stashSystem.autoStoreItem(drop)) {
                    System.out.println("📦 " + drop.getName() + " armazenado no stash!");
                } else if (playerInventory.addItem(drop)) {
                    // Item adicionado ao inventário normal
                } else {
                    System.out.println("❌ Não foi possível armazenar: " + drop.getName());
                }

                if (drop instanceof Equipment) {
                    Equipment equipment = (Equipment) drop;
                    if (equipment.canEquip(player.getLevel())) {
                        if (equipment.getRarity().ordinal() >= ItemRarity.RARE.ordinal()) {
                            player.equipItem(equipment);
                        }
                    }
                }
            }

            if (player.getLevel() % 10 == 0) {
                player.displaySkills();
            }

            if (monstersDefeated % 10 == 0) {
                player.displayEquipment();
            }

            if (monstersDefeated % 5 == 0) {
                playerInventory.displayInventory();
            }

            int newTier = calculateCurrentTier();
            if (monstersDefeated % 3 == 0) {
                System.out.println("📈 ** SUBIU PARA TIER " + (newTier + 1) + "! **");
            }
        }
    }

    private void displayGameStatus() {
        System.out.println("\n=== STATUS ===");
        System.out.println("Jogador: " + player.getName() + " (Nível " + player.getLevel() + ")");
        System.out.println("💰 Ouro: " + player.getGold());

        if (player.getEquipmentManager().hasWeapon()) {
            Weapon weapon = player.getEquipmentManager().getCurrentWeapon();
            System.out.println("Arma: " + weapon.getName() + " (DPS: " +
                    String.format("%.1f", weapon.getDPS()) + ")");
        } else {
            System.out.println("Arma: Nenhuma");
        }

        System.out.println("Tier Atual: " + (calculateCurrentTier() + 1));
        System.out.println("Monstros Derrotados: " + monstersDefeated + "/50");

        if (currentMonster != null) {
            System.out.println("Monstro Atual: " + currentMonster.getName() +
                    " - Vida: " + currentMonster.getHealth() + "/" + currentMonster.getMaxHealth());
        }

        System.out.println("==============\n");
    }

    public void openShop() {
        shopSystem.openShop(player, playerInventory);
    }

    public void openStash() {
        stashSystem.openStash();
    }

    public void showPlayerStatus() {
        System.out.println("\n=== STATUS DO JOGADOR ===");
        player.displayStatus();
    }

    private void checkAutosave() {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastSave = currentTime - lastAutosaveTime;
        long autosaveIntervalMs = saveManager.getAutosaveInterval() * 60 * 1000;

        if (timeSinceLastSave >= autosaveIntervalMs) {
            performAutosave();
            lastAutosaveTime = currentTime;
        }
    }

    private void performAutosave() {
        if (saveManager.isAutosaveEnabled()) {
            System.out.println("💾 Salvando automaticamente...");
            boolean success = saveManager.saveGame("autosave", player, playerStatistics, gameStatistics);
            if (success) {
                playerStatistics.updateSaveTime();
                System.out.println("✅ Progresso salvo automaticamente!");
            }
        }
    }

    public void saveGame(String saveName) {
        System.out.println("💾 Salvando jogo...");
        boolean success = saveManager.saveGame(saveName, player, playerStatistics, gameStatistics);
        if (success) {
            playerStatistics.updateSaveTime();
            System.out.println("✅ Jogo salvo como: " + saveName);
        } else {
            System.out.println("❌ Falha ao salvar o jogo!");
        }
    }

    public void loadGame(String saveName) {
        System.out.println("💾 Carregando jogo: " + saveName);
        System.out.println("🔄 Sistema de carregamento em desenvolvimento...");
    }

    public void showSaveFiles() {
        saveManager.displaySaveFiles();
    }

    public void showStatistics() {
        playerStatistics.displayStatistics();
    }

    public void showDetailedStatistics() {
        gameStatistics.displayDetailedStatistics();
    }

    public void stopGame() {
        gameRunning = false;
        System.out.println("💾 Salvando antes de sair...");
        saveGame("exit_save");

        if (gameThread != null) {
            gameThread.interrupt();
        }
        System.out.println("⏹️ Jogo encerrado!");
        player.displayStatus();
        playerInventory.displayInventory();
    }

    // 🔥 MÉTODOS PARA JAVAFX
    public Player getPlayer() {
        return player;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public int getMonstersDefeated() {
        return monstersDefeated;
    }

    public String getMonsterHealthInfo() {
        if (currentMonster != null) {
            return currentMonster.getHealth() + "/" + currentMonster.getMaxHealth() + " HP";
        }
        return "Nenhum monstro";
    }

    public String getCurrentMonsterName() {
        if (currentMonster != null) {
            return currentMonster.getName();
        }
        return "Aguardando monstro...";
    }

    public void openCrafting() {
        craftingSystem.openCrafting();
    }

    public void showBosses() {
        bossManager.showBosses();
    }

    public void showSkills() {
        player.displaySkills();
    }

    public void showEquipment() {
        player.displayEquipment();
    }

    public void showInventory() {
        playerInventory.displayInventory();
    }
}