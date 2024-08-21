package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Agendamento;


public class AgendaController implements Initializable {

    @FXML
    private Button BtnCadastrar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnExcluir;

    @FXML
    void cadastrarAgendamento(ActionEvent event) {
        try {
            abrirTelas("CadastrarAgendamento");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarAgendamento(ActionEvent event) {
        try {
            abrirTelas("EditarAgendamento");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void excluirCadastro (ActionEvent event) {
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

    private ObservableList<Agendamento> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnCadastrar.setGraphic(Salvar);

        Image excluir = new Image(getClass().getResource("/org/example/petshop/icons/excluir.png").toExternalForm());
        ImageView excl = new ImageView(excluir);
        BtnExcluir.setGraphic(excl);

        Image alt = new Image(getClass().getResource("/org/example/petshop/icons/setas-flechas.png").toExternalForm());
        ImageView alterar = new ImageView(alt);
        alterar.setFitWidth(16);
        alterar.setFitHeight(16);
        BtnEditar.setGraphic(alterar);
    }
}
