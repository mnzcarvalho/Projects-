package miniifood.calculators;

public class CalculadoraDescontoCupom {

    public static double calcularDescontoPercentual(double valorPedido, double percentual){
        if (valorPedido < 0) {
            throw new IllegalArgumentException("Valor do pedido não pode ser negativo");
        }
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100");
        }

        double desconto = valorPedido * (percentual / 100);
        return formatarDuasCasasDecimais(desconto);
    }

    public static double calcularDescontoValorFixo(double valorPedido, double valorDesconto){
        if (valorPedido < 0) {
            throw new IllegalArgumentException("Valor do pedido não pode ser negativo");
        }
        if (valorDesconto < 0) {
            throw new IllegalArgumentException("Valor do desconto não pode ser negativo");
        }


        double desconto = Math.min(valorDesconto, valorPedido);
        return formatarDuasCasasDecimais(desconto);
    }


    public static double aplicarDesconto(double valorPedido, String tipoDesconto, double valorDesconto) {
        if (valorPedido < 0) {
            throw new IllegalArgumentException("Valor do pedido não pode ser negativo");
        }
        if (valorDesconto < 0) {
            throw new IllegalArgumentException("Valor do desconto não pode ser negativo");
        }

        double desconto;

        switch (tipoDesconto.toLowerCase()){
            case "percentual":
                desconto = calcularDescontoPercentual(valorPedido, valorDesconto);
                break;
            case "valor_fixo":
                desconto = calcularDescontoValorFixo(valorPedido, valorDesconto);
                break;
            default:
                throw new IllegalArgumentException("Tipo de desconto inválido. Use 'percentual' ou 'valor_fixo'");
        }
        return formatarDuasCasasDecimais(desconto);
    }

    private static double formatarDuasCasasDecimais(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
