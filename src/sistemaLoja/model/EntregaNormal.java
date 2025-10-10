package sistemaLoja.model;

public class EntregaNormal extends Entrega {

    public EntregaNormal(String enderecoDestino) {
        super(enderecoDestino);
        calcularDataPrevista();
    }

    @Override
    public void calcularDataPrevista() {
        this.dataPrevista = dataPrevista.plusDays(5);
    }
}
