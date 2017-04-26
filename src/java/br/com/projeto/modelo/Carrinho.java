package br.com.projeto.modelo;

import br.com.projeto.dao.ProdutoDAO;


public class Carrinho {
    ProdutoDAO produto;
    int quantidade;

    public ProdutoDAO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDAO produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
