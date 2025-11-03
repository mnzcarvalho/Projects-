package miniifood.generetors;
/**
 * Classe para geração e verificação de hash de senhas
 *
 * IMPORTANTE: Este é um metodo simples usando hashCode() para fins didáticos.
 * EM PRODUÇÃO, deve-se usar algoritmos seguros como BCrypt ou SHA-256
 * com salt, pois hashCode() é vulnerável a ataques de colisão e não é seguro
 * para armazenamento de senhas em sistemas reais.
 */
public class GeradorHashSenha {

    public static String gerar(String senha) {
        if (senha == null) {
            senha = ""; //Evitar NullpointerException
        }

        int hash = Math.abs(senha.hashCode());

        return String.valueOf(hash);
    }

    public static boolean verificar(String senha, String hash) {
        if (senha == null || hash == null) {
            return false;
        }
        String hashGerado = gerar(senha);
        return hashGerado.equals(hash);
    }
}

