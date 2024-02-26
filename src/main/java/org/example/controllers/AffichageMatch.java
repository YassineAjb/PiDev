package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.models.Article;
import org.example.models.Match;
import org.example.services.MatchService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AffichageMatch {

    @FXML
    private Button btnAcceuil;


    @FXML
    private Button btnajouter;

    @FXML
    private Button btncalafficher;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private ScrollPane fetchScrollPane;

    @FXML
    private ListView<Match> listMatches;


    private int id ;
    @FXML
    public void initialize() {

        try {
            MatchService service = new MatchService();
            List<Match> matches = service.getall();
            ObservableList<Match> matchList = FXCollections.observableArrayList();
            matchList.addAll(matches);
            listMatches.setItems(matchList);

            listMatches.setCellFactory(param -> new ListCell<>() {

                @Override
                protected void updateItem(Match match, boolean empty) {
                    super.updateItem(match, empty);

                    if (empty || match == null) {
                        setText(null);
                    } else {
                        GridPane gridPane = new GridPane();
                        gridPane.setHgap(10);
                        // Create labels
                        Label adversaryLabel = createLabel("Adversaire:");
                        Label adversaryValueLabel = createLabel(match.getAdversaireMatch());
                        Label dateLabel = createLabel("Date:");
                        Label dateValueLabel = createLabel(match.getDateMatch().toString());
                        Label scoreLabel = createLabel("Score:");
                        Label scoreValueLabel = createLabel(String.valueOf(match.getScoreMatch()));
                        Label scoreValueLabelid = createLabel(String.valueOf(match.getIdMatch()));
                        id = match.getIdMatch();
                        // Create delete button
                        Button deleteButton = new Button("Delete");

                        deleteButton.setOnAction(event -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer cet match?");

                            ButtonType confirmButton = new ButtonType("Confirmer");
                            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                            alert.getButtonTypes().setAll(confirmButton, cancelButton);

                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.isPresent() && result.get() == confirmButton) {
                                try {
                                    service.remove(match.getIdMatch()); // Assuming getId() is the method to get the match id
                                    fetchDataAndPopulateListView();

                                } catch (SQLException e) {
                                    System.err.println("Erreur lors de la suppression : " + e.getMessage());
                                }
                            }
                        });

                        gridPane.add(adversaryLabel, 0, 0);
                        gridPane.add(adversaryValueLabel, 1, 0);
                        gridPane.add(dateLabel, 0, 1);
                        gridPane.add(dateValueLabel, 1, 1);
                        gridPane.add(scoreLabel, 0, 2);
                        gridPane.add(scoreValueLabel, 1, 2);
                        gridPane.add(deleteButton, 2, 0, 1, 3);
                        setGraphic(gridPane);
                    }
                }
                private Label createLabel(String text) {
                    Label label = new Label(text);
                    label.setStyle("-fx-font-size: 14px;");
                    return label;
                }
            });

            listMatches.getItems().addAll(matches);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des matches : " + e.getMessage());
        }
    }
    @FXML
    private void fetchDataAndPopulateListView() {
        try {
            MatchService service = new MatchService();
            List<Match> matches = service.getall();
            listMatches.getItems().setAll(matches);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des matches : " + e.getMessage());
        }
    }
    @FXML
    void ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ajoutermatch.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


//
//    public void navigateToMatch(ActionEvent event) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/modifiermatch.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//        }catch(IOException e){
//            e.getMessage();
//        }
//    }

    @FXML
    void modifierMatch(ActionEvent event) {
        Match selectedmatch = listMatches.getSelectionModel().getSelectedItem();
        if(selectedmatch != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifiermatch.fxml"));
                Parent root = loader.load();
                ModifierMatch controller = loader.getController();
                controller.initData(selectedmatch);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = stage.getScene();
                scene.setRoot(root);

            }catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

    }

    public int id(){
        return id ;
    }

}

