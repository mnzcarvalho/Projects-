package desafioCadastro.services;

import desafioCadastro.utils.Constantes;
import java.io.IOException;
import java.util.List;

public class FormularioService {

    public static void exibirFormulario() throws IOException {
        List<String> perguntas = ArquivoService.lerArquivo(Constantes.FORMULARIO_FILE);
        for (String pergunta : perguntas) {
            System.out.println(pergunta);
        }
    }

    public static List<String> getPerguntas() throws IOException {
        return ArquivoService.lerArquivo(Constantes.FORMULARIO_FILE);
    }

    public static void adicionarPergunta(String novaPergunta) throws IOException {
        List<String> perguntas = getPerguntas();
        int numero = perguntas.size() + 1;
        perguntas.add(numero + " - " + novaPergunta);
        ArquivoService.escreverArquivo(Constantes.FORMULARIO_FILE, perguntas);
    }

    public static void alterarPergunta(int numeroPergunta, String novaPergunta) throws IOException {
        List<String> perguntas = getPerguntas();
        if (numeroPergunta > 0 && numeroPergunta <= perguntas.size()) {
            if (numeroPergunta <= 7) {
                System.out.println("Não é possível alterar as perguntas originais (1-7)");
                return;
            }
            perguntas.set(numeroPergunta - 1, numeroPergunta + " - " + novaPergunta);
            ArquivoService.escreverArquivo(Constantes.FORMULARIO_FILE, perguntas);
        }
    }

    public static void excluirPergunta(int numeroPergunta) throws IOException {
        List<String> perguntas = getPerguntas();
        if (numeroPergunta > 0 && numeroPergunta <= perguntas.size()) {
            if (numeroPergunta <= 7) {
                System.out.println("Não é possível excluir as perguntas originais (1-7)");
                return;
            }
            perguntas.remove(numeroPergunta - 1);
            // Reorganizar numeração
            for (int i = 0; i < perguntas.size(); i++) {
                String pergunta = perguntas.get(i);
                String textoPergunta = pergunta.substring(pergunta.indexOf("-") + 2);
                perguntas.set(i, (i + 1) + " - " + textoPergunta);
            }
            ArquivoService.escreverArquivo(Constantes.FORMULARIO_FILE, perguntas);
        }
    }
}