/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Antonio Augusto
 */
@Entity
@Table(name = "cliente", catalog = "projeto_tcc", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteDAO.findAll", query = "SELECT c FROM ClienteDAO c"),
    @NamedQuery(name = "ClienteDAO.findById", query = "SELECT c FROM ClienteDAO c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteDAO.findByNome", query = "SELECT c FROM ClienteDAO c WHERE c.nome = :nome"),
    @NamedQuery(name = "ClienteDAO.findByCpf", query = "SELECT c FROM ClienteDAO c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "ClienteDAO.findByEndereco", query = "SELECT c FROM ClienteDAO c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "ClienteDAO.findByTelefone", query = "SELECT c FROM ClienteDAO c WHERE c.telefone = :telefone"),
    @NamedQuery(name = "ClienteDAO.findByEmail", query = "SELECT c FROM ClienteDAO c WHERE c.email = :email"),
    @NamedQuery(name = "ClienteDAO.findByUsuario", query = "SELECT c FROM ClienteDAO c WHERE c.idusuario = :idusuario")})
public class ClienteDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 50)
    @Column(name = "CPF")
    private String cpf;
    @Size(max = 50)
    @Column(name = "ENDERECO")
    private String endereco;
    @Size(max = 50)
    @Column(name = "TELEFONE")
    private String telefone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private UsuarioDAO idusuario;

    public ClienteDAO() {
        idusuario = new UsuarioDAO();
    }

    public ClienteDAO(Integer id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuarioDAO getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(UsuarioDAO idusuario) {
        this.idusuario = idusuario;
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
        if (!(object instanceof ClienteDAO)) {
            return false;
        }
        ClienteDAO other = (ClienteDAO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.projeto.dao.ClienteDAO[ id=" + id + " ]";
    }
    
}
