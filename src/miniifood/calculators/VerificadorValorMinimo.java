package miniifood.calculators;

public class VerificadorValorMinimo {
    public static boolean atingiuMinimo(double valorPedido, double valorMinimo){
        validarValores(valorPedido, valorMinimo);
        return valorPedido >= valorMinimo;
    }

    public static double quantoFalta(double valorPedido, double valorMinimo){
        if (valorPedido >= valorMinimo){
            return 0.0;
        }

        double falta = valorMinimo - valorPedido;
        return formatarDuasCasasDecimais(falta);
    }

    public static double percentualAtingido(double valorPedido, double valorMinimo){
        validarValores(valorPedido, valorMinimo);

        if (valorMinimo == 0) {
            return 100.0; // Se o mínimo é zero, sempre atingiu 100%
        }

        double percentual = (valorPedido / valorMinimo) * 100;

        if (percentual > 100){
            percentual = 100;
        }

        return formatarDuasCasasDecimais(percentual);
    }

    public static String obterMensagem(double valorPedido, double valorMinimo) {
        validarValores(valorPedido, valorMinimo);

        if (atingiuMinimo(valorPedido, valorMinimo)) {
            return "Mínimo ATINGIDO ✓\nVocê pode finalizar o pedido!";
        } else {
            double falta = quantoFalta(valorPedido, valorMinimo);
            return "NÃO ATINGIU o mínimo\nAdicione mais R$ " + String.format("%.2f", falta) + " para finalizar o pedido";
        }
    }

    private static void validarValores(double valorPedido, double valorMinimo) {
        if (valorPedido < 0) {
            throw new IllegalArgumentException("Valor do pedido não pode ser negativo");
        }
        if (valorMinimo < 0) {
            throw new IllegalArgumentException("Valor mínimo não pode ser negativo");
        }
    }

    private static double formatarDuasCasasDecimais(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
