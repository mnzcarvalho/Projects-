package sistemaCityFlow.model;

public class Moto extends Veiculo{

    public Moto(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 80;
    }

    @Override
    public void mover() {
        this.estado = EstadoVeiculo.MOVENDO;
        System.out.println("Moto [" + placa + "] está trafegando a " + velocidade + " km/h");
    }
}
