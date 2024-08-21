package org.example.petshop.modelDAO;

import org.example.petshop.model.Servicos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicosDAO {
    public void cadastrar(Servicos servicos) {
        String sql = "INSERT INTO servicos (descricao, valor) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, servicos.getDescricao());
            ps.setFloat(2, servicos.getValor());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(Servicos servicos) {
        String sql = "UPDATE servicos SET descricao = ?, valor = ? WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, servicos.getDescricao());
            ps.setFloat(2, servicos.getValor());
            ps.setInt(3, servicos.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Servicos servicos) {
        String sql1 = "DELETE FROM servicos WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql1);
            ps.setInt(1, servicos.getId());
            ps.execute();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Servicos> listar() {
        List<Servicos> resultado = new ArrayList<>();
        Statement smt = null;
        ResultSet resultSet = null;

        try {
            smt = Conexao.getConexao().createStatement();
            resultSet = smt.executeQuery("SELECT * FROM servicos");

            while (resultSet.next()) {
                Servicos c = new Servicos();
                c.setDescricao(resultSet.getString("descricao"));
                c.setValor(resultSet.getFloat("valor"));

                resultado.add(c);
            }

            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}