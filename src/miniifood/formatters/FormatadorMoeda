package miniifood.formatters;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorMoeda {
    private static final NumberFormat FORMATADOR =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static String formatar(double valor) {
        return FORMATADOR.format(valor);
    }
}
