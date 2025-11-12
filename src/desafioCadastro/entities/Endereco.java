package desafioCadastro.entities;

import desafioCadastro.utils.Constantes;

public class Endereco {

    private String numero;
    private String cidade;
    private String rua;

    public Endereco() {
        this.numero = Constantes.NAO_INFORMADO;
        this.cidade = Constantes.NAO_INFORMADO;
        this.rua = Constantes.NAO_INFORMADO;
    }

    public Endereco(String numero, String cidade, String rua) {
        this.numero = numero.isEmpty() ? Constantes.NAO_INFORMADO : numero;
        this.cidade = cidade.isEmpty() ? Constantes.NAO_INFORMADO : cidade;
        this.rua = rua.isEmpty() ? Constantes.NAO_INFORMADO : rua;
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

    @Override
    public String toString() {
        return rua + "," + numero + "," + cidade;
    }
}
