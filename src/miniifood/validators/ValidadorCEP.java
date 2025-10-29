package miniifood.validators;

public class ValidadorCEP {

    public static boolean validarCEP(String cep){
        String cepLimpo = cep.replace("-", "");

        if (cepLimpo.length() != 8){
        return false;
        }

        if (!cepLimpo.matches("\\d+")){
            return false;
        }

        if (cepLimpo.equals("00000000")){
            return false;
        }

        return true;
    }
}
