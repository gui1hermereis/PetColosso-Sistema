package org.example.petshop.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.petshop.model.Agenda;

public class AgendaRepository {
    public void cadastrar(Agenda agenda) {
        String sql = "INSERT INTO cliente (CLIENTE, CPF, TELEFONE, RACA, DATA, OBSERVACOES) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, agenda.getCliente());
            ps.setString(2, agenda.getCpf());
            ps.setString(3, agenda.getTelefone());
            ps.setString(4, agenda.getRaca());
            ps.setString(5, agenda.getData());
            ps.setString(6, agenda.getObservacoes());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Agenda agenda) {
        String sql = "UPDATE CLIENTE SET CLIENTE=?, CPF =?, TELEFONE=?, RACA=?, DATA=?, OBSERVACOES=? WHERE idCliente=?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, agenda.getCliente());
            ps.setString(2, agenda.getCpf());
            ps.setString(3, agenda.getTelefone());
            ps.setString(4, agenda.getRaca());
            ps.setString(5, agenda.getData());
            ps.setString(6, agenda.getObservacoes());
            ps.setInt(7, agenda.getId());

            ps.execute();
            ps.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void excluir(Agenda agenda) {
        String sql = "DELETE FROM CLIENTE WHERE idCliente = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, agenda.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Agenda> listar() {
        String sql = "SELECT * FROM CONTALUZ ORDER BY idContaluz DESC";

        List<Agenda> conta = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();

            while (rset.next()) {

                Agenda agenda = new Agenda();

                agenda.setId(rset.getInt("idCliente"));
                agenda.setCliente(rset.getString("cliente"));
                agenda.setCpf(rset.getString("cpf"));
                agenda.setTelefone(rset.getString("telefone"));
                agenda.setRaca(rset.getString("raca"));
                agenda.setData(rset.getString("data"));
                agenda.setObservacoes(((ResultSet) rset).getString("observacoes"));

                conta.add(agenda);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return conta;
    }
}
