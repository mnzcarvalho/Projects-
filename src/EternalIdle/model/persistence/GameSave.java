package EternalIdle.persistence;

import EternalIdle.entity.Player;
import EternalIdle.statistics.PlayerStatistics;
import EternalIdle.statistics.GameStatistics;
import java.time.LocalDateTime;

public class GameSave {
    private String saveName;
    private LocalDateTime saveDate;
    private Player playerData;
    private PlayerStatistics playerStats;
    private GameStatistics gameStats;
    private int version;

    public GameSave(String saveName) {
        this.saveName = saveName;
        this.saveDate = LocalDateTime.now();
        this.version = 1; // Versão do formato de save
    }

    // Getters e Setters
    public String getSaveName() { return saveName; }
    public LocalDateTime getSaveDate() { return saveDate; }
    public Player getPlayerData() { return playerData; }
    public PlayerStatistics getPlayerStats() { return playerStats; }
    public GameStatistics getGameStats() { return gameStats; }
    public int getVersion() { return version; }

    public void setSaveName(String name) { saveName = name; }
    public void setSaveDate(LocalDateTime date) { saveDate = date; }
    public void setPlayerData(Player player) { playerData = player; }
    public void setPlayerStats(PlayerStatistics stats) { playerStats = stats; }
    public void setGameStats(GameStatistics stats) { gameStats = stats; }
    public void setVersion(int v) { version = v; }

    public void displaySaveInfo() {
        System.out.println("💾 " + saveName);
        System.out.println("   Data: " + saveDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        if (playerData != null) {
            System.out.println("   Jogador: " + playerData.getName() + " Nível " + playerData.getLevel());
        }
        if (playerStats != null) {
            System.out.println("   Progresso: " + playerStats.getTotalMonstersKilled() + " monstros mortos");
        }
    }
}