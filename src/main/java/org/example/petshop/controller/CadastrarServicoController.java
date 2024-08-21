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

public class CadastrarServicoController implements Initializable{

    @FXML
    private Button BtnSalvar;


    @FXML
    private TextField TextFieldServico;

    @FXML
    private TextField TextFieldValor;

    @FXML
    void salvarServico(ActionEvent event) {
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
