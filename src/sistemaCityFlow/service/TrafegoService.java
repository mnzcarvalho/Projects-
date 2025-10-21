package sistemaCityFlow.service;

import sistemaCityFlow.model.Cidade;
import sistemaCityFlow.model.Cruzamento;
import sistemaCityFlow.model.Semaforo;
import sistemaCityFlow.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class TrafegoService {
    private final List<Veiculo> veiculos = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();
    private final List<Semaforo> semaforos = new ArrayList<>();
    private Cruzamento cruzamentoCentral;

    public TrafegoService() {
    }

    public void registrarVeiculo(Veiculo v) {
        veiculos.add(v);
    }

    public Cruzamento criarCruzamentoCentral() {
        Semaforo s = new Semaforo("Central", 3000, 1000, 3000);
        semaforos.add(s);
        cruzamentoCentral = new Cruzamento("C-1", s);
        return cruzamentoCentral;
    }

    public void iniciarSimulacao() {
        if (cruzamentoCentral == null) criarCruzamentoCentral();

        for (Semaforo s : semaforos) {
            Thread ts = new Thread(s, "Semaforo-" + s.getLocal());
            ts.start();
            threads.add(ts);
        }

        for (Veiculo v : veiculos) {
            Thread tv = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    v.mover(cruzamentoCentral);
                    try {
                        // intervalo entre tentativas (simula viajar por ruas)
                        Thread.sleep(1000 + (long) (Math.random() * 2000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                System.out.println(v.getClass().getSimpleName() + " [" + v.getPlaca() + "] encerrou suas travessias.");
            }, "Veiculo-" + v.getPlaca());
            tv.start();
            threads.add(tv);
        }

    }

    public void pararSimulacao() {
        for (Semaforo s : semaforos) {
            s.pararSemaforo();
        }
        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
