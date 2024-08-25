package org.example.petshop.controller;

import java.net.URL;
import java.util.ResourceBundle;
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
import org.example.petshop.modelDAO.ClienteDAO;
import org.example.petshop.utils.MultiMaskTextFormatter;

public class EditarClienteController implements Initializable {

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private TextField TextFieldCpf;

    @FXML
    private TextField TextFieldTelefone;

    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);

        TextFieldCpf.setTextFormatter(new MultiMaskTextFormatter(MultiMaskTextFormatter.MaskType.CPF));
        TextFieldTelefone.setTextFormatter(new MultiMaskTextFormatter(MultiMaskTextFormatter.MaskType.TELEFONE));
    }

    @FXML
    void editarCliente(ActionEvent event) {
        String nome = TextFieldNome.getText();
        String cpf = TextFieldCpf.getText();
        String telefone = TextFieldTelefone.getText();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);

        new ClienteDAO().editar(cliente);
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        TextFieldNome.setText(cliente.getNome());
        TextFieldCpf.setText(cliente.getCpf());
        TextFieldTelefone.setText(cliente.getTelefone());
    }
}
