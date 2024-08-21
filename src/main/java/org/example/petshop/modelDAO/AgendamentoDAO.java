package org.example.petshop.modelDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.petshop.model.Agendamento;

public class AgendamentoDAO {
    public void cadastrar(Agendamento agendamento) {
        String sql = "INSERT INTO cliente (CLIENTE, CPF, TELEFONE, RACA, DATA, OBSERVACOES) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, agendamento.getRaca());
            ps.setString(2, agendamento.getData());
            ps.setString(3, agendamento.getObservacoes());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
