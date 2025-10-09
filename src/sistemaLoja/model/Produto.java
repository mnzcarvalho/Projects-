package sistemaLoja.model;

import sistemaLoja.enums.CategoriaProduto;

public class Produto {
    private static int contador = 1;

    private final int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private CategoriaProduto categoria;

    {
        id=contador++;
    }

    public Produto(String nome, double preco, int quantidadeEstoque, CategoriaProduto categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public void reduzirEstoque(int quantidade) throws Exception{
        if (quantidade > quantidadeEstoque){
            throw new Exception("Estoque insuficiênte para o produto: " + nome);
        }
        quantidadeEstoque -= quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return nome + " - " + categoria + " - R$ " + preco + " (Estoque: " + quantidadeEstoque + ")";
    }
}
