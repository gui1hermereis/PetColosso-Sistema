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
import org.example.petshop.model.Servicos;
import org.example.petshop.modelDAO.ClienteDAO;
import org.example.petshop.modelDAO.ServicosDAO;

public class EditarServicoController implements Initializable{

    @FXML
    private Button BtnSalvar;

    @FXML
    private TextField TextFieldServico;

    @FXML
    private TextField TextFieldValor;

    private Servicos servicos;

    @FXML
    void editarServico(ActionEvent event) {
        String descricao = TextFieldServico.getText();
        float valor = Float.parseFloat(TextFieldValor.getText());
        servicos.setDescricao(descricao);
        servicos.setValor(valor);

        new ServicosDAO().editar(servicos);
        exibirMensagem("Sucesso", "Cliente editado com sucesso!");
    }

    public void setServico(Servicos servicos) {
        this.servicos = servicos;
        TextFieldServico.setText(servicos.getDescricao());
        TextFieldValor.setText(String.format("%.2f", servicos.getValor()));
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
    public void initialize(URL url,ResourceBundle rb) {

        TextFieldServico.setEditable(true);
        TextFieldServico.setText("");
        TextFieldValor.setEditable(true);
        TextFieldValor.setText("");

        Image salvar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Salvar = new ImageView(salvar);
        Salvar.setFitHeight(13);
        Salvar.setFitWidth(13);
        BtnSalvar.setGraphic(Salvar);
    }
}
