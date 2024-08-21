package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Usuario;
import org.example.petshop.modelDAO.LoginDAO;

public class LoginController implements Initializable{

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private PasswordField PasswordFieldSenha;

    @FXML
    private Button BtnEntrar;

    @FXML
    private void Entrar() {
        String usuario = TextFieldUsuario.getText();
        String senha = PasswordFieldSenha.getText();

        Usuario logar = new Usuario(usuario, senha);

        if (LoginDAO.logar(logar)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/JanelaPrincipal.fxml"));
                Stage stage = (Stage) BtnEntrar.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
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

    public void initialize(URL url, ResourceBundle rb) {
        TextFieldUsuario.setEditable(true);
        TextFieldUsuario.setText("");
        PasswordFieldSenha.setEditable(true);
        PasswordFieldSenha.setText("");

        Image entrar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Entrar = new ImageView(entrar);
        BtnEntrar.setGraphic(Entrar);
    }
}
