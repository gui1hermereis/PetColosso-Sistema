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
import org.example.petshop.model.Cliente;
import org.example.petshop.modelDAO.ClienteDAO;


public class EditarAgendamentoController implements Initializable {

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
    private Button BtnSalvar;

    private void abrirServicos() {
        String selecServ = ChoiceBoxServicos.getValue();
        if (selecServ!= null && selecServ.equals("Novo Serviço...")) {
            abrirJanelaServicos();
        }
    }

    private void abrirJanelaServicos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/Servicos.fxml"));
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
    void editarAgendamento(ActionEvent event) {
    }

    private ObservableList<Agendamento> cadastro = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TextFieldCliente.setEditable(true);
        TextFieldCliente.setText("");
        TextFieldCpf.setEditable(true);
        TextFieldCpf.setText("");
        TextFieldTelefone.setEditable(true);
        TextFieldTelefone.setText("");
        TextFieldRaca.setEditable(true);
        TextFieldRaca.setText("");
        TextFieldValor.setEditable(true);
        TextFieldValor.setText("");
        DataPickerData.setEditable(true);
        TextAreaObservacoes.setEditable(true);
        TextAreaObservacoes.setText("");

        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);
    }
}
