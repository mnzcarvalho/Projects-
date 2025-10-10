package sistemaLoja.app;

import sistemaLoja.enums.CategoriaProduto;
import sistemaLoja.exception.EstoqueInsuficienteException;
import sistemaLoja.model.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Produtos e estoque
            Produto perfume = new Produto("Essência de Luxo", 250.0, 10, CategoriaProduto.PERFUME);
            Produto joia = new Produto("Colar de Prata", 500.0, 5, CategoriaProduto.JOIA);

            Estoque estoque = new Estoque();
            estoque.adicionarProduto(perfume, 10);
            estoque.adicionarProduto(joia, 5);

            // Cliente e pedido
            Cliente cliente = new Cliente("Maria Silva");
            Pedido pedido = new Pedido(cliente);

            estoque.reduzirEstoque(perfume, 2);
            pedido.adicionarItem(perfume, 2);

            estoque.reduzirEstoque(joia, 1);
            pedido.adicionarItem(joia, 1);

            // Escolher tipo de entrega
            Entrega entrega = new EntregaExpressa("Rua das Flores, 123 - São Paulo");
            pedido.setEntrega(entrega);

            // Mostrar resultado
            System.out.println(pedido.gerarResumo());
            estoque.listarEstoque();

        } catch (EstoqueInsuficienteException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}