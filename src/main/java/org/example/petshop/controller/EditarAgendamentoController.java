package org.example.petshop.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Agendamento;
import org.example.petshop.model.Servicos;
import org.example.petshop.modelDAO.AgendaDAO;
import org.example.petshop.modelDAO.ServicosDAO;
import org.example.petshop.utils.MultiMaskTextFormatter;

public class EditarAgendamentoController implements Initializable {

    @FXML
    private TextField TextFieldCliente;

    @FXML
    private TextField TextFieldCpf;

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

    private List<Servicos> listaServicos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataPickerData.setEditable(false);
        TextFieldValor.setEditable(false);

        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);

        listaServicos = ServicosDAO.listar();
        for (Servicos servico : listaServicos) {
            ChoiceBoxServicos.getItems().add(servico.getDescricao());
        }

        ChoiceBoxServicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                for (Servicos servico : listaServicos) {
                    if (servico.getDescricao().equals(newValue)) {
                        TextFieldValor.setText(String.valueOf(servico.getValor()));
                        break;
                    }
                }
            }
        });

        TextFieldCpf.setTextFormatter(new MultiMaskTextFormatter(MultiMaskTextFormatter.MaskType.CPF));
    }

    @FXML
    void editarAgendamento(ActionEvent event) {
        String raca = TextFieldRaca.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = DataPickerData.getValue().format(formatter);
        String observacoes = TextAreaObservacoes.getText();
        String Clientenome = TextFieldCliente.getText();
        String Clientecpf = TextFieldCpf.getText();
        String servicoDescricao = ChoiceBoxServicos.getValue();
        String servicoValor = TextFieldValor.getText();

        agendamento.setRaca(raca);
        agendamento.setData(data);
        agendamento.setObservacoes(observacoes);
        agendamento.setClienteNome(Clientenome);
        agendamento.setClienteCpf(Clientecpf);
        agendamento.setServicoDescricao(servicoDescricao);
        agendamento.setServicoValor(servicoValor);

        boolean sucesso = new AgendaDAO().editar(agendamento);

        if (sucesso) {
            exibirMensagem("Sucesso", "Agendamento editado com sucesso!");
        } else {
            exibirMensagem("Erro", "Cliente não encontrado. O agendamento não foi realizado.");
        }
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
        TextFieldCliente.setText(agendamento.getClienteNome());
        TextFieldCpf.setText(agendamento.getClienteCpf());
        ChoiceBoxServicos.setValue(agendamento.getServicoDescricao());
        TextFieldValor.setText(agendamento.getServicoValor());
    }
}
