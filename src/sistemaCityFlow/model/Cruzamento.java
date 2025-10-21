package sistemaCityFlow.model;

public class Cruzamento {

    private final String id;
    private final Semaforo semaforo;

    public Cruzamento(String id, Semaforo semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    public void tentarAtravessar(Veiculo v){
        try {
            System.out.println(v.getClass().getSimpleName() + " ["+ v.getPlaca() + "] chegando ao cruzamento " + id + " (semáforo: " + semaforo.getEstado() + ")");

            semaforo.esperarVerde();

            System.out.println(v.getClass().getSimpleName() + " ["+ v.getPlaca() + "] atravessando o cruzamento " + id + "...");

            long tempoTravessia = Math.max(200, (long) (1000 * (60.0 / v.getVelocidade())));
            Thread.sleep(tempoTravessia);

            System.out.println(v.getClass().getSimpleName() + " [" + v.getPlaca() + "] finalizou travessia em " + tempoTravessia + "ms.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Veículo " + v.getPlaca() + " foi interrompido enquanto esperava no cruzamento " + id);

        }
    }

    public String getId() {
        return id;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }
}
