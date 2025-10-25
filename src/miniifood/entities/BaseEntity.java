package miniifood.entities;

import java.time.LocalDateTime;

public abstract class BaseEntity {
private int id;
private LocalDateTime dataCriacao;
private LocalDateTime dataAtualizacao;
private boolean ativo;

    public BaseEntity(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = ativo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean ativar(){
        return this.ativo = true;
    }

    public boolean desativar(){
        return this.ativo = false;
    }

    public void atualizarDataModificacao(){
        this.dataAtualizacao = LocalDateTime.now();
    }


}
