package sistemaLoja.app;

import sistemaLoja.enums.CategoriaProduto;
import sistemaLoja.model.Cliente;
import sistemaLoja.model.Pedido;
import sistemaLoja.model.Produto;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Produto perfume = new Produto("Essência de Luxo", 250.0, 10, CategoriaProduto.PERFUME);
        Produto joia = new Produto("Colar de Prata", 500.0, 5, CategoriaProduto.JOIA);

        Cliente cliente = new Cliente("Maria Silva");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(perfume, 2);
        pedido.adicionarItem(joia);

        System.out.println(pedido.gerarResumo());
    }
}