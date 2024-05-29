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
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Agenda;
import org.example.petshop.repository.AgendaRepository;


public class AgendarServiçosController implements Initializable{

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
    void salvarAgendamento(ActionEvent event) {
        String cliente = TextFieldCliente.getText();
        String cpf = TextFieldCpf.getText();
        String telefone = TextFieldTelefone.getText();
        String raca = TextFieldRaca.getText();
        String valor = TextFieldValor.getText();
        String data = String.valueOf(DataPickerData.getValue());
        String observacoes = TextAreaObservacoes.getText();

        Agenda novoaAgendamento = new Agenda(cliente, cpf, telefone, raca, valor, data, observacoes);
        new AgendaRepository().cadastrar(novoaAgendamento);
        // Exibir mensagem de sucesso
        exibirMensagem("Sucesso", "Agendamento salvo com sucesso!");
    }

    private void exibirMensagem(String titulo, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

    private ObservableList<Agenda> cadastro = FXCollections.observableArrayList();

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
        TextFieldValor.setEditable(true);
        TextFieldValor.setText("");
        TextFieldValor.setEditable(true);
        TextFieldValor.setText("");
        TextAreaObservacoes.setEditable(true);
        TextAreaObservacoes.setText("");

        ChoiceBoxServicos.setOnAction(event -> abrirServicos());

        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        BtnSalvar.setGraphic(Salvar);
    }
}
