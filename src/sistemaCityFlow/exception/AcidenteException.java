package sistemaCityFlow.exception;

public class AcidenteException extends Exception {
    public AcidenteException(String detalhe) {
        super("Acidente " + detalhe);
    }
}
