package org.example.controllers;
import org.example.controllers.AffichageMatch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.Match;
import org.example.services.MatchService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class ModifierMatch {

    MatchService matchService = new MatchService();

    @FXML
    private TextField adversaire;

    @FXML
    private DatePicker date;

    @FXML
    private TextField score;

    @FXML
    private Button btnAcceuil;

    @FXML
    private Button btnBoutique;

    @FXML
    private Button btnJoueurs;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnSettings1;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnAfficher;

    @FXML
    private TextField matchid;

    @FXML
    private Button btnModifier;

    @FXML
    void afficherMatch(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/affichermatch.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }




    public void initData(Match match) {
        adversaire.setText(match.getAdversaireMatch());
        LocalDate matchDate = match.getDateMatch().toLocalDate();
        date.setValue(matchDate);
        score.setText(String.valueOf(match.getScoreMatch()));
        matchid.setText(String.valueOf(match.getIdMatch()));
    }
    @FXML
    public void modifier(ActionEvent event) {
        try {
            String adv = adversaire.getText();
            LocalDate dat = date.getValue();
            int scr = Integer.parseInt(score.getText());
            int idMatch = Integer.parseInt(matchid.getText());

            // Vérifier si les champs sont vides
            if (adv.isEmpty() || dat == null) {
                // Afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;  // Sortir de la fonction si les champs sont vides
            }

            // Vérifier si la date est passée
            LocalDate currentDate = LocalDate.now();
            if (dat.isAfter(currentDate)) {
                // Afficher une alerte indiquant que la date est dans le futur
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText(null);
                alert.setContentText("Ce match est à jouer. Veuillez sélectionner une date passée.");
                alert.showAndWait();
                return;  // Sortir de la fonction si la date est dans le futur
            }

            Match modifiedMatch = new Match(idMatch, adv, Date.valueOf(dat), scr);
            matchService.modify(modifiedMatch);

            Parent root = FXMLLoader.load(getClass().getResource("/ajoutermatch.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en entier échoue
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un numéro valide pour le score.");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void ajouter(ActionEvent event) {
        MatchService x = new MatchService();
        try {
            System.out.println("test");
            String adv = adversaire.getText();
            LocalDate dat = date.getValue();

            // Always set the score to 0 for matches in the future
            int scr = dat.isAfter(LocalDate.now()) ? 0 : Integer.parseInt(score.getText());

            // Vérifier si la date est passée ou future
            LocalDate currentDate = LocalDate.now();

            if (dat.isAfter(currentDate)) {
                // Date future, hide the score field

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText(null);
                alert.setContentText("Ce match est à joué. La saisie du score est désactivée pour les matches futurs.");
                alert.showAndWait();
            } else if (dat.isBefore(currentDate)) {
                // Date passée, show a warning if the score is empty
                if (scr == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Avertissement");
                    alert.setHeaderText(null);
                    alert.setContentText("Ce match est passé. Veuillez entrer un score.");
                    alert.showAndWait();
                    return;  // Sortir de la fonction si la date est passée et le score est vide
                }
            }

            Match match = new Match(adv, Date.valueOf(dat), scr);
            x.add(match);

            Parent root = FXMLLoader.load(getClass().getResource("/affichermatch.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);

        } catch (SQLException e) {
            e.getErrorCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en entier échoue
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un numéro valide pour le score.");
            alert.showAndWait();
        }
    }
    }

