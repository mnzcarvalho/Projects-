package sistemaCityFlow.model;

public class Moto extends Veiculo{

    public Moto(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 80;
    }

    @Override
    public void mover(Cruzamento cruzamento) {
        this.estado = EstadoVeiculo.MOVENDO;
        cruzamento.tentarAtravessar(this);
    }
}
