package desafioCadastro.utils;

import javax.sound.midi.Patch;
import java.util.regex.Pattern;

public class ValidacaoUtils {

    public static boolean isValidNome(String nome){
        return Pattern.matches("^[a-zA-ZÀ-ÿ\\s]+$", nome);
    }

    public static boolean isValidRaca(String raca){
        return Pattern.matches("^[a-zA-ZÀ-ÿ\\s]+$", raca);
    }

    public static boolean isValidPeso(double peso){
        return peso >= 0 && peso <= 60;
    }

    public static boolean isValidIdade(double idade) {
        return idade >= 0 && idade <= 20.0;
    }

    public static boolean isNumero (String input){
        return Pattern.matches("^[0-9.,]+$", input);
    }

    public static double parseDouble(String input){
        input = input.replace(',', '.');
        return Double.parseDouble(input);
    }

}
