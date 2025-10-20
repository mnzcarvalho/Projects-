package sistemaCityFlow.model;

public class Carro extends Veiculo {

    public Carro(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 60;
    }

    @Override
    public void mover() {
        this.estado = EstadoVeiculo.MOVENDO;
        System.out.println("Carro [" + placa + "] está se movendo a " + velocidade + " km/h");
    }
}
