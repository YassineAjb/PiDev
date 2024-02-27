package controllers.Admin.Candidat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Candidat;
import services.CandidatService;

import java.sql.SQLException;
import java.util.List;

public class AfficherCandidatController {

    @FXML
    private TableView<Candidat> tableViewC;

    @FXML
    private TableColumn<?, ?> prenomColC;

    @FXML
    private TableColumn<?, ?> ageColC;

    @FXML
    private TableColumn<?, ?> nomColC;

    private final CandidatService ps = new CandidatService();


    @FXML
    void initialize() {
        try {
            List<Candidat> elections = ps.recuperer();
            ObservableList<Candidat> observableList = FXCollections.observableList(elections);
            tableViewC.setItems(observableList);

            nomColC.setCellValueFactory(new PropertyValueFactory<>("nomC"));
            prenomColC.setCellValueFactory(new PropertyValueFactory<>("prenomC"));
            ageColC.setCellValueFactory(new PropertyValueFactory<>("age"));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
