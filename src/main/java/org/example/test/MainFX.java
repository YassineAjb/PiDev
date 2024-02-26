package org.example.test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainFX extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {


        try{
            //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterarticle.fxml"));
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutermatch.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
//            primaryStage.setTitle("Ajouter Article ");
            primaryStage.setTitle("Ajouter Match");
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch (IOException e){
              throw new RuntimeException(e);
        }


    }
    public static void main(String[] args) {
        launch(args);
    }
}
