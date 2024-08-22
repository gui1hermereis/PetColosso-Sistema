package org.example.petshop.modelDAO;

import org.example.petshop.model.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    public void excluir(Agendamento agendamento) {
        String sql1 = "DELETE FROM agendamento WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql1);
            ps.setInt(1, agendamento.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Agendamento> listar() {
        List<Agendamento> resultado = new ArrayList<>();
        Statement smt = null;
        ResultSet resultSet = null;

        try {
            smt = Conexao.getConexao().createStatement();
            resultSet = smt.executeQuery("SELECT a.id, a.raca, a.data, a.observacoes, " +
                    "c.nome AS clienteNome, c.cpf AS clienteCpf, c.telefone AS clienteTelefone, " +
                    "s.descricao AS servicoDescricao, s.valor AS servicoValor " +
                    "FROM agendamento a " +
                    "JOIN cliente c ON a.idCliente = c.idCliente " +
                    "JOIN servicos s ON a.idServico = s.idServico");

            while (resultSet.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(resultSet.getInt("id"));
                agendamento.setRaca(resultSet.getString("raca"));
                agendamento.setData(resultSet.getString("data"));
                agendamento.setObservacoes(resultSet.getString("observacoes"));
                agendamento.setClienteNome(resultSet.getString("clienteNome"));
                agendamento.setClienteCpf(resultSet.getString("clienteCpf"));
                agendamento.setClienteTelefone(resultSet.getString("clienteTelefone"));
                agendamento.setServicoDescricao(resultSet.getString("servicoDescricao"));
                agendamento.setServicoValor(resultSet.getString("servicoValor"));

                resultado.add(agendamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
