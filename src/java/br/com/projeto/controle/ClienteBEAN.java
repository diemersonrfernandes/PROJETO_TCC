package br.com.projeto.controle;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.util.facesUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


//ManagedBean faz a ligação com interface com usuário (tela)

@ManagedBean 
@ViewScoped

public class ClienteBEAN {
    
    private Cliente cliente; //get e set
    private ClienteDAO clienteDAO;
    private Cliente clienteSelecionado;//GET SET
    private List<Cliente> lista;//GET SET
    
    
public ClienteBEAN(){
    cliente = new Cliente();
    clienteDAO = new ClienteDAO();
    clienteSelecionado = new Cliente();
    lista = clienteDAO.getListaCliente();
    
}    
//GET E SET CLIENTESELECIONADO

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

//GET e SET cliente

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    

//GET E SET DE LISTA

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
    
//BOTÃO CONFIRMAR    
    public String confirmar(){  
        facesUtil.adiconarMsgInfo("Cadastro realizado com sucesso!");
        clienteDAO.insert(cliente);
        cliente = new Cliente();  
        lista = clienteDAO.getListaCliente();
        return "pedido";       
    }    
//BOTÃO ALTERAR    
    public void alterar(){
        clienteDAO.update(cliente);
        lista = clienteDAO.getListaCliente();
        cliente = new Cliente();
    }
    
  public String sair() {
        facesUtil.adiconarMsgInfo("Volte Sempre!");
        return "tela";
    }   
    
  public void carregarCadastro(){
      try{
          String valor = facesUtil.getParam("nome");
          if(valor!= null){
              Long nome = Long.parseLong(valor);
              
              ClienteDAO clienteDAO = new ClienteDAO();
              
              cliente = clienteDAO.getListaCliente(nome);
          
          }
      }catch(RuntimeException ex){
                    
      }
  }
  
  public void editar(){
      try{
          ClienteDAO clienteDAO = new ClienteDAO();
          clienteDAO.editar(cliente);
          
      }catch(RuntimeException ex){
                    
      }
  }
  
}
