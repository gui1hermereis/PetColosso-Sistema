package org.example.petshop.controller;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import org.example.petshop.model.Cliente;
import org.example.petshop.modelDAO.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private TableView<Cliente> TableViewClientes;

    @FXML
    private TableColumn<Cliente, String> TableColumnNome;

    @FXML
    private TableColumn<Cliente, String> TableColumnCpf;

    @FXML
    private TableColumn<Cliente, String> TableColumnTelefone;

    @FXML
    private Button BtnCadastrar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnExcluir;

    private Cliente clienteSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        TableColumnCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCpf()));
        TableColumnTelefone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));

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

        carregarClientes();

        TableViewClientes.setOnMouseClicked(this::selecionarCliente);
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        cliente.setAll(clientes);
        TableViewClientes.setItems(cliente);
    }

    @FXML
    void cadastrarCliente(ActionEvent event) {
        try {
            Stage currentStage = (Stage) TableViewClientes.getScene().getWindow();
            currentStage.close();
            abrirTelas("CadastrarCliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarCliente(ActionEvent event) {
        if (clienteSelecionado != null) {
            try {
                Stage currentStage = (Stage) TableViewClientes.getScene().getWindow();
                currentStage.close();

                EditarClienteController controller = (EditarClienteController) abrirTelas("EditarCliente");
                controller.setCliente(clienteSelecionado);

                Stage stage = (Stage) TableViewClientes.getScene().getWindow();
                stage.setOnHidden(e -> carregarClientes());

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
    void excluirCliente(ActionEvent event) {
        if (clienteSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir esse cliente?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                new ClienteDAO().excluir(clienteSelecionado);
                cliente.remove(clienteSelecionado);
                carregarClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um cliente para excluir.");
            alert.showAndWait();
        }
    }

    @FXML
    void selecionarCliente(MouseEvent event) {
        if (event.getClickCount() == 1) {
            clienteSelecionado = TableViewClientes.getSelectionModel().getSelectedItem();
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

    private ObservableList<Cliente> cliente = FXCollections.observableArrayList();
    private ClienteDAO clienteDAO = new ClienteDAO();
}
