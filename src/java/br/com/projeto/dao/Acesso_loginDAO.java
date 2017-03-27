package br.com.projeto.dao;

import br.com.projeto.conexaoBD.Conexao;
import br.com.projeto.modelo.Acesso_login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Acesso_loginDAO {
     private PreparedStatement ps; //prepara os paramêtros a serem inseridos no BD
    private ResultSet rs; //guarda os dados vindos do BD
    private Connection con; //representa a conexão com o BD
    private String sql;
    
    public Acesso_loginDAO(){
    con = Conexao.abreConexao();
}
public boolean select(Acesso_login login){     
    sql = "select * from cliente where login = ? and senha = ? ";
        
        try{
         ps = con.prepareStatement(sql);
         ps.setString(1, login.getLogin());
         ps.setString(2, login.getSenha());
         rs = ps.executeQuery();
         if (rs.next()){
             String perfil= rs.getString(3);
             System.out.println(perfil);
             if (login.getLogin().equals(rs.getString("login")) || login.getSenha().equals(rs.getString("senha"))){
                 
                 return true;
             }else{
                 
             
                 return false;
                 
                 
             }
         }else{
             
             return false;
         }
         
       
         
     } catch(SQLException erro){
         erro.printStackTrace();
         return false;
     }
    }
    
}