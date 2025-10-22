package sistemaCityFlow.service;

import sistemaCityFlow.exception.VeiculoSemCombustivelException;
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
    private final LogService logService = new LogService();

    public TrafegoService() {
    }

    public void registrarVeiculo(Veiculo v) {
        veiculos.add(v);
        logService.info("Registrar veículo: " + v.getClass().getSimpleName() + " [" + v.getPlaca() + "]");
    }

    public Cruzamento criarCruzamentoCentral() {
        Semaforo s = new Semaforo("Central", 3000, 1000, 3000);
        semaforos.add(s);
        cruzamentoCentral = new Cruzamento("C-1", s);
        logService.info("Cruzamento C-1 criado com semáforo Central.");
        return cruzamentoCentral;
    }

    public void iniciarSimulacao() {
        if (cruzamentoCentral == null) criarCruzamentoCentral();

        for (Semaforo s : semaforos) {
            Thread ts = new Thread(s, "Semaforo-" + s.getLocal());
            ts.start();
            threads.add(ts);
            logService.info("Semáforo iniciado: " + s.getLocal());
        }

        for (Veiculo v : veiculos) {
            Thread tv = new Thread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        try {
                            v.mover(cruzamentoCentral);
                            logService.info(v.getClass().getSimpleName() + "[" + v.getPlaca() + "] atravessou C-1. Combustível restante: " + v.getNivelCombustivelLitros());
                        } catch (RuntimeException re) {
                            Throwable causa = re.getCause();
                            if (causa instanceof VeiculoSemCombustivelException) {
                                logService.warn("Veículo sem combustível: " + v.getPlaca());
                                v.abastecer(5);
                                logService.info("Veículo " + v.getPlaca() + " abastecido automaticamente.");
                            } else if (causa == null) {
                                logService.error("Erro ao atravessar: " + causa.getMessage());
                                break;
                            } else {
                                logService.error("Erro genérico: " + re.getMessage());
                                break;
                            }
                        }

                        try {
                            Thread.sleep(1000 + (long) (Math.random() * 2000));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }

                    logService.info(v.getClass().getSimpleName() + " [" + v.getPlaca() + "] encerrou suas travessias.");
                }catch (Exception e){
                logService.error("Thread de veículo encerrou por erro: " + e.getMessage());
                }
            },"Veiculo-" + v.getPlaca());
                tv.start();
                threads.add(tv);
            }

        }

        public void pararSimulacao () {
            for (Semaforo s : semaforos) {s.pararSemaforo();}
            for (Thread t : threads) {t.interrupt();}
            logService.info("Simulação parada.");
        }

        public LogService getLogService(){
        return logService;
        }
    }
