package br.com.projeto.modelo;


public class Usuario {
    
    private int idUsuario;
    private String nmUsuario;
    private Perfil perfil;
    private String deSenha;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getDeSenha() {
        return deSenha;
    }

    public void setDeSenha(String deSenha) {
        this.deSenha = deSenha;
    }
   
    
}
