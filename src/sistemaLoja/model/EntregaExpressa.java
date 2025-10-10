package sistemaLoja.model;

public class EntregaExpressa extends Entrega{
    private static final double TAXA_ADICIONAL = 50.0;

    public EntregaExpressa(String enderecoDestino) {
        super(enderecoDestino);
        calcularDataPrevista();
    }

    @Override
    public void calcularDataPrevista() {
        this.dataPrevista = dataEnvio.plusDays(2);
    }

    public double getTaxaAdicional(){
        return TAXA_ADICIONAL;
    }

    @Override
    public String toString() {
        return "\nEntrega Expressa para: " + enderecoDestino +
                " (prevista: " + getDataPrevistaFormatada() +
                ", taxa: R$ " + TAXA_ADICIONAL + ")";
    }
}
