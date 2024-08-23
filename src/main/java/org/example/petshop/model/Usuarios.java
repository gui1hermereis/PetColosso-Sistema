package org.example.petshop.model;

public class Usuarios {

    private int id;
    private String usuario;
    private String senha;
    private int nivelAcesso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Usuarios(int id,String usuario, String senha, int nivelAcesso){
        super();
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuarios(){}
}
