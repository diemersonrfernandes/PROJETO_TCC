/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "produto", catalog = "projeto_tcc", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdutoDAO.findAll", query = "SELECT p FROM ProdutoDAO p"),
    @NamedQuery(name = "ProdutoDAO.findById", query = "SELECT p FROM ProdutoDAO p WHERE p.id = :id"),
    @NamedQuery(name = "ProdutoDAO.findByNome", query = "SELECT p FROM ProdutoDAO p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProdutoDAO.findByValor", query = "SELECT p FROM ProdutoDAO p WHERE p.valor = :valor"),
    @NamedQuery(name = "ProdutoDAO.findByNomeimagem", query = "SELECT p FROM ProdutoDAO p WHERE p.nomeimagem = :nomeimagem")})
public class ProdutoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String nomeimagem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduto")
    private Collection<ItemPedidoDAO> itemPedidoDAOCollection;

    public ProdutoDAO() {
    }

    public ProdutoDAO(Integer id) {
        this.id = id;
    }

    public ProdutoDAO(Integer id, String nome, BigDecimal valor, String nomeimagem) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.nomeimagem = nomeimagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomeimagem() {
        return nomeimagem;
    }

    public void setNomeimagem(String nomeimagem) {
        this.nomeimagem = nomeimagem;
    }

    @XmlTransient
    public Collection<ItemPedidoDAO> getItemPedidoDAOCollection() {
        return itemPedidoDAOCollection;
    }

    public void setItemPedidoDAOCollection(Collection<ItemPedidoDAO> itemPedidoDAOCollection) {
        this.itemPedidoDAOCollection = itemPedidoDAOCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoDAO)) {
            return false;
        }
        ProdutoDAO other = (ProdutoDAO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.projeto.dao.ProdutoDAO[ id=" + id + " ]";
    }
    
}
