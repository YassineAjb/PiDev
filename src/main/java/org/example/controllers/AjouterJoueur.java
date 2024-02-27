//package org.example.controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TextField;
//import org.example.models.Joueur;
//import org.example.services.ServiceJoueur;
//import org.example.controllers.AfficherJoueur;
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class AjouterJoueur {
//
//    @FXML
//    private TextField AgeTF;
//
//    @FXML
//    private TextField HauteurTF;
//
//    @FXML
//    private TextField NomTF;
//
//    @FXML
//    private TextField PiedfortTF;
//
//    @FXML
//    private TextField PoidsTF;
//
//    @FXML
//    private TextField PositionTF;
//
//    @FXML
//    private TextField PrenomTF;
//
//
//    private final ServiceJoueur sp = new ServiceJoueur();
////
//    public void ajouterJoueur(ActionEvent actionEvent) {
//        try {
//            sp.ajouter(new Joueur(PositionTF.getText(),Integer.parseInt(HauteurTF.getText()),Integer.parseInt(PoidsTF.getText()),Integer.parseInt(PiedfortTF.getText()),NomTF.getText(),PrenomTF.getText(),Integer.parseInt(AgeTF.getText())));
//
//        } catch (SQLException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setContentText(e.getMessage());
//            alert.showAndWait();
//        }
//    }
//
//    public void naviguezVersAffichage(ActionEvent actionEvent) {
//        /*try {
//            Parent root = FXMLLoader.load(getClass().getResource("/AfficherJoueur.fxml"));
//            HauteurTF.getScene().setRoot(root);
//
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }*/
//    }
//}
