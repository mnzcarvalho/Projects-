package pooP2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
    private static int contador = 1;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private String status;
    
    public Pedido() {
        this.id = contador++;
        this.itens = new ArrayList<>();
        this.status = "Pendente";
    }
    
    public Pedido(Cliente cliente) {
        this();
        this.cliente = cliente;
    }
    
    // RELACIONAMENTO N:N (Pedido tem vários Produtos através de ItemPedido)
    public void adicionarItem(Produto produto, int quantidade) {
        produto.reduzirEstoque(quantidade);
        itens.add(new ItemPedido(produto, quantidade));
    }
    
    public double calcularTotal() {
        double total = 0;
        for(ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    // GETTERS E SETTERS
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Pedido #" + id + " - " + cliente.getNome() + " - Total: R$" + calcularTotal();
    }
}
