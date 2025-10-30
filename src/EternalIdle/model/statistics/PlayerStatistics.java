package EternalIdle.model.statistics;

import java.time.LocalDateTime;
import java.time.Duration;

public class PlayerStatistics {
    private long totalGoldEarned;
    private long totalGoldSpent;
    private long totalMonstersKilled;
    private long totalBossesKilled;
    private long totalItemsFound;
    private long totalCraftsCompleted;
    private long totalDamageDealt;
    private long totalDamageTaken;
    private LocalDateTime gameStartTime;
    private LocalDateTime lastSaveTime;

    public PlayerStatistics() {
        this.totalGoldEarned = 0;
        this.totalGoldSpent = 0;
        this.totalMonstersKilled = 0;
        this.totalBossesKilled = 0;
        this.totalItemsFound = 0;
        this.totalCraftsCompleted = 0;
        this.totalDamageDealt = 0;
        this.totalDamageTaken = 0;
        this.gameStartTime = LocalDateTime.now();
        this.lastSaveTime = LocalDateTime.now();
    }

    // Métodos para incrementar estatísticas
    public void addGoldEarned(long amount) { totalGoldEarned += amount; }
    public void addGoldSpent(long amount) { totalGoldSpent += amount; }
    public void addMonsterKilled() { totalMonstersKilled++; }
    public void addBossKilled() { totalBossesKilled++; }
    public void addItemFound() { totalItemsFound++; }
    public void addCraftCompleted() { totalCraftsCompleted++; }
    public void addDamageDealt(long damage) { totalDamageDealt += damage; }
    public void addDamageTaken(long damage) { totalDamageTaken += damage; }
    public void updateSaveTime() { lastSaveTime = LocalDateTime.now(); }

    // Cálculos derivados
    public long getNetGold() { return totalGoldEarned - totalGoldSpent; }
    public double getGoldEfficiency() {
        return totalGoldEarned > 0 ? (double) getNetGold() / totalGoldEarned : 0;
    }
    public double getKillsPerMinute() {
        Duration playTime = Duration.between(gameStartTime, LocalDateTime.now());
        long minutes = playTime.toMinutes();
        return minutes > 0 ? (double) totalMonstersKilled / minutes : totalMonstersKilled;
    }
    public String getPlayTime() {
        Duration playTime = Duration.between(gameStartTime, LocalDateTime.now());
        long hours = playTime.toHours();
        long minutes = playTime.toMinutes() % 60;
        return String.format("%dh %dm", hours, minutes);
    }

    public void displayStatistics() {
        System.out.println("\n📊 === ESTATÍSTICAS DO JOGADOR ===");
        System.out.println("⏰ Tempo de jogo: " + getPlayTime());
        System.out.println("💰 Ouro ganho: " + totalGoldEarned);
        System.out.println("💸 Ouro gasto: " + totalGoldSpent);
        System.out.println("📈 Ouro líquido: " + getNetGold() + " (" + String.format("%.1f", getGoldEfficiency() * 100) + "% eficiência)");
        System.out.println("👹 Monstros mortos: " + totalMonstersKilled);
        System.out.println("🐉 Bosses mortos: " + totalBossesKilled);
        System.out.println("🎒 Itens encontrados: " + totalItemsFound);
        System.out.println("🔨 Crafts completados: " + totalCraftsCompleted);
        System.out.println("⚔️ Dano causado: " + totalDamageDealt);
        System.out.println("🛡️ Dano recebido: " + totalDamageTaken);
        System.out.println("🎯 Mortes/minuto: " + String.format("%.2f", getKillsPerMinute()));
        System.out.println("💾 Último save: " + lastSaveTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm")));
        System.out.println("================================\n");
    }

    // Getters para salvamento
    public long getTotalGoldEarned() { return totalGoldEarned; }
    public long getTotalGoldSpent() { return totalGoldSpent; }
    public long getTotalMonstersKilled() { return totalMonstersKilled; }
    public long getTotalBossesKilled() { return totalBossesKilled; }
    public long getTotalItemsFound() { return totalItemsFound; }
    public long getTotalCraftsCompleted() { return totalCraftsCompleted; }
    public long getTotalDamageDealt() { return totalDamageDealt; }
    public long getTotalDamageTaken() { return totalDamageTaken; }
    public LocalDateTime getGameStartTime() { return gameStartTime; }
    public LocalDateTime getLastSaveTime() { return lastSaveTime; }

    // Setters para carregamento
    public void setTotalGoldEarned(long value) { totalGoldEarned = value; }
    public void setTotalGoldSpent(long value) { totalGoldSpent = value; }
    public void setTotalMonstersKilled(long value) { totalMonstersKilled = value; }
    public void setTotalBossesKilled(long value) { totalBossesKilled = value; }
    public void setTotalItemsFound(long value) { totalItemsFound = value; }
    public void setTotalCraftsCompleted(long value) { totalCraftsCompleted = value; }
    public void setTotalDamageDealt(long value) { totalDamageDealt = value; }
    public void setTotalDamageTaken(long value) { totalDamageTaken = value; }
    public void setGameStartTime(LocalDateTime time) { gameStartTime = time; }
    public void setLastSaveTime(LocalDateTime time) { lastSaveTime = time; }
}