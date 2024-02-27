package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Election/AfficherElection.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Gérer Evénement Eléctorale");
        primaryStage.setScene(scene);
        primaryStage.show();}catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
        launch(args);}
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
