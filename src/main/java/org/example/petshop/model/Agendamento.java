package org.example.petshop.model;

public class Agendamento {
    private int id;
    private String raca;
    private String data;
    private String observacoes;
    private int idCliente;
    private int idServico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdCliente() {

        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Agendamento(String raca, String data, String observacoes) {
        super();
        this.raca = raca;
        this.data = data;
        this.observacoes = observacoes;
    }

    public Agendamento() {}
}
