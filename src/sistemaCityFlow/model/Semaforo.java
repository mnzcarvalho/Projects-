package sistemaCityFlow.model;

public class Semaforo {
    private boolean verde;
    private final String local;

    public Semaforo(String local) {
        this.local = local;
        this.verde = true;
    }

    public synchronized void alternar() {
        verde = !verde;
        System.out.println("Semáforo em " + local + ": " + (verde ? "VERDE" : "VERMELHO"));
    }

    public boolean isVerde(){
        return verde;
    }
}
