/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "item_pedido", catalog = "projeto_tcc", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPedidoDAO.findAll", query = "SELECT i FROM ItemPedidoDAO i"),
    @NamedQuery(name = "ItemPedidoDAO.findByIditem", query = "SELECT i FROM ItemPedidoDAO i WHERE i.iditem = :iditem"),
    @NamedQuery(name = "ItemPedidoDAO.findByIdproduto", query = "SELECT i FROM ItemPedidoDAO i WHERE i.idproduto = :idproduto"),
    @NamedQuery(name = "ItemPedidoDAO.findByQuantidade", query = "SELECT i FROM ItemPedidoDAO i WHERE i.quantidade = :quantidade")})
public class ItemPedidoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer iditem;
    @JoinColumn(name = "idproduto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProdutoDAO idproduto;
    @Basic(optional = false)
    @NotNull
    private int quantidade;
    @JoinColumn(name = "idpedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PedidoDAO idpedido;
 
    public ItemPedidoDAO() {
    }

    public ItemPedidoDAO(Integer iditem) {
        this.iditem = iditem;
    }

    public ItemPedidoDAO(Integer iditem, ProdutoDAO idproduto, int quantidade) {
        this.iditem = iditem;
        this.idproduto = idproduto;
        this.quantidade = quantidade;
    }

    public Integer getIditem() {
        return iditem;
    }

    public void setIditem(Integer iditem) {
        this.iditem = iditem;
    }

    public ProdutoDAO getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(ProdutoDAO idproduto) {
        this.idproduto = idproduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoDAO getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(PedidoDAO idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditem != null ? iditem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedidoDAO)) {
            return false;
        }
        ItemPedidoDAO other = (ItemPedidoDAO) object;
        if ((this.iditem == null && other.iditem != null) || (this.iditem != null && !this.iditem.equals(other.iditem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.projeto.dao.ItemPedidoDAO[ iditem=" + iditem + " ]";
    }
    
}
