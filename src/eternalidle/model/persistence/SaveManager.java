package eternalidle.model.persistence;

import eternalidle.model.entity.Player;
import eternalidle.model.statistics.PlayerStatistics;
import eternalidle.model.statistics.GameStatistics;
import eternalidle.model.util.FileHandler;
import java.util.*;

public class SaveManager {
    private static final String AUTOSAVE_NAME = "autosave";
    private boolean autosaveEnabled;
    private int autosaveInterval; // em minutos

    public SaveManager() {
        this.autosaveEnabled = true;
        this.autosaveInterval = 5; // Salvar a cada 5 minutos
    }

    public boolean saveGame(String saveName, Player player, PlayerStatistics playerStats, GameStatistics gameStats) {
        try {
            // Criar objeto de save
            GameSave save = new GameSave(saveName);
            save.setPlayerData(clonePlayer(player));
            save.setPlayerStats(clonePlayerStats(playerStats)); // 🔥 CORRIGIDO: PlayerStatistics
            save.setGameStats(cloneGameStats(gameStats));

            // Converter para JSON (simplificado)
            String saveData = convertToSimpleFormat(save);

            // Salvar no arquivo
            return FileHandler.saveGame(saveName, saveData);

        } catch (Exception e) {
            System.err.println("❌ Erro no processo de save: " + e.getMessage());
            return false;
        }
    }

    public GameSave loadGame(String saveName) {
        try {
            String saveData = FileHandler.loadGame(saveName);
            if (saveData == null) return null;

            return parseFromSimpleFormat(saveData);

        } catch (Exception e) {
            System.err.println("❌ Erro no processo de load: " + e.getMessage());
            return null;
        }
    }

    public void autosaveIfNeeded(Player player, PlayerStatistics playerStats, GameStatistics gameStats) {
        if (!autosaveEnabled) return;

        // Lógica de autosave baseada em tempo seria implementada aqui
        // Por enquanto, vamos fazer um autosave manual
        saveGame(AUTOSAVE_NAME, player, playerStats, gameStats);
    }

    public void displaySaveFiles() {
        List<String> saveFiles = FileHandler.getSaveFiles();

        System.out.println("\n💾 === ARQUIVOS DE SAVE ===");
        if (saveFiles.isEmpty()) {
            System.out.println("   Nenhum save encontrado.");
        } else {
            for (int i = 0; i < saveFiles.size(); i++) {
                System.out.print((i + 1) + ". ");
                GameSave save = loadGame(saveFiles.get(i));
                if (save != null) {
                    save.displaySaveInfo();
                } else {
                    System.out.println(saveFiles.get(i) + " (corrompido)");
                }
            }
        }
        System.out.println("===========================\n");
    }

    // 🔥 MÉTODO SIMPLIFICADO - Em produção use uma biblioteca JSON
    private String convertToSimpleFormat(GameSave save) {
        StringBuilder sb = new StringBuilder();
        sb.append("SAVE_VERSION:1\n");
        sb.append("SAVE_NAME:").append(save.getSaveName()).append("\n");
        sb.append("SAVE_DATE:").append(save.getSaveDate()).append("\n");

        // Dados básicos do jogador (simplificado)
        if (save.getPlayerData() != null) {
            Player p = save.getPlayerData();
            sb.append("PLAYER_NAME:").append(p.getName()).append("\n");
            sb.append("PLAYER_LEVEL:").append(p.getLevel()).append("\n");
            sb.append("PLAYER_GOLD:").append(p.getGold()).append("\n");
            sb.append("PLAYER_EXP:").append(p.getExperience()).append("\n");
        }

        // Estatísticas (simplificado)
        if (save.getPlayerStats() != null) {
            PlayerStatistics ps = save.getPlayerStats(); // 🔥 CORRIGIDO: PlayerStatistics
            sb.append("STATS_MONSTERS:").append(ps.getTotalMonstersKilled()).append("\n");
            sb.append("STATS_GOLD_EARNED:").append(ps.getTotalGoldEarned()).append("\n");
        }

        return sb.toString();
    }

    private GameSave parseFromSimpleFormat(String data) {
        GameSave save = new GameSave("temp");
        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1];

                switch (key) {
                    case "SAVE_NAME":
                        save.setSaveName(value);
                        break;
                    case "PLAYER_NAME":
                        // Recriar objeto Player com dados básicos
                        break;
                    // ... outros casos
                }
            }
        }

        return save;
    }

    // 🔥 Métodos de clone CORRIGIDOS
    private Player clonePlayer(Player original) {
        // Implementar clone do jogador (simplificado)
        Player clone = new Player(original.getName());
        clone.addGold(original.getGold());
        // Para cada nível acima de 1, adicionar experiência equivalente
        for (int i = 1; i < original.getLevel(); i++) {
            clone.addExperience(i * 100L);
        }
        return clone;
    }

    private PlayerStatistics clonePlayerStats(PlayerStatistics original) {
        PlayerStatistics clone = new PlayerStatistics(); // 🔥 CORRIGIDO: PlayerStatistics
        clone.setTotalGoldEarned(original.getTotalGoldEarned());
        clone.setTotalGoldSpent(original.getTotalGoldSpent());
        clone.setTotalMonstersKilled(original.getTotalMonstersKilled());
        clone.setTotalBossesKilled(original.getTotalBossesKilled());
        clone.setTotalItemsFound(original.getTotalItemsFound());
        clone.setTotalCraftsCompleted(original.getTotalCraftsCompleted());
        clone.setTotalDamageDealt(original.getTotalDamageDealt());
        clone.setTotalDamageTaken(original.getTotalDamageTaken());
        clone.setGameStartTime(original.getGameStartTime());
        clone.setLastSaveTime(original.getLastSaveTime());
        return clone;
    }

    private GameStatistics cloneGameStats(GameStatistics original) {
        GameStatistics clone = new GameStatistics();
        clone.setTotalRebirths(original.getTotalRebirths());
        clone.setTotalPlayTimeSeconds(original.getTotalPlayTimeSeconds());
        clone.setMonsterKillCount(new HashMap<>(original.getMonsterKillCount()));
        clone.setItemObtainedCount(new HashMap<>(original.getItemObtainedCount()));
        clone.setBossKillCount(new HashMap<>(original.getBossKillCount()));
        return clone;
    }

    // Getters e Setters
    public boolean isAutosaveEnabled() { return autosaveEnabled; }
    public void setAutosaveEnabled(boolean enabled) { autosaveEnabled = enabled; }
    public int getAutosaveInterval() { return autosaveInterval; }
    public void setAutosaveInterval(int minutes) { autosaveInterval = minutes; }
}