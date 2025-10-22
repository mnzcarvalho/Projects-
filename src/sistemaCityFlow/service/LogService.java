package sistemaCityFlow.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {
    private static final String LOG_DIR = "resources/logs";
    private static final String LOG_FILE = "cityflow.log";
    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path logoPath;

    public LogService() {
        try {
            Path dir = Paths.get(LOG_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            logoPath = dir.resolve(LOG_FILE);
            if (!Files.exists(logoPath)) {
                Files.createFile(logoPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível inicializar LogService", e);
        }
    }

    public synchronized void escrever(String nivel, String mensagem) {
        String linha = String.format("%s [%s] %s%n", LocalDateTime.now().format(TF), nivel, mensagem);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logoPath.toFile(), true))) {
            bw.write(linha);
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }
    }

    public void info(String mensagem) {
        escrever("INFO", mensagem);
    }

    public void warn(String mensagem) {
        escrever("WARN", mensagem);
    }

    public void error(String mensagem) {
        escrever("ERROR", mensagem);
    }

    public synchronized String lerTodos() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(logoPath.toFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                sb.append(linha).append(System.lineSeparator());
            }
        } catch (IOException e) {
            sb.append("Erro lendo logs: ").append(e.getMessage());
        }
        return sb.toString();
    }

    public String getLogPath() {
        return logoPath.toAbsolutePath().toString();
    }

}
