package org.example.Test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/listeReservation.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reservation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("SWIFTMINDER");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}