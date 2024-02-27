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

public class AjouterCandidatController {

    @FXML
    private TextField ageTFAC;

    @FXML
    private TextField nomTFAC;
    @FXML
    private TextField prenomTFAC;
    private final CandidatService ps = new CandidatService();

    @FXML
    void ajouterCandidat(ActionEvent event) {
        {
            try {
                ps.ajouter(new Candidat(nomTFAC.getText(),
                        prenomTFAC.getText(),
                        Integer.parseInt(ageTFAC.getText())));
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }



    @FXML
    void naviguezACVersAffichage(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCandidat.fxml"));
            nomTFAC.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}


