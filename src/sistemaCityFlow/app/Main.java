package sistemaCityFlow.app;

import sistemaCityFlow.model.*;
import sistemaCityFlow.service.TrafegoService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Simulador de Tráfego Urbano - City Flow ===");
        Cidade cidade = new Cidade("São Paulo");
        TrafegoService service = new TrafegoService(cidade);

        cidade.adicionarRua(new Rua("Av. Paulista", 5));
        cidade.adicionarRua(new Rua("Rua Augusta", 3));

        service.registrarVeiculo(new Carro("ABC-1234", TipoCombustivel.GASOLINA));
        service.registrarVeiculo(new Moto("XYZ-9876", TipoCombustivel.ETANOL));
        service.registrarVeiculo(new Onibus("BUS-5544", TipoCombustivel.DIESEL));

        service.iniciarSimulacao();
    }
}
