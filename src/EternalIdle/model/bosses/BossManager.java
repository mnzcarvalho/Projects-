package EternalIdle.bosses;

import EternalIdle.entity.Player;
import EternalIdle.rewards.BossReward;
import java.util.*;

public class BossManager {
    private List<Boss> availableBosses;
    private List<Boss> defeatedBosses;
    private Boss currentBoss;
    private Map<BossType, Integer> bossKillCount;
    private boolean bossSpawned;

    public BossManager() {
        this.availableBosses = new ArrayList<>();
        this.defeatedBosses = new ArrayList<>();
        this.bossKillCount = new HashMap<>();
        this.bossSpawned = false;
        initializeBosses();
    }

    private void initializeBosses() {
        // Adicionar todos os bosses disponíveis
        for (BossType bossType : BossType.values()) {
            if (!bossType.isEventBoss()) { // Bosses normais sempre disponíveis
                availableBosses.add(new Boss(bossType));
            }
        }
    }

    public boolean canSpawnBoss(Player player) {
        return !bossSpawned && player.getLevel() >= 20; // Bosses disponíveis a partir do nível 20
    }

    public void spawnRandomBoss(Player player) {
        if (!canSpawnBoss(player)) return;

        // Escolher boss baseado no nível do jogador
        List<Boss> eligibleBosses = new ArrayList<>();
        for (Boss boss : availableBosses) {
            if (boss.getLevel() <= player.getLevel() + 5) {
                eligibleBosses.add(boss);
            }
        }

        if (!eligibleBosses.isEmpty()) {
            currentBoss = eligibleBosses.get(new Random().nextInt(eligibleBosses.size()));
            currentBoss.startCombat();
            bossSpawned = true;

            System.out.println("\n🚨 🚨 🚨 BOSS SPAWNADO! 🚨 🚨 🚨");
            System.out.println("Prepare-se para enfrentar: " + currentBoss.getName());
            System.out.println("Nível: " + currentBoss.getLevel());
            System.out.println("Tempo limite: " + (currentBoss.getTimeLimit() / 60000) + " minutos");
        }
    }

    public void spawnSpecificBoss(BossType bossType) {
        currentBoss = new Boss(bossType);
        currentBoss.startCombat();
        bossSpawned = true;
    }

    public boolean defeatBoss(Player player) {
        if (currentBoss == null || !bossSpawned) return false;

        BossReward reward = currentBoss.getReward();
        reward.giveReward(player);

        // Estatísticas
        bossKillCount.put(currentBoss.getBossType(),
                bossKillCount.getOrDefault(currentBoss.getBossType(), 0) + 1);
        defeatedBosses.add(currentBoss);

        System.out.println("\n🎉 🎊 BOSS DERROTADO! 🎊 🎉");
        System.out.println("Você derrotou " + currentBoss.getName() + "!");

        currentBoss = null;
        bossSpawned = false;

        return true;
    }

    public void bossEscaped() {
        if (currentBoss != null && currentBoss.isTimeUp()) {
            System.out.println("⏰ " + currentBoss.getName() + " escapou! Tempo esgotado.");
            currentBoss = null;
            bossSpawned = false;
        }
    }

    public void displayAvailableBosses(Player player) {
        System.out.println("\n🐉 === BOSSES DISPONÍVEIS ===");

        for (Boss boss : availableBosses) {
            String status = boss.getLevel() <= player.getLevel() ? "✅" : "🔒";
            int kills = bossKillCount.getOrDefault(boss.getBossType(), 0);

            System.out.println(status + " " + boss.getBossType().getEmoji() + " " + boss.getName());
            System.out.println("   Nível: " + boss.getLevel() + " | Derrotas: " + kills);
            System.out.println("   " + boss.getBossType().getDescription());
            System.out.println();
        }

        if (currentBoss != null) {
            System.out.println("🚨 BOSS ATUAL: " + currentBoss.getName());
            System.out.println("   Vida: " + currentBoss.getHealth() + "/" + currentBoss.getMaxHealth());
            System.out.println("   Tempo restante: " + (currentBoss.getRemainingTime() / 60000) + "min");
        }

        System.out.println("============================\n");
    }

    // Getters
    public Boss getCurrentBoss() { return currentBoss; }
    public boolean isBossSpawned() { return bossSpawned; }
    public List<Boss> getDefeatedBosses() { return defeatedBosses; }
    public int getTotalBossKills() {
        return bossKillCount.values().stream().mapToInt(Integer::intValue).sum();
    }
}