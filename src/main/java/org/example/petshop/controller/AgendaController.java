package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import org.example.petshop.model.Agendamento;
import org.example.petshop.modelDAO.AgendaDAO;

public class AgendaController implements Initializable {

    @FXML
    private TableView<Agendamento> TableViewAgenda;

    @FXML
    private TableColumn<Agendamento, Integer> TableColumnId;

    @FXML
    private TableColumn<Agendamento, String> TableColumnCliente;

    @FXML
    private TableColumn<Agendamento, String> TableColumnCpf;

    @FXML
    private TableColumn<Agendamento, String> TableColumnTelefone;

    @FXML
    private TableColumn<Agendamento, String> TableColumnRaca;

    @FXML
    private TableColumn<Agendamento, String> TableColumnObservacoes;

    @FXML
    private TableColumn<Agendamento, String> TableColumnData;

    @FXML
    private TableColumn<Agendamento, String> TableColumnServico;

    @FXML
    private TableColumn<Agendamento, String> TableColumnValor;

    @FXML
    private Button BtnCadastrar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnExcluir;

    private Agendamento agendamentoSelecionado;

    private void carregarAgenda() {
        List<Agendamento> agenda = agendaDAO.listar();
        agendamento.setAll(agenda);
        TableViewAgenda.setItems(agendamento);
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
    void editarAgendamento(ActionEvent event) {
        try {
            abrirTelas("EditarAgendamento");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void excluirAgendamento(ActionEvent event) {
        if (agendamentoSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir esse agendamento?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                new AgendaDAO().excluir(agendamentoSelecionado);
                agendamento.remove(agendamentoSelecionado);
                carregarAgenda();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um agendamento para excluir.");
            alert.showAndWait();
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

    @FXML
    void selecionarAgendamento(MouseEvent event) {
        if (event.getClickCount() == 1) {
            agendamentoSelecionado = TableViewAgenda.getSelectionModel().getSelectedItem();
        }
    }

    private ObservableList<Agendamento> agendamento = FXCollections.observableArrayList();
    private AgendaDAO agendaDAO = new AgendaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumnId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        TableColumnCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteNome()));
        TableColumnCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteCpf()));
        TableColumnTelefone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteTelefone()));
        TableColumnServico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicoDescricao()));
        TableColumnRaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRaca()));
        TableColumnData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData()));
        TableColumnObservacoes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObservacoes()));
        TableColumnValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicoValor()));

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

        carregarAgenda();
        TableViewAgenda.setOnMouseClicked(this::selecionarAgendamento);
    }
}
