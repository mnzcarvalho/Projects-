package miniifood.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User extends BaseEntity{
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String senha;
    private LocalDate dataNascimento;
    private boolean ativo;
    private LocalDateTime dataCadastro;

    public User(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public User(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String email, String telefone, String cpf, String senha, LocalDate dataNascimento, boolean ativo, LocalDateTime dataCadastro) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
