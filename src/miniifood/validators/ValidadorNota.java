package miniifood.validators;

public class ValidadorNota {

    public static boolean validar(int nota){
         if(nota >= 1 && nota <= 5){
             return true;
        }
         return false;
    }
}
