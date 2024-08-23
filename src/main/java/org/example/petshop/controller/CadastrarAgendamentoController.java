package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import org.example.petshop.model.Agendamento;
import org.example.petshop.modelDAO.AgendaDAO;


public class CadastrarAgendamentoController implements Initializable {

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
    void salvarAgendamento(ActionEvent event) {
        String raca = TextFieldRaca.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = DataPickerData.getValue().format(formatter);
        String observacoes = TextAreaObservacoes.getText();

        Agendamento agendamento = new Agendamento(0, raca, data, observacoes, 12, 2);
        new AgendaDAO().cadastrar(agendamento);
        exibirMensagem("Sucesso", "Cliente cadastrado com sucesso!");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldCliente.setEditable(false);
        TextFieldCliente.setText("");
        TextFieldCpf.setEditable(false);
        TextFieldCpf.setText("");
        TextFieldTelefone.setEditable(false);
        TextFieldTelefone.setText("");
        TextFieldRaca.setEditable(true);
        TextFieldRaca.setText("");
        TextFieldValor.setEditable(false);
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
