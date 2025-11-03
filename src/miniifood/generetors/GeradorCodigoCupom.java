package miniifood.generetors;

import java.util.Random;

public class GeradorCodigoCupom {

    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final Random random = new Random();

    public static String gerar(String prefixo){
        if (prefixo == null || prefixo.trim().isEmpty()){
            return gerarSemPrefixo();
        }

        String letrasAleatorias = gerarCaracteresAleatorios(LETRAS, 4);
        String numerosAleatorios = gerarCaracteresAleatorios(NUMEROS, 4);

        return prefixo.trim().toUpperCase() + letrasAleatorias + numerosAleatorios;
    }

    public static String gerarSemPrefixo(){

        String letrasAleatorias = gerarCaracteresAleatorios(LETRAS, 4);
        String numerosAleatorios = gerarCaracteresAleatorios(NUMEROS, 4);

        return letrasAleatorias + numerosAleatorios;
    }

    private static String gerarCaracteresAleatorios(String caracteresPermitidos, int quantidade){
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < quantidade; i++) {
            int indiceAleatorio = random.nextInt(caracteresPermitidos.length());
            char caractereAleatorio = caracteresPermitidos.charAt(indiceAleatorio);
            resultado.append(caractereAleatorio);
        }

        return resultado.toString();
    }
}
