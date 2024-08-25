package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Usuarios;
import org.example.petshop.modelDAO.LoginDAO;

public class LoginController implements Initializable {

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private PasswordField PasswordFieldSenha;

    @FXML
    private Button BtnEntrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image entrar = new Image(getClass().getResource("/org/example/petshop/icons/entrar.png").toExternalForm());
        ImageView Entrar = new ImageView(entrar);
        Entrar.setFitHeight(13);
        Entrar.setFitWidth(13);
        BtnEntrar.setGraphic(Entrar);
    }

    @FXML
    private void Entrar() {
        String usuario = TextFieldUsuario.getText();
        String senha = PasswordFieldSenha.getText();

        Usuarios logar = new Usuarios(0, usuario, senha, 0);

        Usuarios usuarioLogado = LoginDAO.logar(logar);

        if (usuarioLogado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/JanelaPrincipal.fxml"));
                Stage stage = (Stage) BtnEntrar.getScene().getWindow();
                Parent root = loader.load();

                JanelaPrincipalController controller = loader.getController();
                controller.setNivelAcesso(usuarioLogado.getNivelAcesso());

                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            exibirMensagem("Erro!!", "Usuário ou senha inválidos.");
        }
    }

    private void exibirMensagem(String titulo, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}
