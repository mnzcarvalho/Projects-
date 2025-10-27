package miniifood.validators;

//by Gabriel Augusto
public class ValidadorCPF {
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // remove tudo que não é número
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false; // verifica tamanho e repetição de números iguais
        }

        int[] pesos1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int soma1 = 0;
            for (int i = 0; i < 9; i++) {
                soma1 += Character.getNumericValue(cpf.charAt(i)) * pesos1[i];
            }
            int dig1 = 11 - (soma1 % 11);
            if (dig1 > 9) dig1 = 0;

            int soma2 = 0;
            for (int i = 0; i < 10; i++) {
                soma2 += Character.getNumericValue(cpf.charAt(i)) * pesos2[i];
            }
            int dig2 = 11 - (soma2 % 11);
            if (dig2 > 9) dig2 = 0;

            return dig1 == Character.getNumericValue(cpf.charAt(9)) &&
                    dig2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}