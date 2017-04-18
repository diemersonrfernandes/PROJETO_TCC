package br.com.projeto.controle;

import br.com.projeto.dao.Acesso_loginDAO;
import br.com.projeto.modelo.Acesso_login;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="acessoBean")
@SessionScoped

public class Acesso_loginBEAN implements Serializable{
    
    private Acesso_login acesso_login;
    private Acesso_loginDAO acesso_loginDAO;
    
    public Acesso_loginBEAN(){
        acesso_login = new Acesso_login();
        acesso_loginDAO = new Acesso_loginDAO();     
    }
    public String confirmar(){
        if(acesso_loginDAO.select(acesso_login))
            return "pedido";
        else
            return"cadastro";  
    }

    public Acesso_login getAcesso_login() {
        return acesso_login;
    }

    public void setAcesso_login(Acesso_login acesso_login) {
        this.acesso_login = acesso_login;
    }
    
    
}
