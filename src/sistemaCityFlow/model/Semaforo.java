package sistemaCityFlow.model;

public class Semaforo implements Runnable {
    private EstadoSemaforo estado;
    private final String local;
    private final long tempoVerdeMillis;
    private final long tempoAmareloMillis;
    private final long tempoVermelhoMillis;
    private volatile boolean ativo = true;

    public Semaforo(String local, long tempoVerdeMillis, long tempoAmareloMillis, long tempoVermelhoMillis) {
        this.local = local;
        this.tempoVerdeMillis = tempoVerdeMillis;
        this.tempoAmareloMillis = tempoAmareloMillis;
        this.tempoVermelhoMillis = tempoVermelhoMillis;
        this.estado = EstadoSemaforo.VERMELHO;
    }

    public synchronized EstadoSemaforo getEstado() {
        return estado;
    }

    private synchronized void setEstado(EstadoSemaforo novo) {
        this.estado = novo;
        System.out.println("[Semáforo " + local + "] agora " + novo);
        if (novo == EstadoSemaforo.VERDE){
            notifyAll();
        }
    }

    public synchronized void esperarVerde() throws InterruptedException{
        while (estado != EstadoSemaforo.VERDE){
            wait();
        }
    }

    @Override
    public void run() {
        try {
            setEstado(EstadoSemaforo.VERDE);
            Thread.sleep(tempoVerdeMillis);

            setEstado(EstadoSemaforo.AMARELO);
            Thread.sleep(tempoAmareloMillis);

            setEstado(EstadoSemaforo.VERMELHO);
            Thread.sleep(tempoVermelhoMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Semáforo " + local + " interrompido.");
        }
    }

    public void pararSemaforo(){
        ativo = false;
    }

    public String getLocal() {
        return local;
    }
}
