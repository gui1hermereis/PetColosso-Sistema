package org.example.petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;


public class LoginController {

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private TextField TextFieldSenha;

    @FXML
    private Button BtnEntrar;

    @FXML
    private void Entrar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/JanelaPrincipal.fxml"));
        try {
            Stage stage = (Stage) BtnEntrar.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {

        TextFieldUsuario.setEditable(true);
        TextFieldUsuario.setText("");
        TextFieldSenha.setEditable(true);
        TextFieldSenha.setText("");


        Image entrar = new Image(getClass().getResource("/org/example/petshop/icons/salvar.png").toExternalForm());
        ImageView Entrar = new ImageView(entrar);
        BtnEntrar.setGraphic(Entrar);





    }
}
