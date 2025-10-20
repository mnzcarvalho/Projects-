package sistemaCityFlow.service;

import sistemaCityFlow.model.Cidade;
import sistemaCityFlow.model.Veiculo;

import java.util.List;

public class TrafegoService {
    private Cidade cidade;

    public TrafegoService(Cidade cidade) {
        this.cidade = cidade;
    }

    public void registrarVeiculo(Veiculo v){
        cidade.adicionarVeiculo(v);
    }

    public void iniciarSimulacao(){
        System.out.println("\nIniciando a simulação na cidade de " + cidade.getVeiculos().size() + " veículos...\n");

        List<Veiculo> veiculos = cidade.getVeiculos();
        for (Veiculo v : veiculos) {
            Thread t = new Thread(v);
            t.start();
        }

    }
}
