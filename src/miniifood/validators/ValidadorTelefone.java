package miniifood.validators;

public class ValidadorTelefone {

    public static boolean validar(String telefone) {
        // Remove caracteres especiais: parênteses, traços e espaços
        String numeroLimpo = telefone.replaceAll("[()\\-\\s]", "");

        // Verifica se contém apenas dígitos
        if (!numeroLimpo.matches("\\d+")) {
            return false;
        }

        // Verifica o tamanho do número (10 para fixo, 11 para celular)
        int tamanho = numeroLimpo.length();
        if (tamanho != 10 && tamanho != 11) {
            return false;
        }

        // Verifica se o DDD é válido (11 a 99)
        int ddd = Integer.parseInt(numeroLimpo.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            return false;
        }

        // Validações específicas para celular (11 dígitos)
        if (tamanho == 11) {
            // Para celular, o terceiro dígito deve ser 9
            char terceiroDigito = numeroLimpo.charAt(2);
            return terceiroDigito == '9';
        }

        // Validações específicas para telefone fixo (10 dígitos)
        if (tamanho == 10) {
            // Para fixo, o terceiro dígito NÃO deve ser 9
            char terceiroDigito = numeroLimpo.charAt(2);
            return terceiroDigito != '9';
        }

        return false;
    }
}