/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.modelo;

import br.com.projeto.dao.ProdutoDAO;

/**
 *
 * @author Antonio Augusto
 */
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
