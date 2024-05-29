package org.example.petshop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import org.example.petshop.model.Servicos;

public class CadastrarServicosController implements Initializable{

    @FXML
    private Button BtnSalvar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TextField TextFieldServico;

    @FXML
    private TextField TextFieldValor;

    @FXML
    private Button BtnAlterar;

    @FXML
    void alterarServico(ActionEvent event) {
    }
    @FXML
    void salvarServico(ActionEvent event) {
    }

    @FXML
    void excluirServico(ActionEvent event) {
    }

    private ObservableList<Servicos> servicos = FXCollections.observableArrayList();

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

        Image excluir = new Image(getClass().getResource("/org/example/petshop/icons/excluir.png").toExternalForm());
        ImageView excl = new ImageView(excluir);
        BtnExcluir.setGraphic(excl);

        Image alt = new Image(getClass().getResource("/org/example/petshop/icons/setas-flechas.png").toExternalForm());
        ImageView alterar = new ImageView(alt);
        alterar.setFitWidth(16);
        alterar.setFitHeight(16);
        BtnAlterar.setGraphic(alterar);
    }
}
