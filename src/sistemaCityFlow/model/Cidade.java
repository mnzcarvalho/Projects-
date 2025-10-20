package sistemaCityFlow.model;

import java.util.ArrayList;
import java.util.List;

public class Cidade {

    private String nome;
    private List<Rua> ruas;
    private List<Veiculo> veiculos;

    public Cidade(String nome) {
        this.nome = nome;
        this.ruas = new ArrayList<>();
        this.veiculos = new ArrayList<>();
    }

    public void adicionarRua(Rua rua){
        ruas.add(rua);
    }

    public void adicionarVeiculo(Veiculo v){
        veiculos.add(v);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
