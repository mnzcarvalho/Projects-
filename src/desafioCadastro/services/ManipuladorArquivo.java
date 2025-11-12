package desafioCadastro.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorArquivo {
    List<String> dadosPet = new ArrayList<>();

    public void leitorArquivo() throws IOException {
        File file = new File("D:/CODE/JAVA/Projects-/src/desafioCadastro/formulario.txt");
        String linha;

    try (FileReader fr = new FileReader(file);
         BufferedReader leitor = new BufferedReader(fr)) {
        while (((linha = leitor.readLine()) != null)){
            this.dadosPet.add(linha);
            System.out.println(linha);
        }
    } catch (IOException e) {
        throw new IOException(e);
    }
    }

    public List<String> getDadosPet() {
        return dadosPet;
    }
}