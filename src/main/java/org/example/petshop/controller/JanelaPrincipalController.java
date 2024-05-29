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
    private Button buttonCadastrarServicos;

    @FXML
    private Button buttonVisualizarAgenda;

    @FXML
    private Button buttonCadastrarClientes;

    @FXML
    private Button buttonCadastrarAgendamento;

    @FXML
    void visualizarAgendamento(ActionEvent event) {
        try {
            abrirTelas("VisualizarAgenda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrarServicos(ActionEvent event) {
        try {
            abrirTelas("CadastrarServicos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrarCliente(ActionEvent event) {
        try {
            abrirTelas("CadastrarCliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrarAgendamento(ActionEvent event) {
        try {
            abrirTelas("CadastrarAgendamento");
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
