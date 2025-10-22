package sistemaCityFlow.exception;

public class VeiculoSemCombustivelException extends Exception {
    public VeiculoSemCombustivelException(String placa) {
        super("Veículo [" + placa + "] sem combustível.");
    }
}
