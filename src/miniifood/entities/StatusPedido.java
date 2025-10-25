package miniifood.entities;

import java.time.LocalDateTime;

public class StatusPedido extends BaseEntity{
    private String nome;

    public StatusPedido(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public StatusPedido(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatusPedido{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
