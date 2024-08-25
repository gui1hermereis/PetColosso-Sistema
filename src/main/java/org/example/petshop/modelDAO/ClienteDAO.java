package org.example.petshop.modelDAO;

import org.example.petshop.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO clientes (NOME, CPF, TELEFONE) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?,  telefone = ? WHERE idCliente = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, cliente.getIdCliente());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Cliente cliente) {
        String sql1 = "DELETE FROM agendamentos WHERE idCliente = ?";
        String sql2 = "DELETE FROM clientes WHERE idCliente = ?";

        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            ps1 = Conexao.getConexao().prepareStatement(sql1);
            ps1.setInt(1, cliente.getIdCliente());
            ps1.execute();

            ps2 = Conexao.getConexao().prepareStatement(sql2);
            ps2.setInt(1, cliente.getIdCliente());
            ps2.execute();

            ps1.close();
            ps2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listar() {
        List<Cliente> resultado = new ArrayList<>();
        Statement smt = null;
        ResultSet resultSet = null;

        try {
            smt = Conexao.getConexao().createStatement();
            resultSet = smt.executeQuery("SELECT * FROM clientes");

            while (resultSet.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(resultSet.getInt("idCliente"));
                c.setNome(resultSet.getString("nome"));
                c.setCpf(resultSet.getString("cpf"));
                c.setTelefone(resultSet.getString("telefone"));

                resultado.add(c);
            }

            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
