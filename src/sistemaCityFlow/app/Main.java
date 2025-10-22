package sistemaCityFlow.app;

import sistemaCityFlow.model.*;
import sistemaCityFlow.service.LogService;
import sistemaCityFlow.service.TrafegoService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Simulador de Tráfego Urbano - City Flow ===");

        TrafegoService service = new TrafegoService();

        service.criarCruzamentoCentral();

        service.registrarVeiculo(new Carro("ABC-1234", TipoCombustivel.GASOLINA));
        service.registrarVeiculo(new Moto("XYZ-9876", TipoCombustivel.ETANOL));
        service.registrarVeiculo(new Onibus("BUS-5544", TipoCombustivel.DIESEL));

        service.iniciarSimulacao();

        Thread.sleep(25000);

        System.out.println("===Encerrando simulação===");
        service.pararSimulacao();

        Thread.sleep(1000);

        LogService log = service.getLogService();
        System.out.println("----- Caminho do log: " + log.getLogPath() + " -----");
        System.out.println(log.lerTodos());
    }
}
