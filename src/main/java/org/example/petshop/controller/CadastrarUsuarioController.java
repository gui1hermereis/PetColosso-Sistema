package org.example.petshop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.example.petshop.model.Usuarios;
import org.example.petshop.modelDAO.UsuariosDAO;

public class CadastrarUsuarioController implements Initializable {

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private TextField TextFieldSenha;

    @FXML
    private TextField TextFieldNivel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);

        TextFieldNivel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 1) {
                    TextFieldNivel.setText(oldValue);
                }
                if (!TextFieldNivel.getText().matches("[01]*")) {
                    TextFieldNivel.setText(oldValue);
                }
            }
        });
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String usuario = TextFieldUsuario.getText();
        String senha = TextFieldSenha.getText();
        String nivelAcessoText = TextFieldNivel.getText();

        if (usuario.isEmpty()) {
            exibirMensagem("Erro", "O campo 'Usuário' não pode estar vazio.");
            return;
        }

        if (senha.isEmpty()) {
            exibirMensagem("Erro", "O campo 'Senha' não pode estar vazio.");
            return;
        }

        if (nivelAcessoText.isEmpty()) {
            exibirMensagem("Erro", "O campo 'Nível de Acesso' não pode estar vazio.");
            return;
        }

        int nivelAcesso;
        try {
            nivelAcesso = Integer.parseInt(nivelAcessoText);
        } catch (NumberFormatException e) {
            exibirMensagem("Erro", "O campo 'Nível de Acesso' deve ser um número inteiro.");
            return;
        }

        Usuarios usuarios = new Usuarios(0, usuario, senha, nivelAcesso);
        new UsuariosDAO().cadastrar(usuarios);
        exibirMensagem("Sucesso", "Usuário cadastrado com sucesso!");
    }

    private void exibirMensagem(String titulo, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait().ifPresent(response -> {
            ((Stage) BtnSalvar.getScene().getWindow()).close();
        });
    }
}
