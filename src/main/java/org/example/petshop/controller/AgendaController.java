package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import org.example.petshop.model.Servicos;
import org.example.petshop.modelDAO.AgendaDAO;
import org.example.petshop.modelDAO.ServicosDAO;


public class AgendaController implements Initializable {

    @FXML
    private Label labelFaturamento;

    @FXML
    private TextField TextFieldFaturamento;

    @FXML
    private Label labelData;

    @FXML
    private Label labelServico;

    @FXML
    private ChoiceBox<String> ChoiceBoxServicos;

    @FXML
    private ChoiceBox<String> ChoiceBoxData;

    @FXML
    private TableView<Agendamento> TableViewAgenda;

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
    private Button BtnCadastrar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnExcluir;

    private List<Servicos> listaServicos;

    private Agendamento agendamentoSelecionado;

    private int nivelAcesso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldFaturamento.setEditable(false);

        TableColumnCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteNome()));
        TableColumnCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteCpf()));
        TableColumnTelefone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteTelefone()));
        TableColumnServico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicoDescricao()));
        TableColumnRaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRaca()));
        TableColumnData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData()));
        TableColumnObservacoes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObservacoes()));

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

        ChoiceBoxServicos.getItems().add("Nenhum");

        listaServicos = ServicosDAO.listar();
        for (Servicos servico : listaServicos) {
            ChoiceBoxServicos.getItems().add(servico.getDescricao());
        }

        ChoiceBoxServicos.getSelectionModel().select("Nenhum");

        ChoiceBoxServicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if ("Nenhum".equals(newValue)) {
                } else {
                    for (Servicos servico : listaServicos) {
                        if (servico.getDescricao().equals(newValue)) {
                            break;
                        }
                    }
                }
            }
        });

        ChoiceBoxData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filtrarAgendaPorData(newValue);
            }
        });

        ChoiceBoxServicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filtrarAgendaPorServico(newValue);
            }
        });

        carregarAgenda();
        atualizarFaturamento();
        TableViewAgenda.setOnMouseClicked(this::selecionarAgendamento);
    }

    private void carregarAgenda() {
        List<Agendamento> agenda = agendaDAO.listar();
        agendamento.setAll(agenda);
        TableViewAgenda.setItems(agendamento);

        ObservableList<String> datas = FXCollections.observableArrayList();
        for (Agendamento agendamento : agenda) {
            String data = agendamento.getData();
            if (!datas.contains(data)) {
                datas.add(data);
            }
        }
        ChoiceBoxData.setItems(datas);
        ChoiceBoxData.getItems().add(0, "Nenhum");
        ChoiceBoxData.getSelectionModel().select("Nenhum");
    }

    @FXML
    void cadastrarAgendamento(ActionEvent event) {
        try {
            Stage stage = (Stage) TableViewAgenda.getScene().getWindow();
            stage.close();
            abrirTelas("CadastrarAgendamento");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarAgendamento(ActionEvent event) {
        if (agendamentoSelecionado != null) {
            try {
                Stage currentStage = (Stage) TableViewAgenda.getScene().getWindow();
                currentStage.close();
                EditarAgendamentoController controller = (EditarAgendamentoController) abrirTelas("EditarAgendamento");
                controller.setAgendamento(agendamentoSelecionado);

                Stage stage = (Stage) TableViewAgenda.getScene().getWindow();
                stage.setOnHidden(e -> carregarAgenda());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um cliente para editar.");
            alert.showAndWait();
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

    public Object abrirTelas(String tela) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/" + tela + ".fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tela");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        return loader.getController();
    }

    @FXML
    void selecionarAgendamento(MouseEvent event) {
        if (event.getClickCount() == 1) {
            agendamentoSelecionado = TableViewAgenda.getSelectionModel().getSelectedItem();
        }
    }

    private void atualizarFaturamento() {
        double totalFaturamento = 0.0;

        for (Agendamento agendamento : TableViewAgenda.getItems()) {
            String valorServico = agendamento.getServicoValor();
            try {
                double valor = Double.parseDouble(valorServico);
                totalFaturamento += valor;
            } catch (NumberFormatException e) {
                System.err.println("Valor de serviço inválido: " + valorServico);
            }
        }

        TextFieldFaturamento.setText(String.format("R$: %.2f", totalFaturamento));
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
        atualizarVisibilidadeFaturamento();
    }

    private void atualizarVisibilidadeFaturamento() {
        labelFaturamento.setVisible(nivelAcesso == 1);
        TextFieldFaturamento.setVisible(nivelAcesso == 1);
        labelData.setVisible(nivelAcesso == 1);
        ChoiceBoxData.setVisible(nivelAcesso == 1);
        labelServico.setVisible(nivelAcesso == 1);
        ChoiceBoxServicos.setVisible(nivelAcesso == 1);
    }

    private void filtrarAgendaPorData(String dataSelecionada) {
        List<Agendamento> agenda;

        if ("Nenhum".equals(dataSelecionada)) {
            agenda = agendaDAO.listar();
        } else {
            agenda = agendaDAO.listarPorData(dataSelecionada);
            ChoiceBoxServicos.getSelectionModel().select("Nenhum");
        }

        agendamento.setAll(agenda);
        TableViewAgenda.setItems(agendamento);
    }

    private void filtrarAgendaPorServico(String servicoSelecionado) {
        List<Agendamento> agenda;

        if ("Nenhum".equals(servicoSelecionado)) {
            agenda = agendaDAO.listar();
        } else {
            agenda = agendaDAO.listarPorServicos(servicoSelecionado);
            ChoiceBoxData.getSelectionModel().select("Nenhum");
        }

        agendamento.setAll(agenda);
        TableViewAgenda.setItems(agendamento);
    }

    private ObservableList<Agendamento> agendamento = FXCollections.observableArrayList();
    private AgendaDAO agendaDAO = new AgendaDAO();
}