package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.models.Article;
import org.example.services.ArticleService;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class ArticleControllors  implements Initializable{

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnimporter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TextArea description;
    @FXML
    private TextField idJourNaliste;

    @FXML
    private Pane img;

    @FXML
    private ScrollPane fetchScrollPane;

    @FXML
    private TextField titre;


    ArticleService artic = new ArticleService() ;

    public void ajouterArticle() {
        try {
            System.out.println("test");
            String desc = description.getText();
            String tit = titre.getText();
            int idjounaliste = Integer.parseInt(String.valueOf(Integer.parseInt(idJourNaliste.getText())));
            Article article = new Article(tit , desc ,"Image",idjounaliste);
            artic.add(article);
        }catch (SQLException e){
            e.getErrorCode();
        }
        try {
            fetchDataAndPopulateScrollPane();
        } catch (SQLException e) {
            e.printStackTrace();
//             Handle exception as needed
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fetchDataAndPopulateScrollPane();
        } catch (SQLException e) {
            e.printStackTrace();
//             Handle exception as needed
        }
    }

    private void fetchDataAndPopulateScrollPane() throws SQLException {
        ArticleService service = new ArticleService();
        List<Article> articles = service.getall();

        VBox contentVBox = new VBox();

        for (Article article : articles) {
            Label label = new Label(article.getTitreArticle() + " " + article.getContenuArticle());

            Button deleteButton = new Button("Deleteee");
            deleteButton.setOnAction(event -> {
                // Afficher une alerte de confirmation avant de supprimer
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");

                // Option pour confirmer ou annuler la suppression
                ButtonType confirmButton = new ButtonType("Confirmer");
                ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(confirmButton, cancelButton);

                // Attendre la réponse de l'utilisateur
                Optional<ButtonType> result = alert.showAndWait();

                // Si l'utilisateur confirme, supprimer l'élément
                if (result.isPresent() && result.get() == confirmButton) {
                    try {
                        service.remove(article.getIdArticle());
                        // Rafraîchir la liste ou l'interface utilisateur après la suppression
                        fetchDataAndPopulateScrollPane();
                    } catch (SQLException e) {
                        // Gérer les erreurs de suppression
                        afficherErreur("Erreur lors de la suppression : " + e.getMessage());
                    }
                }
            });

            HBox buttonBox = new HBox(deleteButton);
            VBox articleBox = new VBox(label, buttonBox);
            contentVBox.getChildren().add(articleBox);
        }

        fetchScrollPane.setContent(contentVBox);
    }

    // Méthode pour afficher une alerte d'erreur
    private void afficherErreur(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void modifierArticle(ActionEvent event) {
        try {
            System.out.println("test");
            String desc = description.getText();
            String tit = titre.getText();
            int idjounaliste = Integer.parseInt(String.valueOf(Integer.parseInt(idJourNaliste.getText())));
            Article article = new Article(tit , desc ,"Image",idjounaliste);
            artic.modify(article);
            try {
                fetchDataAndPopulateScrollPane();
            } catch (SQLException e) {
                e.printStackTrace();
//             Handle exception as needed
            }
        }catch (SQLException e){
            e.getErrorCode();
        }


    }



}


