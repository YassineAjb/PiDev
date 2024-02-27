package controllers.Admin.Candidat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Election;
import services.ElectionService;

import java.sql.SQLException;
import java.util.List;

public class SupprimerCandidatController {

    @FXML
    private TableView<Election> tableViewS;

    @FXML
    private TextField idTFS;

    @FXML
    private TableColumn<?, ?> dateColS;
    @FXML
    private TextField nomTFSupp;
    @FXML
    private TableColumn<?, ?> descriptionColS;

    @FXML
    private TableColumn<?, ?> nomColS;
    private final ElectionService ps = new ElectionService();
    @FXML
    void supprimerElection(ActionEvent event) {
            try {
                String nom = nomTFSupp.getText();

                // Assuming supprimerParId is a void method
                ps.supprimer(nom);
                //refreshTableView();
                // Assuming the method doesn't throw an exception on failure
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("L'élection a été supprimée avec succès.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("L'ID doit être valide.");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();            }
        }

    @FXML
    void initialize() {
        try {
            List<Election> elections = ps.recuperer();
            ObservableList<Election> observableList = FXCollections.observableList(elections);
            tableViewS.setItems(observableList);

            nomColS.setCellValueFactory(new PropertyValueFactory<>("nomE"));
            descriptionColS.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateColS.setCellValueFactory(new PropertyValueFactory<>("dateE"));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
  /*  private void refreshTableView() {
        try {
            List<Election> elections = ps.recuperer();
            ObservableList<Election> observableList = FXCollections.observableList(elections);
            tableViewS.setItems(observableList);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }*/


    @FXML
    void NaviguerVersElectionHome(ActionEvent event) {

    }


}




