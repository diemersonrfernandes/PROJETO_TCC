package br.com.projeto.controle;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.PerfilDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.facade.ClienteFacade;
import br.com.projeto.facade.UsuarioFacade;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.util.facesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//ManagedBean faz a ligação com interface com usuário (tela)
@ManagedBean(name = "clienteBEAN")
@RequestScoped
public class ClienteBEAN implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ClienteFacade clienteFacade;

    //private Cliente cliente; //get e set
    private ClienteDAO clienteDAO;
    // private UsuarioDAO usuarioDAO;
    private Cliente clienteSelecionado;//GET SET
    private List<ClienteDAO> lista;//GET SET

    public ClienteBEAN() {
        //cliente = new Cliente();
        //clienteDAO = new ClienteDAO();
        clienteSelecionado = new Cliente();
    }
    
    @PostConstruct
    public void init() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAO();
        }
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

//        if (clienteDAO == null) {
//            clienteDAO = new ClienteDAO();

            Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (parameterMap != null && Boolean.valueOf(parameterMap.get("edit"))) {
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                UsuarioDAO usuario = (UsuarioDAO) attr.getRequest().getSession().getAttribute("usuario");
                clienteDAO = getClienteByUsuario(usuario);
                clienteDAO.setIdusuario(usuario);
            }
//        }
        return clienteDAO;
    }

    public void setCliente(ClienteDAO cliente) {
        this.clienteDAO = cliente;
    }

//GET E SET DE LISTA
    public ClienteDAO getClienteByUsuario(UsuarioDAO usuarioDAO) throws NoResultException {
        if (clienteFacade == null) {
            clienteFacade = new ClienteFacade();
        }

        return clienteFacade.FindByUsuario(usuarioDAO);
    }

    public List<ClienteDAO> getLista() {
        if (clienteFacade == null) {
            clienteFacade = new ClienteFacade();
        }
        lista = clienteFacade.findAll();
        return lista;
    }

    public void setLista(List<ClienteDAO> lista) {
        this.lista = lista;
    }

//BOTÃO CONFIRMAR    
    public String confirmar() throws IOException {

        if (clienteDAO.getId() > 0) {
            clienteFacade.edit(clienteDAO);
        } else {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setNmusuario(clienteDAO.getIdusuario().getNmusuario());
            usuarioDAO.setDeSenha(clienteDAO.getIdusuario().getDeSenha());

            PerfilDAO perfil = new PerfilDAO();
            // 3 = Cliente
            perfil.setIdperfil(3);
            usuarioDAO.setIdperfil(perfil);

            usuarioFacade.create(usuarioDAO);

            clienteDAO.setIdusuario(usuarioDAO);

            clienteFacade.create(clienteDAO);
        }
        facesUtil.adiconarMsgInfo("Cadastro realizado com sucesso!");
        return "pedido.xhtml?faces-redirect=true";
    }
//BOTÃO ALTERAR    

    public void alterar() {
//        clienteDAO.update(cliente);
//        lista = clienteDAO.getListaCliente();
//        cliente = new Cliente();
    }

    public String sair() {
        facesUtil.adiconarMsgInfo("Volte Sempre!");
        return "index";
    }

    public void carregarCadastro() {
        try {
            String valor = facesUtil.getParam("nome");
            if (valor != null) {
                Long nome = Long.parseLong(valor);

                ClienteDAO clienteDAO = new ClienteDAO();

                //cliente = clienteDAO.getListaCliente(nome);
            }
        } catch (RuntimeException ex) {

        }
    }

    public void editar() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            //clienteDAO.editar(cliente);

        } catch (RuntimeException ex) {

        }
    }

}
