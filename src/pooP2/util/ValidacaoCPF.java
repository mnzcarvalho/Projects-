package pooP2.util;

public class ValidacaoCPF {
    public static boolean validar(String cpf) {
        if(cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }
        
        // Simulação simples de validação
        return !cpf.matches("(\\d)\\1{10}"); // Não pode ter todos dígitos iguais
    }
}
