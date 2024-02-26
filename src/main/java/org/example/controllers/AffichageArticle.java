package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.models.Article;
import org.example.services.ArticleService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AffichageArticle{

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
    private Button btnStaff;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btncalendrier;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private ScrollPane fetchScrollPane;

    @FXML
    private Pane img;

    @FXML
    private ListView<Article> listarticles;


    @FXML
    public void initialize() throws SQLException {
        ArticleService service = new ArticleService();
        List<Article> articles = service.getall();

        listarticles.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    // Create labels
                    Label titleLabel = createLabel("Titre:");
                    Label titleValueLabel = createLabel(item.getTitreArticle());
                    Label contentLabel = createLabel("Contenu:");
                    Label contentValueLabel = createLabel(item.getContenuArticle());
                    Label imageLabel = createLabel("Image:");
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(50); // Set the height as needed
                    imageView.setFitWidth(50);  // Set the width as needed

                    // Create an Image from the file path
                    Image image = new Image(new File(item.getImageArticle()).toURI().toString());

                    // Set the Image to the ImageView
                    imageView.setImage(image);

                    // Add the ImageView to the GridPane
                    gridPane.add(imageView, 3, 0, 1, 3);
                    // Create delete button
                    Button deleteButton = new Button("Delete");
                    deleteButton.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation de suppression");
                        alert.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");

                        ButtonType confirmButton = new ButtonType("Confirmer");
                        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(confirmButton, cancelButton);

                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == confirmButton) {
                            try {
                                service.remove(item.getIdArticle());
                                fetchDataAndPopulateListView();
                            } catch (SQLException e) {
                                System.err.println("Erreur lors de la suppression : " + e.getMessage());
                            }
                        }
                    });
                    // Add items to the GridPane
                    gridPane.add(titleLabel, 0, 0);
                    gridPane.add(titleValueLabel, 1, 0);
                    gridPane.add(contentLabel, 0, 1);
                    gridPane.add(contentValueLabel, 1, 1);
                    gridPane.add(imageLabel, 0, 2);
//                    gridPane.add(imageValueLabel, 1, 2);
                    gridPane.add(deleteButton, 2, 0, 1, 3);
                    setGraphic(gridPane);
                }
            }

            private Label createLabel(String text) {
                Label label = new Label(text);
                label.setStyle("-fx-font-size: 14px;"); // Set font size as needed
                return label;
            }
        });

        listarticles.getItems().addAll(articles);
    }



    private void fetchDataAndPopulateListView() {
        try {
            ArticleService service = new ArticleService();
            List<Article> articles = service.getall();
//            listarticles.getItems().setAll(articles);
        } catch (SQLException e) {
            // Handle the exception appropriately
            System.err.println("Erreur lors de la récupération des articles : " + e.getMessage());
        }
    }

    @FXML
    void ajouterArticle(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/ajouterarticle.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void modifierArticle(ActionEvent event) {
        Article selectedarticle = listarticles.getSelectionModel().getSelectedItem();
        if(selectedarticle != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierarticle.fxml"));
                Parent root = loader.load();
                ModifierArticle controller = loader.getController();
                controller.initData(selectedarticle);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = stage.getScene();
                scene.setRoot(root);

            }catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

    }







}
