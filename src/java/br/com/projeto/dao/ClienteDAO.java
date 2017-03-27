package br.com.projeto.dao;

import br.com.projeto.conexaoBD.Conexao;
import br.com.projeto.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    private PreparedStatement ps; //prepara os paramêtros a serem inseridos no BD
    private ResultSet rs; //guarda os dados vindos do BD
    private Connection con; //representa a conexão com o BD
    private String sql;
    
public ClienteDAO(){
    con = Conexao.abreConexao();
}    
    //método para fazer o insert pessoa
    public boolean insert(Cliente cliente){
        sql = "insert into cliente (id,nome,cpf,endereco,telefone,email,login,senha)"
                + "values(?,?,?,?,?,?,?,?)";
        try{
             ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getEndereco());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getLogin());
            ps.setString(8, cliente.getSenha()); 
            
        return ps.execute();
        }catch (SQLException erro){  
            erro.printStackTrace();
            return false;
        }        
    }
    public List<Cliente> getListaCliente(){
        sql = "select * from cliente ";
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setLogin(rs.getString("LOGIN"));
                cliente.setSenha(rs.getString("SENHA"));
                lista.add(cliente);
            }
        }catch(SQLException err){
            err.printStackTrace();
        }  
        return lista;
    }//fim do metodo
    
    public boolean update(Cliente cliente){
        sql = "update cliente "+
                 " set nome = ? , "+
                 "     cpf = ? , "+
                 "     endereco = ?  "+
                 "     telefone = ? "+
                 "     email =? "+
                 "     login =?"+
                 "      senha=?"+
                 "  where id = ? ";     
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getLogin());
            ps.setString(7, cliente.getSenha());
            return ps.execute();
        }catch (SQLException erro){            
            return false;
        }
        
    }
    
    public boolean editar(Cliente cliente){
        sql="update cliente set nome=?, endereco=?, telefone=?, email=?, login=?, senha=?";
        con = Conexao.abreConexao();
        try{
            
            ps=con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getLogin());
            ps.setString(6, cliente.getSenha());
            

        return ps.execute();
        
        }catch (SQLException erro){  
            erro.printStackTrace();
            return false;
        }
    }

    public Cliente getListaCliente(Long nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
