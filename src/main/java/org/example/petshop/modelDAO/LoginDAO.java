package org.example.petshop.modelDAO;

import org.example.petshop.model.Usuarios;
import org.example.petshop.utils.CryptoUtil;
import javax.crypto.SecretKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    public static Usuarios logar(Usuarios usuarios) {
        String sql = "SELECT senha, nivelAcesso FROM usuarios WHERE usuario = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                String encryptedPassword = rs.getString("senha");
                SecretKey secretKey = CryptoUtil.getTestKey();
                String decryptedPassword = CryptoUtil.decrypt(encryptedPassword, secretKey);

                if (decryptedPassword.equals(usuarios.getSenha())) {
                    int nivelAcesso = rs.getInt("nivelAcesso");
                    usuarios.setNivelAcesso(nivelAcesso);
                    return usuarios;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao logar: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}