package miniifood.validators;

import java.util.regex.Pattern;

public class ValidadorCPF {
    public static boolean validar(String cpf) {
        // Remove caracteres especiais (pontos e traços)
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem exatamente 11 dígitos
        if (!Pattern.matches("\\d{11}", cpfLimpo)) {
            return false;
        }

        // Verifica se não são todos dígitos iguais
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        return true;
    }
}
