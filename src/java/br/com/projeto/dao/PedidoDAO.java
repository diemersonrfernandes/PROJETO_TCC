/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "pedido", catalog = "projeto_tcc", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoDAO.findAll", query = "SELECT p FROM PedidoDAO p"),
    @NamedQuery(name = "PedidoDAO.findById", query = "SELECT p FROM PedidoDAO p WHERE p.id = :id"),
    @NamedQuery(name = "PedidoDAO.findByValor", query = "SELECT p FROM PedidoDAO p WHERE p.valor = :valor"),
    @NamedQuery(name = "PedidoDAO.findByQuantidade", query = "SELECT p FROM PedidoDAO p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "PedidoDAO.findByDtpedido", query = "SELECT p FROM PedidoDAO p WHERE p.dtpedido = :dtpedido"),
    @NamedQuery(name = "PedidoDAO.findByStatus", query = "SELECT p FROM PedidoDAO p WHERE p.status = :status"),
    @NamedQuery(name = "PedidoDAO.findByCliente", query = "SELECT p FROM PedidoDAO p WHERE p.idcliente = :cliente")})
public class PedidoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    private int quantidade;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpedido;
    private Boolean status;
    @JoinColumn(name = "idcliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClienteDAO idcliente;

    public PedidoDAO() {
    }

    public PedidoDAO(Integer id) {
        this.id = id;
    }

    public PedidoDAO(Integer id, BigDecimal valor, int quantidade, Date dtpedido) {
        this.id = id;
        this.valor = valor;
        this.quantidade = quantidade;
        this.dtpedido = dtpedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDtpedido() {
        return dtpedido;
    }

    public void setDtpedido(Date dtpedido) {
        this.dtpedido = dtpedido;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ClienteDAO getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(ClienteDAO idcliente) {
        this.idcliente = idcliente;
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
        if (!(object instanceof PedidoDAO)) {
            return false;
        }
        PedidoDAO other = (PedidoDAO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.projeto.dao.PedidoDAO[ id=" + id + " ]";
    }
    
}
