package br.com.projeto.controle;

import br.com.projeto.modelo.Funcionario;
import java.io.Serializable;
import java.security.Principal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ManagedBean(name = "securityController")
@SessionScoped
public class SecurityController implements Serializable{
 
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        
        String role = "FORBIDEN";
        if (principal != null && !principal.getName().equals(""))
            role = principal.getName();
        return role;
    }
    
     public String getNome() {
        return "Nome de merda";
    }
}