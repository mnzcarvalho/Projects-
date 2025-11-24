package pooP2.model;

import pooP2.exception.ClienteNaoEncontradoException;
import pooP2.exception.ProdutoNaoEncontradoException;
import pooP2.model.Pessoa;

import java.io.*;
import java.util.*;

// 9. CLASSE LOJA (RELACIONAMENTOS 1:N)
public class Loja implements Serializable {
    // ARRAYLIST POLIMÓRFICO
    private List<Pessoa> pessoas;
    private List<Produto> produtos;
    private List<Pedido> pedidos;
    
    public Loja() {
        this.pessoas = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }
    
    // RELACIONAMENTO 1:N (Loja tem várias Pessoas, vários Produtos, vários Pedidos)
    public void adicionarCliente(Cliente cliente) {
        pessoas.add(cliente);
    }
    
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
    
    // BUSCAS COM LOOPS
    public Cliente buscarCliente(String cpf) {
        for(Pessoa pessoa : pessoas) {
            if(pessoa instanceof Cliente && pessoa.getCpf().equals(cpf)) {
                return (Cliente) pessoa;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente não encontrado: " + cpf);
    }
    
    public Produto buscarProduto(int id) {
        for(Produto produto : produtos) {
            if(produto.getId() == id) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto não encontrado: " + id);
    }
    
    // LISTAGENS
    public void listarClientes() {
        System.out.println("\n--- CLIENTES ---");
        for(Pessoa pessoa : pessoas) {
            if(pessoa instanceof Cliente) {
                System.out.println(pessoa);
            }
        }
    }
    
    public void listarProdutos() {
        System.out.println("\n--- PRODUTOS ---");
        for(Produto produto : produtos) {
            System.out.println(produto);
        }
    }
    
    public void listarPedidos() {
        System.out.println("\n--- PEDIDOS ---");
        for(Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
    
    public void gerarRelatorioVendas() {
        System.out.println("\n--- RELATÓRIO DE VENDAS ---");
        double totalVendas = 0;
        
        for(Pedido pedido : pedidos) {
            double totalPedido = pedido.calcularTotal();
            totalVendas += totalPedido;
            System.out.println(pedido);
        }
        
        System.out.println("TOTAL DE VENDAS: R$" + totalVendas);
        System.out.println("TOTAL DE PEDIDOS: " + pedidos.size());
    }
    
    // SERIALIZAÇÃO
    public void salvarDados() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("loja.dat"))) {
            oos.writeObject(this);
        }
    }
    
    public void carregarDados() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("loja.dat"))) {
            Loja lojaCarregada = (Loja) ois.readObject();
            this.pessoas = lojaCarregada.pessoas;
            this.produtos = lojaCarregada.produtos;
            this.pedidos = lojaCarregada.pedidos;
        }
    }
}
