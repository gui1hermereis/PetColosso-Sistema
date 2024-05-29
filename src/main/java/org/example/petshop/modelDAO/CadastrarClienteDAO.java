package org.example.petshop.modelDAO;

import org.example.petshop.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarClienteDAO {

    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente (NOME, CPF, TELEFONE) VALUES (?, ?, ?)";

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
}
