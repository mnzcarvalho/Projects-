package desafioCadastroDois.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArquivoService {

    public static List<String> lerArquivo(String caminho) throws IOException {
        List<String> linhas = new ArrayList<>();
        if (Files.exists(Paths.get(caminho))){
            linhas = Files.readAllLines(Paths.get(caminho));
        }
        return linhas;
    }
}
