package sistemaLoja.model;

import sistemaLoja.enums.StatusPedido;
import sistemaLoja.util.MoedaUtil;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1000;
    private final int id;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status;
    private Entrega entrega;

    {
        id = contador++;
        status = StatusPedido.ABERTO;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
    }

    public void adicionarItem(Produto produto) {
        adicionarItem(produto, 1);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido i : itens) {
            total += i.calcularSubtotal();
        }
        return total;
    }

    //**********
    public String gerarResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id).append(" - Cliente: ").append(cliente.getNome()).append("\n");
        for (ItemPedido i : itens) {
            sb.append(i.toString()).append("\n");
        }
        sb.append("Total: ").append(MoedaUtil.formatar(calcularTotal()));

        if (entrega != null) {
            sb.append(entrega.toString());
        }

        return sb.toString();
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
