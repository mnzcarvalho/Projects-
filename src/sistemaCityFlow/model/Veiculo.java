package sistemaCityFlow.model;

import java.time.LocalTime;

public abstract class Veiculo implements Runnable {

    protected String placa;
    protected TipoCombustivel combustivel;
    protected double velocidade;
    protected EstadoVeiculo estado;
    protected LocalTime horaInicio;

    public Veiculo(String placa, TipoCombustivel combustivel) {
        this.placa = placa;
        this.combustivel = combustivel;
        this.estado = EstadoVeiculo.PARADO;
        this.horaInicio = LocalTime.now();
    }

    public abstract void mover();

    public void parar(){
        this.estado = EstadoVeiculo.PARADO;
        System.out.println(getClass().getSimpleName() + " [" + placa + "] parou");
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public void run(){
        mover();
    }
}
