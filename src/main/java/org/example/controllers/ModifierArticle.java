package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.models.Article;
import org.example.services.ArticleService;

import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ModifierArticle {
    ArticleService artic = new ArticleService() ;

    @FXML
    private TextField ida;

    @FXML
    private TextField idj;

    @FXML
    private TextField image;
    @FXML
    private TextField idArticle;

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
    private Button btnafficher;

    @FXML
    private Button btncalendrier;

    @FXML
    private Button btnmodifier;

    @FXML
    private TextArea description;


    @FXML
    private Pane img;

    @FXML
    private TextField titre;

    @FXML
    void afficherarticle(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/afficherarticles.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    public void initData(Article article){
        ida.setText(String.valueOf(article.getIdArticle()));
        idj.setText(String.valueOf(article.getIdJournaliste()));
        description.setText(article.getContenuArticle());
        image.setText(article.getImageArticle());
        titre.setText(article.getTitreArticle());
    }

    @FXML
    void modifierArticle(ActionEvent event) {
        try {
            String idText = ida.getText();
            String tit = titre.getText();
            String desc = description.getText();
            String img = image.getText();

            // Vérifier si les champs sont vides
            if (idText.isEmpty() || tit.isEmpty() || desc.isEmpty() || img.isEmpty()) {
                // Afficher un message d'erreur
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;  // Sortir de la fonction si les champs sont vides
            }
            int id = Integer.parseInt(idText);
            Article modifiedArticle = new Article(id, tit, desc, img, 2);
            artic.modify(modifiedArticle);
            Parent root = FXMLLoader.load(getClass().getResource("/afficherarticles.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en entier échoue
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un numéro valide pour l'ID.");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
