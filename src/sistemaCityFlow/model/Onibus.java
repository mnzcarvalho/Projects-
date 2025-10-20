package sistemaCityFlow.model;

public class Onibus extends Veiculo{

    public Onibus(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 40;
    }

    @Override
    public void mover() {
        this.estado = EstadoVeiculo.MOVENDO;
        System.out.println("Ônibus [" + placa + "] está trafegando a " + velocidade + " km/h");
    }
}
