package miniifood.formatters;

public class FormatadorCPF {

    public static String formatar(String cpf){
        if (!cpf.matches("\\d{11}")){
            cpf = "CPF inválido, deve conter 11 dígitos";
        } else {
            cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }

    public static String removerFormatacao(String cpf){
        cpf = cpf.replaceAll("\\D", "");
        return "CPF sem formatação: " + cpf;
    }
}
