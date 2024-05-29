package org.example.petshop.model;

public class Agenda {
    private int id;
    private String cliente;
    private String cpf;
    private String telefone;
    private String raca;
    private String data;
    private String observacoes;
    private int idServico;
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agenda(String cliente, String cpf, String telefone, String raca, String valor, String data, String observacoes) {
        super();
        this.cliente = cliente;
        this.cpf = cpf;
        this.telefone = telefone;
        this.raca = raca;
        this.data = data;
        this.observacoes = observacoes;
        this.valor = valor;
    }

    public Agenda() {}
}
