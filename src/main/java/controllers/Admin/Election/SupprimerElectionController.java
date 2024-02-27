package controllers.Admin.Election;

import controllers.ELectionItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Election;
import services.ElectionService;
import utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupprimerElectionController implements Initializable {

    @FXML
    private TableView<Election> tableViewS;

    @FXML
    private TextField nomTFSupp;

    @FXML
    private TableColumn<?, ?> dateColS;

    @FXML
    private TableColumn<?, ?> descriptionColS;
    @FXML
    private VBox electionslayoutSupp;
    @FXML
    private TableColumn<?, ?> nomColS;
    private final ElectionService ps = new ElectionService();

    @FXML
    void NaviguerVersElectionHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Election/AfficherElection.fxml"));
            nomTFSupp.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }





    @FXML
    void supprimerElection(ActionEvent event) {
            try {
                String nom = nomTFSupp.getText();

                // Assuming supprimerParId is a void method
                ps.supprimer(nom);
                //refreshTableView();
               // initialize();
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

   /* @FXML
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
    }*/


    public List<Election> getAllElections() {
        List<Election> elections = new ArrayList<>();

        try (Connection connection = MyDatabase.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM evenement")) {

            while (resultSet.next()) {
                Election election = new Election();
                election.setNomE(resultSet.getString("nomE"));
                election.setDateE(LocalDate.parse(resultSet.getString("dateE"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                election.setPosteE(resultSet.getString("posteE"));
                election.setPeriodeP(resultSet.getString("periodeP"));
                election.setImgEpath(resultSet.getString("imgEpath"));
                System.out.println(resultSet.getString("imgEpath"));
                elections.add(election);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return elections;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Election> elections = new ArrayList<>(getAllElections());

        for (Election election : elections) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Election/ElectionItem.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                ELectionItemController electionItemController = fxmlLoader.getController();
                electionItemController.setData(election);

                // Directly add HBox to the container
                electionslayoutSupp.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*private void refreshTableView() {
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


   /* @FXML
    void naviguezSVersAjout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterElection.fxml"));
            tableViewS.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void naviguezSVersModif(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierElection.fxml"));
            tableViewS.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }*/

}




