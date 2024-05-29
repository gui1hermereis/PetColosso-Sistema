package org.example.petshop.model;

public class Servicos {
    private int id;
    private String descricao;
    private float valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Servicos(String descricao, float valor){
        super();
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servicos(){
    }
}
