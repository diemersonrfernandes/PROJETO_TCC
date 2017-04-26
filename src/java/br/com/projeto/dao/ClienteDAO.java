package br.com.projeto.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


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
    @NamedQuery(name = "ClienteDAO.findByUsuario", query = "SELECT c FROM ClienteDAO c WHERE c.idusuario.idusuario = :idusuario")})
public class ClienteDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String cpf;
    @Size(max = 50)
    private String endereco;
    @Size(max = 50)
    private String telefone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    private String email;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private UsuarioDAO idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente")
    private Collection<PedidoDAO> pedidoDAOCollection;

    public ClienteDAO() {
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

    @XmlTransient
    public Collection<PedidoDAO> getPedidoDAOCollection() {
        return pedidoDAOCollection;
    }

    public void setPedidoDAOCollection(Collection<PedidoDAO> pedidoDAOCollection) {
        this.pedidoDAOCollection = pedidoDAOCollection;
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
