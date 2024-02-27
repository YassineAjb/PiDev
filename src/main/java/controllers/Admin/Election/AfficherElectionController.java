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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Election;
import services.ElectionService;
import utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherElectionController implements Initializable {
    private final ElectionService ps = new ElectionService();
    @FXML
    private TableColumn<Election, LocalDate> dateCol;
    @FXML
    private TableColumn<Election, String> nomCol;
    @FXML
    private TableColumn<Election, String> descriptionCol;
    @FXML
    private TableView<Election> tableView;
    static int id;
    @FXML
    private Button btnElection;
    @FXML
    private VBox electionslayout;

    @FXML
    void naviqueVersAjoutE(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Election/AjouterElection.fxml"));
            electionslayout.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

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
                electionslayout.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}




















    //@FXML
    /*void initialize() {
        try {
            List<Election> elections = ps.recuperer();
            ObservableList<Election> observableList = FXCollections.observableList(elections);
            tableView.setItems(observableList);

            nomCol.setCellValueFactory(new PropertyValueFactory<>("nomE"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dateE"));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }*/



