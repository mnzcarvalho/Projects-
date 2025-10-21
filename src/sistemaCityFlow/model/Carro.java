package sistemaCityFlow.model;

public class Carro extends Veiculo {

    public Carro(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 60;
    }

    @Override
    public void mover(Cruzamento cruzamento) {
        this.estado = EstadoVeiculo.MOVENDO;
        cruzamento.tentarAtravessar(this);
    }
}
