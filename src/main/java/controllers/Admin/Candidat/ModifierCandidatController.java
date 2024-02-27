package controllers.Admin.Candidat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Candidat;
import services.CandidatService;

import java.io.IOException;
import java.sql.SQLException;


public class ModifierCandidatController {

        @FXML
        private TextField idTFCM;

        @FXML
        private TextField prenomTFCM;

        @FXML
        private TextField nomTFCM;
        @FXML
        private TextField ageTFCM;

        @FXML
        private final CandidatService ps = new CandidatService();
        @FXML
        public void modifierCandidat(ActionEvent actionEvent) {
            try {
                int id = Integer.parseInt(idTFCM.getText());
                String nom = nomTFCM.getText();
                String prenom = prenomTFCM.getText();
                int age = Integer.parseInt(ageTFCM.getText());  // Assuming there's a TextField for age

                // Vérifiez si tous les champs nécessaires sont remplis
                if (nom.isEmpty() || prenom.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Veuillez remplir tous les champs.");
                    alert.showAndWait();
                    return; // Arrêtez l'exécution si des champs sont vides
                }

                // Créez un objet Candidat avec les nouvelles données
                Candidat candidat = new Candidat(id, nom, prenom,age);

                // Utilisez le service CandidatService pour modifier le candidat
                ps.modifier(candidat);

                // Affichez une alerte de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Le candidat a été modifié avec succès.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                // Gestion de l'exception si la conversion de l'ID échoue
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("L'ID doit être un nombre entier.");
                alert.showAndWait();
            } catch (SQLException e) {
                // Gestion des erreurs SQL
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }



        @FXML
        void naviguezMCVersAffichage(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/AfficherCandidat.fxml"));
                nomTFCM.getScene().setRoot(root);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }


