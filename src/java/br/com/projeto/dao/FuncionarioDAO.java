package br.com.projeto.dao;

import br.com.projeto.conexaoBD.Conexao;
import br.com.projeto.modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Session;
import javax.persistence.Query;


public class FuncionarioDAO {
    private PreparedStatement ps; //prepara os paramêtros a serem inseridos no BD
    private ResultSet rs; //guarda os dados vindos do BD
    private Connection con; // conexão com o BD
    private String sql;
    
public FuncionarioDAO(){
    con = Conexao.abreConexao();   
}   
 public boolean insert(Funcionario funcionario){
        sql = "insert into funcionario (id,nome,matricula,data_admissional,login,senha)"
                + "values(?,?,?,?,?,?)";
        try{
             ps = con.prepareStatement(sql);
            ps.setInt(1, funcionario.getId());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getMatricula());
            ps.setString(4, funcionario.getData_admissional());
            ps.setString(5, funcionario.getLogin());
            ps.setString(6, funcionario.getSenha());
                       
            return ps.execute();
        }catch (SQLException erro){  
            erro.printStackTrace();
            return false;
        }  
}
 
 public List<Funcionario> getListaFuncionario(){
        sql = "select * from funcionario ";
        List<Funcionario> listaFun = new ArrayList<>();
        Funcionario funcionario;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("ID"));
                funcionario.setNome(rs.getString("NOME"));
                funcionario.setMatricula(rs.getString("MATRICULA"));
                funcionario.setData_admissional(rs.getString("DATA_ADMISSIONAL"));
                funcionario.setLogin(rs.getString("LOGIN"));
                
                listaFun.add(funcionario);
            }
        }catch(SQLException err){
            err.printStackTrace();
        }  
        return listaFun;
    }

 
 
 
}


