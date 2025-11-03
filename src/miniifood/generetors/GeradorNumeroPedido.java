package miniifood.generetors;

import java.time.LocalDate;

public class GeradorNumeroPedido {
    private static int contador = 0;
    private static LocalDate dataAtual = LocalDate.now();

    public static String gerar(){
        return gerarComPrefixo("PED");
    }

    public static String gerarComPrefixo(String prefixo){
        LocalDate hoje = LocalDate.now();

        if (!hoje.equals(dataAtual)){
            contador = 0;
            dataAtual = hoje;
        }

        contador++;

        String dataFormatada = hoje.toString().replace("-", "");

        String sequencial = String.format("%03d", contador);

        return prefixo + dataFormatada + sequencial;
    }
}
