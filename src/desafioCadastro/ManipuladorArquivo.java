package desafioCadastro;

import java.io.*;

public class ManipuladorArquivo {

    public static void leitorArquivo() throws IOException {
        File file = new File("D:/Dudu/CODE/PROJECTS/src/desafioCadastro/formulario.txt");
        String linha;
    try (FileReader fr = new FileReader(file);
         BufferedReader leitor = new BufferedReader(fr);) {
        while (((linha = leitor.readLine()) != null)){
            System.out.println(linha);
        }
    } catch (IOException e) {
        throw new IOException(e);
    }
    }
}