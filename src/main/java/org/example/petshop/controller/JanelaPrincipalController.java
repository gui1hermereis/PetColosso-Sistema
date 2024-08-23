package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JanelaPrincipalController implements Initializable {

    @FXML
    private Button buttonSair;

    @FXML
    private Button buttonAgenda;

    @FXML
    private Button buttonServicos;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonUsuarios;

    private int nivelAcesso;

    @FXML
    void agenda(ActionEvent event) {
        try {
            abrirTelas("Agenda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void servicos(ActionEvent event) {
        try {
            abrirTelas("Servicos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clientes(ActionEvent event) {
        try {
            abrirTelas("Clientes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void usuarios(ActionEvent event) {
        try {
            abrirTelas("Usuarios");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) buttonSair.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/Login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();

            stage.close();
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            loginStage.setResizable(false);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirTelas(String tela) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/petshop/view/" + tela + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tela");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
        atualizarVisibilidadeBotoes();
    }

    private void atualizarVisibilidadeBotoes() {
        buttonUsuarios.setVisible(nivelAcesso == 1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/saida.png").toExternalForm());
        ImageView sairIcone = new ImageView(salvar);
        sairIcone.setFitHeight(13);
        sairIcone.setFitWidth(13);
        buttonSair.setGraphic(sairIcone);
    }
}