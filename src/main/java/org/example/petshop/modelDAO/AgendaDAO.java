package org.example.petshop.modelDAO;

import org.example.petshop.model.Agendamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
    public boolean cadastrar(Agendamento agendamento) {
        String sql1 = "SELECT IDCLIENTE FROM CLIENTE WHERE NOME LIKE ? AND CPF = ?";
        String sql2 = "SELECT IDSERVICO FROM SERVICOS WHERE DESCRICAO LIKE ? AND VALOR = ?";
        String sql = "INSERT INTO agendamento (RACA, DATA, OBSERVACOES, IDCLIENTE, IDSERVICO) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            ps1 = Conexao.getConexao().prepareStatement(sql1);
            ps1.setString(1, "%" + agendamento.getClienteNome() + "%");
            ps1.setString(2, agendamento.getClienteCpf());
            rs1 = ps1.executeQuery();

            int idCliente = -1;
            if (rs1.next()) {
                idCliente = rs1.getInt("IDCLIENTE");
            }

            rs1.close();
            ps1.close();

            ps2 = Conexao.getConexao().prepareStatement(sql2);
            ps2.setString(1, "%" + agendamento.getServicoDescricao() + "%");
            ps2.setString(2, agendamento.getServicoValor());
            rs2 = ps2.executeQuery();

            int idServico = -1;
            if (rs2.next()) {
                idServico = rs2.getInt("IDSERVICO");
            }

            rs2.close();
            ps2.close();

            if (idCliente != -1 && idServico != -1) {
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, agendamento.getRaca());
                ps.setString(2, agendamento.getData());
                ps.setString(3, agendamento.getObservacoes());
                ps.setInt(4, idCliente);
                ps.setInt(5, idServico);

                ps.execute();
                ps.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Agendamento agendamento) {
        String sql = "UPDATE agendamento SET RACA = ?, DATA = ?,  OBSERVACOES = ?, IDCLIENTE = ?, IDSERVICO = ? WHERE id = ?";
        String sql1 = "SELECT IDCLIENTE FROM CLIENTE WHERE NOME LIKE ? AND CPF = ?";
        String sql2 = "SELECT IDSERVICO FROM SERVICOS WHERE DESCRICAO LIKE ? AND VALOR = ?";

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            ps1 = Conexao.getConexao().prepareStatement(sql1);
            ps1.setString(1, "%" + agendamento.getClienteNome() + "%");
            ps1.setString(2, agendamento.getClienteCpf());
            rs1 = ps1.executeQuery();

            int idCliente = -1;
            if (rs1.next()) {
                idCliente = rs1.getInt("IDCLIENTE");
            }

            rs1.close();
            ps1.close();

            ps2 = Conexao.getConexao().prepareStatement(sql2);
            ps2.setString(1, "%" + agendamento.getServicoDescricao() + "%");
            ps2.setString(2, agendamento.getServicoValor());
            rs2 = ps2.executeQuery();

            int idServico = -1;
            if (rs2.next()) {
                idServico = rs2.getInt("IDSERVICO");
            }

            rs2.close();
            ps2.close();

            if (idCliente != -1 && idServico != -1) {
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, agendamento.getRaca());
                ps.setString(2, agendamento.getData());
                ps.setString(3, agendamento.getObservacoes());
                ps.setInt(4, idCliente);
                ps.setInt(5, idServico);
                ps.setInt(6, agendamento.getId());

                ps.execute();
                ps.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
                    "c.nome AS clienteNome, c.cpf AS clienteCpf, c.telefone AS clienteTelefone," +
                    "s.descricao AS servicoDescricao, s.valor AS servicoValor " +
                    "FROM agendamento a " +
                    "JOIN cliente c ON a.idCliente = c.idCliente " +
                    "JOIN servicos s ON a.idServico = s.idServico ORDER BY a.data DESC;");

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

    public List<Agendamento> listarPorData(String data) {
        List<Agendamento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT a.id, a.raca, a.data, a.observacoes, " +
                "c.nome AS clienteNome, c.cpf AS clienteCpf, c.telefone AS clienteTelefone," +
                "s.descricao AS servicoDescricao, s.valor AS servicoValor " +
                "FROM agendamento a " +
                "JOIN cliente c ON a.idCliente = c.idCliente " +
                "JOIN servicos s ON a.idServico = s.idServico " +
                "WHERE a.data = ? ORDER BY a.data DESC;";

        try {
            if (data == null || "Nenhum".equals(data)) {
                return listar();
            } else {
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, data);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(rs.getInt("id"));
                    agendamento.setRaca(rs.getString("raca"));
                    agendamento.setData(rs.getString("data"));
                    agendamento.setObservacoes(rs.getString("observacoes"));
                    agendamento.setClienteNome(rs.getString("clienteNome"));
                    agendamento.setClienteCpf(rs.getString("clienteCpf"));
                    agendamento.setClienteTelefone(rs.getString("clienteTelefone"));
                    agendamento.setServicoDescricao(rs.getString("servicoDescricao"));
                    agendamento.setServicoValor(rs.getString("servicoValor"));

                    resultado.add(agendamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<Agendamento> listarPorServicos(String servico) {
        List<Agendamento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT a.id, a.raca, a.data, a.observacoes, " +
                "c.nome AS clienteNome, c.cpf AS clienteCpf, c.telefone AS clienteTelefone," +
                "s.descricao AS servicoDescricao, s.valor AS servicoValor " +
                "FROM agendamento a " +
                "JOIN cliente c ON a.idCliente = c.idCliente " +
                "JOIN servicos s ON a.idServico = s.idServico " +
                "WHERE s.descricao = ? ORDER BY a.data DESC;";

        try {
            if (servico == null || "Nenhum".equals(servico)) {
                return listar();
            } else {
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, servico);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(rs.getInt("id"));
                    agendamento.setRaca(rs.getString("raca"));
                    agendamento.setData(rs.getString("data"));
                    agendamento.setObservacoes(rs.getString("observacoes"));
                    agendamento.setClienteNome(rs.getString("clienteNome"));
                    agendamento.setClienteCpf(rs.getString("clienteCpf"));
                    agendamento.setClienteTelefone(rs.getString("clienteTelefone"));
                    agendamento.setServicoDescricao(rs.getString("servicoDescricao"));
                    agendamento.setServicoValor(rs.getString("servicoValor"));

                    resultado.add(agendamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
