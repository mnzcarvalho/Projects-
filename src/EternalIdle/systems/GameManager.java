package EternalIdle.systems;

import EternalIdle.entity.Monster;
import EternalIdle.entity.MonsterFactory;
import EternalIdle.entity.Player;
import java.math.BigDecimal;
import java.util.List;
import EternalIdle.items.Item;
import EternalIdle.inventory.Inventory;
import EternalIdle.items.ItemRarity;
import EternalIdle.items.equipment.Equipment;
import EternalIdle.items.equipment.Weapon;
import EternalIdle.persistence.SaveManager;
import EternalIdle.statistics.PlayerStatistics;
import EternalIdle.statistics.GameStatistics;

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
        this.shopSystem = new ShopSystem(stashSystem, playerStatistics); // 🔥 ATUALIZADO

        this.craftingSystem = new CraftingSystem();
        this.bossManager = new BossManager();
        this.saveManager = new SaveManager();
        this.lastAutosaveTime = System.currentTimeMillis();

        spawnMonsterByTier();
    }

    public void startGame() {
        System.out.println("🎮 === IDLE GAME INICIADO === 🎮");
        System.out.println("Bem-vindo, " + player.getName() + "!");
        System.out.println("Progressão automática ativada...");

        gameThread = new Thread(this::gameLoop);
        gameThread.start();
    }

    private void gameLoop() {
        while (gameRunning && monstersDefeated < 50) { // Limite de 50 monstros para teste
            try {
                updateGame();
                Thread.sleep(2000); // Mais rápido para teste (2 segundos)
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

    private void updateGame() {
        if (currentMonster == null || !currentMonster.isAlive()) {
            defeatMonster();
            spawnMonsterByTier();
        } else {
            simulateCombat();
        }

        displayGameStatus();
    }

    private void simulateCombat() {
        // 🔥 DANO FIXO DE 50 + DANO DA ARMA
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
        // Progressão: a cada 3 monstros, sobe 1 tier
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
            // 🔥 APLICAR BÔNUS DE OURO DAS SKILLS
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
                // 🔥 NOVO: Tentar adicionar ao stash primeiro
                if (stashSystem.autoStoreItem(drop)) {
                    System.out.println("📦 " + drop.getName() + " armazenado no stash!");
                } else if (playerInventory.addItem(drop)) {
                    // Item adicionado ao inventário normal
                } else {
                    System.out.println("❌ Não foi possível armazenar: " + drop.getName());
                }


                // EQUIPAR AUTOMATICAMENTE SE FOR MELHOR
                if (drop instanceof Equipment) {
                    Equipment equipment = (Equipment) drop;
                    if (equipment.canEquip(player.getLevel())) {
                        // Simples lógica para equipar se for raro ou melhor
                        if (equipment.getRarity().ordinal() >= ItemRarity.RARE.ordinal()) {
                            player.equipItem(equipment);
                        }
                    }
                }
            }

            // 🔥 MOSTRAR SKILL TREE A CADA 10 LEVELS (FORA DO LOOP)
            if (player.getLevel() % 10 == 0) {
                player.displaySkills();
            }

            // Mostrar equipamento a cada 10 monstros
            if (monstersDefeated % 10 == 0) {
                player.displayEquipment();
            }

            // Mostrar inventário a cada 5 monstros derrotados
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


        // 🔥 MOSTRAR ARMA EQUIPADA
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



    // 🔥 MÉTODOS PARA ACESSAR OS MENUS
    public void showPlayerStatus() {
        System.out.println("\n=== STATUS DO JOGADOR ===");
        player.displayStatus();
    }

    // 🔥 NOVO: Tentar carregar autosave ao iniciar
    private void attemptLoadAutosave() {
        System.out.println("💾 Procurando save automático...");
        // Aqui você implementaria a lógica de carregamento
        // Por enquanto, vamos apenas inicializar um novo jogo
        System.out.println("🎮 Iniciando novo jogo...");
    }

    private void checkAutosave() {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastSave = currentTime - lastAutosaveTime;
        long autosaveIntervalMs = saveManager.getAutosaveInterval() * 60 * 1000; // Converter para ms

        if (timeSinceLastSave >= autosaveIntervalMs) {
            performAutosave();
            lastAutosaveTime = currentTime;
        }
    }

    // 🔥 NOVO: Realizar autosave
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

    // 🔥 ADICIONE ESTES MÉTODOS PARA SALVAMENTO MANUAL
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
        // Aqui você implementaria a lógica de carregamento completo
        // Por enquanto, vamos apenas mostrar uma mensagem
        System.out.println("🔄 Sistema de carregamento em desenvolvimento...");
        System.out.println("💡 Dica: O autosave funciona a cada " + saveManager.getAutosaveInterval() + " minutos!");
    }

    public void showSaveFiles() {
        saveManager.displaySaveFiles();
    }

    // 🔥 ADICIONE MÉTODOS PARA ESTATÍSTICAS
    public void showStatistics() {
        playerStatistics.displayStatistics();
    }

    public void showDetailedStatistics() {
        gameStatistics.displayDetailedStatistics();
    }

    // 🔥 ATUALIZE o método stopGame() para salvar ao sair
    public void stopGame() {
        gameRunning = false;

        // 🔥 SALVAR AO SAIR
        System.out.println("💾 Salvando antes de sair...");
        saveGame("exit_save");

        if (gameThread != null) {
            gameThread.interrupt();
        }
        System.out.println("⏹️ Jogo encerrado!");
        player.displayStatus();
        playerInventory.displayInventory();
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
