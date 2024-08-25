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
import org.example.petshop.model.Usuarios;
import org.example.petshop.modelDAO.UsuariosDAO;

public class UsuarioController implements Initializable {

    @FXML
    private TableView<Usuarios> TableViewUsuarios;

    @FXML
    private TableColumn<Usuarios, Integer> TableColumnId;

    @FXML
    private TableColumn<Usuarios, String> TableColumnUsuario;

    @FXML
    private TableColumn<Usuarios, String> TableColumnSenha;

    @FXML
    private TableColumn<Usuarios, String> TableColumnNivel;

    @FXML
    private Button BtnCadastrar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnExcluir;

    private Usuarios usuarioSelecionado;

    private int nivelAcesso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumnId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        TableColumnUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario()));
        TableColumnSenha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSenha()));
        TableColumnNivel.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNivelAcesso()).asObject().asString());

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

        carregarUsuarios();
        TableViewUsuarios.setOnMouseClicked(this::selecionarUsuario);
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
        BtnCadastrar.setVisible(nivelAcesso == 1);
        BtnEditar.setVisible(nivelAcesso == 1);
        BtnExcluir.setVisible(nivelAcesso == 1);
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        try {
            Stage currentStage = (Stage) TableViewUsuarios.getScene().getWindow();
            currentStage.close();
            abrirTelas("CadastrarUsuarios");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarUsuario(ActionEvent event) {
        if (usuarioSelecionado != null) {
            try {
                Stage currentStage = (Stage) TableViewUsuarios.getScene().getWindow();
                currentStage.close();
                EditarUsuarioController controller = (EditarUsuarioController) abrirTelas("EditarUsuarios");
                controller.setUsuarios(usuarioSelecionado);

                Stage stage = (Stage) TableViewUsuarios.getScene().getWindow();
                stage.setOnHidden(e -> carregarUsuarios());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um usuario para editar.");
            alert.showAndWait();
        }
    }

    @FXML
    void excluirUsuario(ActionEvent event) {
        if (usuarioSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText("Tem certeza que deseja excluir esse usuario?");
            alert.setContentText("Esta ação não poderá ser desfeita.");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeConfirmar) {
                new UsuariosDAO().excluir(usuarioSelecionado);
                usuarios.remove(usuarioSelecionado);
                carregarUsuarios();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um usuario para excluir.");
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

    private ObservableList<Usuarios> usuarios = FXCollections.observableArrayList();
    private UsuariosDAO usuariosDAO = new UsuariosDAO();

    @FXML
    void selecionarUsuario(MouseEvent event) {
        if (event.getClickCount() == 1) {
            usuarioSelecionado = TableViewUsuarios.getSelectionModel().getSelectedItem();
        }
    }

    private void carregarUsuarios() {
        List<Usuarios> usuario = UsuariosDAO.listar();
        usuarios.setAll(usuario);
        TableViewUsuarios.setItems(usuarios);
    }
}
