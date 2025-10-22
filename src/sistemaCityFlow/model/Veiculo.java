package sistemaCityFlow.model;

import sistemaCityFlow.exception.VeiculoSemCombustivelException;

import java.time.LocalTime;

public abstract class Veiculo implements Runnable {

    protected String placa;
    protected TipoCombustivel combustivel;
    protected double velocidade;
    protected EstadoVeiculo estado;
    protected LocalTime horaInicio;

    protected double nivelCombustivelLitros;
    protected final double consumoPorTravessiaLitros = 1.0;

    public Veiculo(String placa, TipoCombustivel combustivel) {
        this.placa = placa;
        this.combustivel = combustivel;
        this.estado = EstadoVeiculo.PARADO;
        this.horaInicio = LocalTime.now();
        this.nivelCombustivelLitros = 5.0;
    }

    public abstract void mover(Cruzamento cruzamento);

    public void parar() {
        this.estado = EstadoVeiculo.PARADO;
        System.out.println(getClass().getSimpleName() + " [" + placa + "] parou");
    }

    public String getPlaca() {
        return placa;
    }

    public double getVelocidade() {
        return velocidade;
    }

    protected void consumirCombustivel() throws VeiculoSemCombustivelException {
        if (nivelCombustivelLitros <= 0) {
            throw new VeiculoSemCombustivelException(placa);
        }
        nivelCombustivelLitros -= consumoPorTravessiaLitros;
    }

    public double getNivelCombustivelLitros() {
        return nivelCombustivelLitros;
    }

    public void abastecer(double litros) {
        this.nivelCombustivelLitros += litros;
        System.out.println(getClass().getSimpleName() + " [" + placa + "] abastecido. Nivel: " + nivelCombustivelLitros);
    }

    @Override
    public void run() {

    }
}
