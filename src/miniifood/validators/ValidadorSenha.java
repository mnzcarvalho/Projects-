package miniifood.validators;
//        /^                    // indica o inicio da string
//        (?=.*\d)              // deve conter ao menos um dígito
//        (?=.*[a-z])           // deve conter ao menos uma letra minúscula
//        (?=.*[A-Z])           // deve conter ao menos uma letra maiúscula
//        (?=.*[$*&@#])         // deve conter ao menos um caractere especial
//        [0-9a-zA-Z$*&@#]{8,}  // deve conter ao menos 8 dos caracteres mencionados
//        $/                    // indica o fim da string

public class ValidadorSenha {

    public static boolean validar(String senha) {
        return senha != null && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    public static String obterMensagemErro(String senha) {
        if (senha == null) {
            return "Senha não pode ser nula";
        }

        if (senha.length() < 8) {
            return "A senha deve ter no mínimo 8 caracteres";
        }

        if (!senha.matches(".*[A-Z].*")) {
            return "A senha deve conter pelo menos uma letra maiúscula";
        }

        if (!senha.matches(".*[a-z].*")) {
            return "A senha deve conter pelo menos uma letra minúscula";
        }

        if (!senha.matches(".*\\d.*")) {
            return "A senha deve conter pelo menos um número";
        }

        return null;
    }
}
