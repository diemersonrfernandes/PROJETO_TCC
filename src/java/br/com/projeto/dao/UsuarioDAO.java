/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio Augusto
 */
@Entity
@Table(name = "usuario", catalog = "projeto_tcc", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioDAO.findAll", query = "SELECT u FROM UsuarioDAO u"),
    @NamedQuery(name = "UsuarioDAO.findByIdusuario", query = "SELECT u FROM UsuarioDAO u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "UsuarioDAO.findByNmusuario", query = "SELECT u FROM UsuarioDAO u WHERE u.nmusuario = :nmusuario"),
    @NamedQuery(name = "UsuarioDAO.findByDeSenha", query = "SELECT u FROM UsuarioDAO u WHERE u.deSenha = :deSenha")})
public class UsuarioDAO implements Serializable {

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
//    private Collection<ClienteDAO> clienteDAOCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmusuario")
    private String nmusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "deSenha")
    private String deSenha;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private PerfilDAO idperfil;

//    private PreparedStatement ps; //prepara os paramêtros a serem inseridos no BD
//    private ResultSet rs; //guarda os dados vindos do BD
//    private Connection con; //representa a conexão com o BD
//    private String sql;
    
    public UsuarioDAO() {
        idperfil = new PerfilDAO();
        //con = Conexao.abreConexao();
    }

    public UsuarioDAO(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public UsuarioDAO(Integer idusuario, String nmusuario, String deSenha) {
        this.idusuario = idusuario;
        this.nmusuario = nmusuario;
        this.deSenha = deSenha;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNmusuario() {
        return nmusuario;
    }

    public void setNmusuario(String nmusuario) {
        this.nmusuario = nmusuario;
    }

    public String getDeSenha() {
        return deSenha;
    }

    public void setDeSenha(String deSenha) {
        this.deSenha = deSenha;
    }

    public PerfilDAO getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(PerfilDAO idperfil) {
        this.idperfil = idperfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioDAO)) {
            return false;
        }
        UsuarioDAO other = (UsuarioDAO) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.projeto.dao.UsuarioDAO[ idusuario=" + idusuario + " ]";
    }
    
       //método para fazer o insert pessoa
//    public boolean insert(Usuario obj) {
//        sql = "insert into usuario (idusuario,nmusuario,idperfil,deSenha)"
//                + "values(?,?,?,?)";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, obj.getIdUsuario());
//            ps.setString(2, obj.getNmUsuario());
//            ps.setInt(3, obj.getPerfil().getIdPerfil());
//            ps.setString(4, obj.getDeSenha());
//
//            return ps.execute();
//        } catch (SQLException erro) {
//            erro.printStackTrace();
//            return false;
//        }
//    }

//    @XmlTransient
//    public Collection<ClienteDAO> getClienteDAOCollection() {
//        return clienteDAOCollection;
//    }
//
//    public void setClienteDAOCollection(Collection<ClienteDAO> clienteDAOCollection) {
//        this.clienteDAOCollection = clienteDAOCollection;
//    }
    
}
