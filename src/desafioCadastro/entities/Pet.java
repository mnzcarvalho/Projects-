package desafioCadastro.entities;

import desafioCadastro.utils.Constantes;
import desafioCadastro.utils.ValidacaoUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Pet {
    private String nomeCompleto;
    private TipoPet tipo;
    private SexoPet sexo;
    private Endereco endereco;
    private double idade;
    private double peso;
    private String raca;
    private LocalDateTime dataCadastro;

    public Pet(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Pet(String nomeCompleto, TipoPet tipo, SexoPet sexo, Endereco endereco, double idade, double peso, String raca, LocalDateTime dataCadastro) {
        this.nomeCompleto = nomeCompleto;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
        this.dataCadastro = dataCadastro;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório");
        }

        if (!ValidacaoUtils.isValidNome(nomeCompleto)) {
            throw new IllegalArgumentException("Nome completo não pode conter números ou caracteres especiais.");
        }
        this.nomeCompleto = nomeCompleto;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public void setTipo(TipoPet tipo) {
        this.tipo = tipo;
    }

    public SexoPet getSexo() {
        return sexo;
    }

    public void setSexo(SexoPet sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade){
        if (!ValidacaoUtils.isValidIdade(idade)){
            throw new IllegalArgumentException("Idade deve ter entre 0 a 20 anos.");
        }
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (!ValidacaoUtils.isValidPeso(peso)){
            throw new IllegalArgumentException("Peso deve estar entre 0.5kg e 60kg");
        }
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        if (raca != null && !raca.trim().isEmpty() && !ValidacaoUtils.isValidRaca(raca)){
            throw new IllegalArgumentException("Raça não pode conter números ou caracteres especiais.");
        }
        this.raca = (raca == null || raca.trim().isEmpty() ? Constantes.NAO_INFORMADO : raca);
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String setDataCadastro(LocalDateTime dataCadastro) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataFormatada = dataCadastro.format(formatter);
        String nomeArquivo = nomeCompleto.toUpperCase().replace(" ", "");
        return dataFormatada + "-" + nomeArquivo + ".txt";
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %.1f anos - %.1fkg - %s", nomeCompleto, tipo.getDescricao(),
                sexo.getDescricao(), endereco.toString(), idade, peso, raca);
    }
}
