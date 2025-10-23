package sistemaCityFlow.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarPlaca {

    private static final String PADRAO_PLACA = "^[A-Z]{3}-?\\d[A-Z\\d]\\d{2}$";

    public static boolean validar(String placa){
        Pattern pattern = Pattern.compile(PADRAO_PLACA);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }

}
