package miniifood.calculators;

import java.util.ArrayList;

public class CalculadoraPedido {
    public static double calcularSubtotal(ArrayList<Double> precosProdutos){
        double subtotal = 0.0;
        for (Double preco : precosProdutos) {
            if (preco < 0){
                throw new IllegalArgumentException("Preços não podem ser negativos");
            }
            subtotal += preco;
        }
        return formatarDuasCasasDecimais(subtotal);
    }

    public static double aplicarDesconto(double subtotal, double desconto){
        if (subtotal < 0 || desconto < 0 ){
            throw new IllegalArgumentException("Valores não podem ser negativos");
        }
        if (desconto > subtotal){
            desconto = subtotal;
        }

        double valorComDesconto = subtotal - desconto;
        return formatarDuasCasasDecimais(valorComDesconto);
    }

    public static double adicionarTaxa(double valor, double taxa){
        if (valor < 0 || taxa < 0){
            throw new IllegalArgumentException("Valores não podem ser negativos");
        }

        double valorComTaxa = valor + taxa;
        return formatarDuasCasasDecimais(valorComTaxa);
    }

    public static double calcularTotal (double subtotal, double desconto, double taxa){
        if (subtotal < 0 || desconto < 0 || taxa < 0) {
            throw new IllegalArgumentException("Valores não podem ser negativos");
        }
        double valorAposDesconto = subtotal - desconto;
        if (valorAposDesconto < 0) {
            valorAposDesconto = 0;
        }

        double total = valorAposDesconto + taxa;
        return formatarDuasCasasDecimais(total);
    }

    private static double formatarDuasCasasDecimais(double valor){
        return Math.round(valor * 100.0) / 100.0;
    }


}
