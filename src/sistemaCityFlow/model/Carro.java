package sistemaCityFlow.model;

import sistemaCityFlow.exception.VeiculoSemCombustivelException;

public class Carro extends Veiculo {

    public Carro(String placa, TipoCombustivel combustivel) {
        super(placa, combustivel);
        this.velocidade = 60;
    }

    @Override
    public void mover(Cruzamento cruzamento) {
        this.estado = EstadoVeiculo.MOVENDO;

        try {
            consumirCombustivel();
            cruzamento.tentarAtravessar(this);

        } catch (VeiculoSemCombustivelException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
