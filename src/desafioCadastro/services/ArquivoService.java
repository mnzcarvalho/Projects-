package desafioCadastro.services;

import desafioCadastro.utils.Constantes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArquivoService {

    public static void criarPastaSeNaoExistir() {
        File pasta = new File(Constantes.PASTA_PET);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public static List<String> lerArquivo(String caminho) throws IOException {
        List<String> linhas = new ArrayList<>();
        if (Files.exists(Paths.get(caminho))) {
            linhas = Files.readAllLines(Paths.get(caminho));
        }
        return linhas;
    }

    public static void escreverArquivo(String caminho, List<String> conteudo) throws IOException {
        Files.write(Paths.get(caminho), conteudo);
    }

    public static void escreverArquivo(String caminho, String conteudo) throws IOException {
        Files.write(Paths.get(caminho), conteudo.getBytes());
    }

    public static boolean arquivoExiste(String caminho) {
        return Files.exists(Paths.get(caminho));
    }

    public static void deletarArquivo(String caminho) throws IOException {
        Files.deleteIfExists(Paths.get(caminho));
    }
}