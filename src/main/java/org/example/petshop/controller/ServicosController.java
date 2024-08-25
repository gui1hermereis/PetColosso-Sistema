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
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.petshop.model.Cliente;
import org.example.petshop.model.Servicos;
import org.example.petshop.modelDAO.ServicosDAO;

public class ServicosController implements Initializable{

    @FXML
    private Button BtnCadastrar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<Servicos> TableViewServicos;

    @FXML
    private TableColumn<Servicos, Integer> TableColumnId;

    @FXML
    private TableColumn<Servicos, String> TableColumnServico;

    @FXML
    private TableColumn<Servicos, String> TableColumnValor;

    private Servicos servicoSelecionado;

    @Override
    public void initialize(URL url,ResourceBundle rb) {
        TableColumnId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        TableColumnServico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        TableColumnValor.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getValor())));

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

        carregarServicos();

        TableViewServicos.setOnMouseClicked(this::selecionarServico);
    }

    private void carregarServicos() {
        List<Servicos> servicos = ServicosDAO.listar();
        servico.setAll(servicos);
        TableViewServicos.setItems(servico);
    }

    @FXML
    void cadastrarServico(ActionEvent event) {
        try {
            Stage currentStage = (Stage) TableViewServicos.getScene().getWindow();
            currentStage.close();
            abrirTelas("CadastrarServico");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarServico(ActionEvent event) {
        if (servicoSelecionado != null) {
            try {
                Stage currentStage = (Stage) TableViewServicos.getScene().getWindow();
                currentStage.close();
                EditarServicoController controller = (EditarServicoController) abrirTelas("EditarServico");
                controller.setServico(servicoSelecionado);

                Stage stage = (Stage) TableViewServicos.getScene().getWindow();
                stage.setOnHidden(e -> carregarServicos());

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
    void excluirServico(ActionEvent event) {
        if (servicoSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir esse serviço?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                new ServicosDAO().excluir(servicoSelecionado);
                servico.remove(servicoSelecionado);
                carregarServicos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um serviço para excluir.");
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
    void selecionarServico(MouseEvent event) {
        if (event.getClickCount() == 1) {
            servicoSelecionado = TableViewServicos.getSelectionModel().getSelectedItem();
        }
    }

    private ObservableList<Servicos> servico = FXCollections.observableArrayList();
}
