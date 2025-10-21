package sistemaCityFlow.app;

import sistemaCityFlow.model.*;
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

        Thread.sleep(200000);

        System.out.println("===Encerrando simulação===");
        service.pararSimulacao();
    }
}
