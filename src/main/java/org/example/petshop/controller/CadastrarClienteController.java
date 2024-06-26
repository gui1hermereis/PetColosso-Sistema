package org.example.petshop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.example.petshop.model.Cliente;
import org.example.petshop.modelDAO.CadastrarClienteDAO;

public class CadastrarClienteController implements Initializable {

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private TextField TextFieldCpf;

    @FXML
    private TextField TextFieldTelefone;

    @FXML
    void cadastrarCliente(ActionEvent event) {
        String nome = TextFieldNome.getText();
        String cpf = TextFieldCpf.getText();
        String telefone = TextFieldTelefone.getText();

        Cliente cliente = new Cliente(nome, cpf, telefone);
        new CadastrarClienteDAO().cadastrar(cliente);
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

    private ObservableList<Cliente> cliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFieldNome.setEditable(true);
        TextFieldNome.setText("");
        TextFieldCpf.setEditable(true);
        TextFieldCpf.setText("");
        TextFieldTelefone.setEditable(true);
        TextFieldTelefone.setText("");

        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);
    }
}
