package miniifood.validators;

public class ValidadorEmail {
    public static boolean validar(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Verificar se contém exatamente um @
        int posicaoArroba = email.indexOf('@');
        if (posicaoArroba == -1 || posicaoArroba != email.lastIndexOf('@')) {
            return false;
        }

        // Verificar se tem texto antes e depois do @
        if (posicaoArroba == 0 || posicaoArroba == email.length() - 1) {
            return false;
        }

        // Verificar se tem pelo menos um ponto após o @
        String parteAposArroba = email.substring(posicaoArroba + 1);
        if (parteAposArroba.indexOf('.') == -1) {
            return false;
        }

        // Verificar se não termina com ponto
        if (email.charAt(email.length() - 1) == '.') {
            return false;
        }

        // Verificar se não tem espaços
        if (email.contains(" ")) {
            return false;
        }

        return true;
    }
}