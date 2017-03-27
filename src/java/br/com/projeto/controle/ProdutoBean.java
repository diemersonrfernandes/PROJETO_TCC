package br.com.projeto.controle;

import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.modelo.Produto;
import br.com.projeto.util.facesUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class ProdutoBean {
 
    private Produto produto;
    private ProdutoDAO produtoDAO;
    private List<Produto> listaProduto;
    private Produto produtoSelecionado;
   
    public ProdutoBean(){
        
        produto = new Produto();
        produtoDAO = new ProdutoDAO();
        produtoSelecionado = new Produto();
        listaProduto = produtoDAO.getListaProduto();
            
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
    
    
    public void cadastrarProduto(){
        produtoDAO.insert(produto);
        produto = new Produto();         
        facesUtil.adiconarMsgInfo("Produto cadastrado com sucesso!");
        
    }
    
}
