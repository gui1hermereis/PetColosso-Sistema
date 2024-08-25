package org.example.petshop.modelDAO;

import org.example.petshop.model.Usuarios;
import org.example.petshop.utils.CryptoUtil;
import javax.crypto.SecretKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    private static final SecretKey SECRET_KEY = CryptoUtil.getTestKey();

    public void cadastrar(Usuarios usuarios) {
        String sql = "INSERT INTO usuarios (USUARIO, SENHA, NIVELACESSO) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            String senhaCriptografada = CryptoUtil.encrypt(usuarios.getSenha(), SECRET_KEY);

            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, senhaCriptografada);
            ps.setInt(3, usuarios.getNivelAcesso());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar(Usuarios usuarios) {
        String sql = "UPDATE usuarios SET USUARIO = ?, SENHA = ?, NIVELACESSO = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            String senhaCriptografada = CryptoUtil.encrypt(usuarios.getSenha(), SECRET_KEY);

            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, senhaCriptografada);
            ps.setInt(3, usuarios.getNivelAcesso());
            ps.setInt(4, usuarios.getId());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Usuarios usuarios) {
        String sql1 = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql1);
            ps.setInt(1, usuarios.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuarios> listar() {
        List<Usuarios> resultado = new ArrayList<>();
        Statement smt = null;
        ResultSet resultSet = null;

        try {
            smt = Conexao.getConexao().createStatement();
            resultSet = smt.executeQuery("SELECT * FROM USUARIOS");

            while (resultSet.next()) {
                Usuarios usuarios = new Usuarios();
                usuarios.setId(resultSet.getInt("id"));
                usuarios.setUsuario(resultSet.getString("usuario"));
                usuarios.setSenha(resultSet.getString("senha"));
                usuarios.setNivelAcesso(resultSet.getInt("nivelAcesso"));

                resultado.add(usuarios);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}