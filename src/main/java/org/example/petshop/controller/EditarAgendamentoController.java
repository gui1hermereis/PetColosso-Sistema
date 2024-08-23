package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Agendamento;
import org.example.petshop.modelDAO.AgendaDAO;

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

    private Agendamento agendamento;

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
        String raca = TextFieldRaca.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = DataPickerData.getValue().format(formatter);
        String observacoes = TextAreaObservacoes.getText();

        agendamento.setRaca(raca);
        agendamento.setData(data);
        agendamento.setObservacoes(observacoes);
        agendamento.setIdCliente(12);
        agendamento.setIdServico(2);

        new AgendaDAO().editar(agendamento);
        exibirMensagem("Sucesso", "Cliente editado com sucesso!");
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

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
        TextFieldRaca.setText(agendamento.getRaca());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(agendamento.getData(), formatter);
        DataPickerData.setValue(data);
        TextAreaObservacoes.setText(agendamento.getObservacoes());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);
    }
}
