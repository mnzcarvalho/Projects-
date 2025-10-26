package miniifood.validators;

public class FormatadorCNPJ {
    public static String formatar(String cnpj) {
        // Remove formatação existente
        String cnpjLimpo = removerFormatacao(cnpj);

        // Verifica se tem 14 dígitos
        if (cnpjLimpo.length() != 14) {
            return "CNPJ deve conter 14 dígitos";
        }

        // Formata no padrão: 00.000.000/0000-00
        return cnpjLimpo.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    public static String removerFormatacao(String cnpj) {
        return cnpj.replaceAll("[^0-9]", "");
    }
}
