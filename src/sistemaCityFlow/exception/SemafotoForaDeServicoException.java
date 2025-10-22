package sistemaCityFlow.exception;

public class SemafotoForaDeServicoException extends Exception {
    public SemafotoForaDeServicoException(String local) {
        super("Semáforo " + local + " fora de serviço");
    }
}
