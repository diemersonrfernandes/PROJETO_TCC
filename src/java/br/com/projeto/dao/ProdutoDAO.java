package br.com.projeto.dao;

import br.com.projeto.conexaoBD.Conexao;
import br.com.projeto.modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
    private PreparedStatement ps; 
    private ResultSet rs; 
    private Connection con; 
    private String sql;
    
    public ProdutoDAO(){    
    con = Conexao.abreConexao();
    }
    
    public boolean insert(Produto produto){
        sql = "insert into produto (cod_produto,nome_produto,valor)"
                + "values(?,?,?)";
        try{
             ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getCod_produto());
            ps.setString(2, produto.getNome_produto());
            ps.setDouble(3, produto.getValor());
            
        return ps.execute();
        }catch (SQLException erro){  
            erro.printStackTrace();
            return false;
        }        
    }
    public List<Produto> getListaProduto(){
        sql = "select * from produto ";
        List<Produto> lista = new ArrayList<>();
        Produto produto;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                produto = new Produto();
                produto.setCod_produto(rs.getInt("COD_PRODUTO"));
                produto.setNome_produto(rs.getString("NOME_PRODUTO"));
                produto.setValor(rs.getDouble("VALOR"));
                
                lista.add(produto);
            }
        }catch(SQLException err){
            err.printStackTrace();
        }  
        return lista;
    }
}
