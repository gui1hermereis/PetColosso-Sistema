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
    private TextField TextFieldCliente;

    @FXML
    private TextField TextFieldCpf;

    @FXML
    private TextField TextFieldTelefone;

    @FXML
    private TextField TextFieldRaca;

    @FXML
    private TextField TextFieldValor;

    @FXML
    private ChoiceBox<String> ChoiceBoxServicos;

    @FXML
    private TextArea TextAreaObservacoes;

    @FXML
    private DatePicker DataPickerData;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    private void abrirServicos() {
        String selecServ = ChoiceBoxServicos.getValue();
        if (selecServ!= null && selecServ.equals("Novo Serviço...")) {
            abrirJanelaServicos();
        }
    }

    private void abrirJanelaServicos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/CadastroServicos.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Serviços");

            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void alterarCadastro(ActionEvent event) {
        TextFieldCliente.setEditable(true);
        TextFieldCpf.setEditable(true);
        TextFieldTelefone.setEditable(true);
        TextFieldRaca.setEditable(true);
        TextFieldValor.setEditable(true);
        DataPickerData.setEditable(true);
        TextAreaObservacoes.setEditable(true);
    }

    @FXML
    void excluirCadastro (ActionEvent event) {
    }


    private ObservableList<Agendamento> cadastro = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TextFieldCliente.setEditable(false);
        TextFieldCliente.setText("");
        TextFieldCpf.setEditable(false);
        TextFieldCpf.setText("");
        TextFieldTelefone.setEditable(false);
        TextFieldTelefone.setText("");
        TextFieldRaca.setEditable(false);
        TextFieldRaca.setText("");
        TextFieldValor.setEditable(false);
        TextFieldValor.setText("");
        DataPickerData.setEditable(true);
        TextAreaObservacoes.setEditable(true);
        TextAreaObservacoes.setText("");

        Image excluir = new Image(getClass().getResource("/org/example/petshop/icons/excluir.png").toExternalForm());
        ImageView excl = new ImageView(excluir);
        BtnExcluir.setGraphic(excl);

        Image alt = new Image(getClass().getResource("/org/example/petshop/icons/setas-flechas.png").toExternalForm());
        ImageView alterar = new ImageView(alt);
        alterar.setFitWidth(16);
        alterar.setFitHeight(16);
        BtnAlterar.setGraphic(alterar);
    }
}
