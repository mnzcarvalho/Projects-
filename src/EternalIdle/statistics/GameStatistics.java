package EternalIdle.statistics;

import java.util.HashMap;
import java.util.Map;

public class GameStatistics {
    private Map<String, Integer> monsterKillCount;
    private Map<String, Integer> itemObtainedCount;
    private Map<String, Integer> bossKillCount;
    private int totalRebirths;
    private long totalPlayTimeSeconds;

    public GameStatistics() {
        this.monsterKillCount = new HashMap<>();
        this.itemObtainedCount = new HashMap<>();
        this.bossKillCount = new HashMap<>();
        this.totalRebirths = 0;
        this.totalPlayTimeSeconds = 0;
    }

    public void addMonsterKill(String monsterName) {
        monsterKillCount.put(monsterName, monsterKillCount.getOrDefault(monsterName, 0) + 1);
    }

    public void addItemObtained(String itemName) {
        itemObtainedCount.put(itemName, itemObtainedCount.getOrDefault(itemName, 0) + 1);
    }

    public void addBossKill(String bossName) {
        bossKillCount.put(bossName, bossKillCount.getOrDefault(bossName, 0) + 1);
    }

    public void addRebirth() { totalRebirths++; }
    public void addPlayTime(long seconds) { totalPlayTimeSeconds += seconds; }

    public void displayDetailedStatistics() {
        System.out.println("\n📈 === ESTATÍSTICAS DETALHADAS ===");

        System.out.println("👹 MONSTROS MAIS MORTOS:");
        monsterKillCount.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .forEach(entry -> System.out.println("   " + entry.getKey() + ": " + entry.getValue()));

        System.out.println("🎒 ITENS MAIS OBTIDOS:");
        itemObtainedCount.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .forEach(entry -> System.out.println("   " + entry.getKey() + ": " + entry.getValue()));

        System.out.println("🐉 BOSSES DERROTADOS:");
        bossKillCount.forEach((boss, kills) ->
                System.out.println("   " + boss + ": " + kills + " vezes"));

        System.out.println("🔄 Total de Rebirths: " + totalRebirths);
        System.out.println("⏰ Tempo total de jogo: " + (totalPlayTimeSeconds / 3600) + "h " + ((totalPlayTimeSeconds % 3600) / 60) + "m");
        System.out.println("==================================\n");
    }

    // Getters e Setters para salvamento
    public Map<String, Integer> getMonsterKillCount() { return monsterKillCount; }
    public Map<String, Integer> getItemObtainedCount() { return itemObtainedCount; }
    public Map<String, Integer> getBossKillCount() { return bossKillCount; }
    public int getTotalRebirths() { return totalRebirths; }
    public long getTotalPlayTimeSeconds() { return totalPlayTimeSeconds; }

    public void setMonsterKillCount(Map<String, Integer> map) { monsterKillCount = map; }
    public void setItemObtainedCount(Map<String, Integer> map) { itemObtainedCount = map; }
    public void setBossKillCount(Map<String, Integer> map) { bossKillCount = map; }
    public void setTotalRebirths(int value) { totalRebirths = value; }
    public void setTotalPlayTimeSeconds(long value) { totalPlayTimeSeconds = value; }
}