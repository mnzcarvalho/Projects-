package desafioCadastro.entities;

import desafioCadastro.utils.Constantes;

public class Endereco {

    private String numero;
    private String cidade;
    private String rua;
    private String bairro;

    public Endereco() {
        this.numero = Constantes.NAO_INFORMADO;
        this.cidade = Constantes.NAO_INFORMADO;
        this.rua = Constantes.NAO_INFORMADO;
    }

    public Endereco(String numero, String cidade, String rua) {
        this.numero = numero;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = "";
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return bairro;
    }

    @Override
    public String toString() {
        if (bairro != null && !bairro.isEmpty()) {
            return rua + ", " + numero + ", " + bairro;
        }
        return rua + ", " + numero + ", " + cidade;
    }
}
