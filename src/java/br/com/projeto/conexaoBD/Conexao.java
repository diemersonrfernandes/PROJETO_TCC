package br.com.projeto.conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
     private static Connection con=null;
    
    public static Connection abreConexao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_tcc", "root", "admin");
            return con;
        } catch (ClassNotFoundException ex) {
            return con;
        } catch (SQLException ex){
            return con;
        }
    }
}
