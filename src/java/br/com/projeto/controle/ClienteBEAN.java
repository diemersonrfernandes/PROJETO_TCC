package br.com.projeto.controle;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.PerfilDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.facade.ClienteFacade;
import br.com.projeto.facade.UsuarioFacade;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.util.facesUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


//ManagedBean faz a ligação com interface com usuário (tela)

@ManagedBean(name = "clienteBEAN")
@SessionScoped
public class ClienteBEAN implements Serializable{

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ClienteFacade clienteFacade;
    
    //private Cliente cliente; //get e set
    private ClienteDAO clienteDAO = new ClienteDAO();
   // private UsuarioDAO usuarioDAO;
    private Cliente clienteSelecionado;//GET SET
    private List<ClienteDAO> lista;//GET SET
    private List<ClienteDAO> listas;


    
    
public ClienteBEAN(){
    //cliente = new Cliente();
    //clienteDAO = new ClienteDAO();
    clienteSelecionado = new Cliente();
}    
//GET E SET CLIENTESELECIONADO

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

//GET e SET cliente

    public ClienteDAO getCliente() {
        return clienteDAO;
    }

    public void setCliente(ClienteDAO cliente) {
        this.clienteDAO = cliente;
    }    

//GET E SET DE LISTA

    public ClienteDAO getClienteByUsuario(int idUsuario) {
        if (clienteFacade == null)
            clienteFacade = new ClienteFacade();
        return clienteFacade.FindByUsuario(idUsuario);
    }
    
    public List<ClienteDAO> getLista() {
        if (clienteFacade == null)
            clienteFacade = new ClienteFacade();
        lista = clienteFacade.findAll();
        return lista;
    }

    public void setLista(List<ClienteDAO> lista) {
        this.lista = lista;
    }
    
//BOTÃO CONFIRMAR    
    public String confirmar(int idPerfil){  
        facesUtil.adiconarMsgInfo("Cadastro realizado com sucesso!");
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.setNmusuario(clienteDAO.getIdusuario().getNmusuario());
        usuarioDAO.setDeSenha(clienteDAO.getIdusuario().getDeSenha());
        
        PerfilDAO perfil = new PerfilDAO();
        perfil.setIdperfil(idPerfil);
        usuarioDAO.setIdperfil(perfil);
        
        usuarioFacade.create(usuarioDAO);
        
        clienteDAO.setIdusuario(usuarioDAO);
        
        clienteFacade.create(clienteDAO);
        //clienteDAO.insert(cliente);
        //cliente = new Cliente();  
        //lista = clienteDAO.getListaCliente();
        return "pedido.xhtml";
    }    
//BOTÃO ALTERAR    
    public void alterar(){
//        clienteDAO.update(cliente);
//        lista = clienteDAO.getListaCliente();
//        cliente = new Cliente();
    }
    
  public String sair() {
        facesUtil.adiconarMsgInfo("Volte Sempre!");
        return "index";
    }   
    
  public void carregarCadastro(){
      try{
          String valor = facesUtil.getParam("nome");
          if(valor!= null){
              Long nome = Long.parseLong(valor);
              
              ClienteDAO clienteDAO = new ClienteDAO();
              
              //cliente = clienteDAO.getListaCliente(nome);
          
          }
      }catch(RuntimeException ex){
                    
      }
  }
  
  public void editar(){
      try{
          ClienteDAO clienteDAO = new ClienteDAO();
          //clienteDAO.editar(cliente);
          
      }catch(RuntimeException ex){
                    
      }
  }
  
}
