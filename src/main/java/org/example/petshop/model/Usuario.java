package org.example.petshop.model;

public class Usuario {

    private int id;
    private String usuario;
    private String senha;

    private int tipo;

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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Usuario(String usuario, String senha){
        super();
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(){}
}
