package org.example.petshop.model;

import java.util.Date;

public class Agendamento {
    private int id;
    private String raca;
    private String data;
    private String observacoes;
    private String clienteNome;
    private String clienteCpf;
    private String clienteTelefone;
    private String servicoDescricao;
    private String servicoValor;
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

    public String getData() {return data;}

    public void setData(String data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {this.clienteNome = clienteNome;}

    public String getServicoDescricao() {
        return servicoDescricao;
    }

    public void setServicoDescricao(String servicoDescricao) {
        this.servicoDescricao = servicoDescricao;
    }

    public String getServicoValor() {
        return servicoValor;
    }

    public void setServicoValor(String servicoValor) {
        this.servicoValor = servicoValor;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public Agendamento(int id,String raca, String data, String observacoes, String clienteNome,String clienteCpf,String clienteTelefone, String servicoDescricao,String servicoValor) {
        super();
        this.id = id;
        this.raca = raca;
        this.data = data;
        this.observacoes = observacoes;
        this. clienteNome = clienteNome;
        this. clienteCpf= clienteCpf;
        this. clienteTelefone= clienteTelefone;
        this.servicoDescricao = servicoDescricao;
        this.servicoValor = servicoValor;
    }

    public Agendamento(int id, String raca, String data, String observacoes, int idCliente, int idServico) {
        super();
        this.id = id;
        this.raca = raca;
        this.data = data;
        this.observacoes = observacoes;
        this.idCliente = idCliente;
        this.idServico = idServico;
    }

    public Agendamento() {}
}
