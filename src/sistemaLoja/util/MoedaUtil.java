package sistemaLoja.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {
    public static String formatar(double valor){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }
}
