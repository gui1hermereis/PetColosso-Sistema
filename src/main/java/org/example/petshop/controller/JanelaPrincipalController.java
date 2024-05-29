package org.example.petshop.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class JanelaPrincipalController {

    @FXML
    private Button buttonSair;

    @FXML
    private Button buttonAgenda;

    @FXML
    private Button buttnAgendar;

    @FXML
    private Button buttonServiços;

    @FXML
    void acessarAgenda(ActionEvent event) {
        try {
            abrirTelas("Agenda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) buttonSair.getScene().getWindow();

        try {
            stage.close();
            abrirTelas("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void acessarAgendamento(ActionEvent event) {
        try {
            abrirTelas("AgendarServicos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void acessarServiços(ActionEvent event) {
        try {
            abrirTelas("CadastroServicos");
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
}
