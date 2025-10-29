package miniifood.validators;

public class ValidadorNota {

    public static boolean validar(int nota){
        return nota >= 1 && nota <= 5;
    }
}
