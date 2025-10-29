package miniifood.validators;

public class ValidadorNomeCompleto {

    public static boolean validar(String nome){
        if (nome.isEmpty() || !nome.trim().contains(" ")){
            return false;
        }
        //Se contem apenas letras, espaços e acentos
        if (!nome.matches("^[\\p{L}\\s]+$")){
            return false;
        }

        String [] partes = nome.trim().split(" ");
        if (partes[0].length() < 2 && partes[1].length() < 2){
            return false;
        }
        return true;
    }
}
