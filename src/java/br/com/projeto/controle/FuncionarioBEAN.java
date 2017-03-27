package br.com.projeto.controle;

import br.com.projeto.dao.FuncionarioDAO;
import br.com.projeto.modelo.Funcionario;
import br.com.projeto.util.facesUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "funBean")
@SessionScoped

public class FuncionarioBEAN implements Serializable{

    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDAO;
    private Funcionario funcionarioSelecionado;//GET SET
    private List<Funcionario> lista;//GET SET

    public FuncionarioBEAN() {

        funcionario = new Funcionario();
        funcionarioDAO = new FuncionarioDAO();
        funcionarioSelecionado = new Funcionario();
        lista = funcionarioDAO.getListaFuncionario();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }

    public void cadastrar() {
        funcionarioDAO.insert(funcionario);
        funcionario = new Funcionario();
        facesUtil.adiconarMsgInfo("Funcionário cadastrado com sucesso!");
        lista = funcionarioDAO.getListaFuncionario();

    }
}
