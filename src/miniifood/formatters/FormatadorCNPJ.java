package miniifood.formatters;

public class FormatadorCNPJ {

    public static String formatar(String cnpj){
        if (!cnpj.matches("\\d{14}")){
            cnpj = "CNPJ inválido, deve conter 14 dígitos";
        } else {
            cnpj = cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        return cnpj;
    }

    public static String removerFormatacao(String cnpj){
        cnpj = cnpj.replaceAll("\\D", "");
        return "CNPJ sem formatação: " + cnpj;
    }
}
