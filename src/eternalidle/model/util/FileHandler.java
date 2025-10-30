package eternalidle.model.util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String SAVE_DIRECTORY = "saves/";
    private static final String SAVE_EXTENSION = ".eternalidle";

    static {
        // Criar diretório de saves se não existir
        try {
            Files.createDirectories(Paths.get(SAVE_DIRECTORY));
        } catch (IOException e) {
            System.err.println("❌ Erro ao criar diretório de saves: " + e.getMessage());
        }
    }

    public static boolean saveGame(String saveName, String data) {
        try {
            String filename = SAVE_DIRECTORY + saveName + SAVE_EXTENSION;
            Files.write(Paths.get(filename), data.getBytes());
            System.out.println("✅ Jogo salvo: " + saveName);
            return true;
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar jogo: " + e.getMessage());
            return false;
        }
    }

    public static String loadGame(String saveName) {
        try {
            String filename = SAVE_DIRECTORY + saveName + SAVE_EXTENSION;
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.err.println("❌ Erro ao carregar jogo: " + e.getMessage());
            return null;
        }
    }

    public static List<String> getSaveFiles() {
        List<String> saveFiles = new ArrayList<>();
        try {
            Files.list(Paths.get(SAVE_DIRECTORY))
                    .filter(path -> path.toString().endsWith(SAVE_EXTENSION))
                    .forEach(path -> {
                        String filename = path.getFileName().toString();
                        saveFiles.add(filename.substring(0, filename.length() - SAVE_EXTENSION.length()));
                    });
        } catch (IOException e) {
            System.err.println("❌ Erro ao listar saves: " + e.getMessage());
        }
        return saveFiles;
    }

    public static boolean deleteSave(String saveName) {
        try {
            String filename = SAVE_DIRECTORY + saveName + SAVE_EXTENSION;
            return Files.deleteIfExists(Paths.get(filename));
        } catch (IOException e) {
            System.err.println("❌ Erro ao deletar save: " + e.getMessage());
            return false;
        }
    }

    public static boolean backupSave(String saveName) {
        try {
            String original = SAVE_DIRECTORY + saveName + SAVE_EXTENSION;
            String backup = SAVE_DIRECTORY + saveName + "_backup" + SAVE_EXTENSION;
            Files.copy(Paths.get(original), Paths.get(backup), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📦 Backup criado: " + saveName + "_backup");
            return true;
        } catch (IOException e) {
            System.err.println("❌ Erro ao criar backup: " + e.getMessage());
            return false;
        }
    }
}