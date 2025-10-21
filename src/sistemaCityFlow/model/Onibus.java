package sistemaCityFlow.model;

public class Onibus extends Veiculo{

    public Onibus(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 40;
    }



    @Override
    public void mover(Cruzamento cruzamento) {
        this.estado = EstadoVeiculo.MOVENDO;
        cruzamento.tentarAtravessar(this);
    }
}
