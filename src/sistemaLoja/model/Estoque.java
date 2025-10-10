package sistemaLoja.model;

import sistemaLoja.exception.EstoqueInsuficienteException;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtos = new HashMap<>();

    public void adicionarProduto(Produto produto, int quantidade){
        produtos.put(produto, quantidade);
    }

    public void reduzirEstoque(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        Integer qtdAtual = produtos.get(produto);

        if (qtdAtual == null){
            throw new EstoqueInsuficienteException("Produto não encontrado no estoque: " + produto);
        }

        if (quantidade > qtdAtual){
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiênte para o produto: " + produto.getNome() + ". Disponível: "
                    + qtdAtual + ", solicitado: " + quantidade
            );
        }
        produtos.put(produto, qtdAtual - quantidade);
    }

    public void listarEstoque(){
        System.out.println("=======Listar Produtos=======");
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()){
            System.out.println(entry.getKey().getNome() + " - Qtd: " + entry.getValue());
        }
    }
}
