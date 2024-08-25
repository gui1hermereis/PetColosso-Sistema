package org.example.petshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.petshop.controller.LoginController;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/org/example/petshop/icons/icone.png")));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petshop/view/Login.fxml"));
            AnchorPane root = loader.load();

            LoginController controller = loader.getController();

            primaryStage.setTitle("Agenda");
            primaryStage.setScene(new Scene(root));

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}