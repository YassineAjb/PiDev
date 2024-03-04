package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.example.models.Joueur;
import org.example.services.ServiceJoueur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageJoueur implements Initializable {

    private final ServiceJoueur serviceJoueur = new ServiceJoueur();
    @FXML
    private ScrollPane JoueurScroll;
    @FXML
    private GridPane gridJoueurs;
    private List<Joueur> joueurs = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;
        try {
            joueurs.addAll(serviceJoueur.afficher());

            for (int i = 0; i < joueurs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/JoueurRow.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();
                anchorPane.setPrefHeight(1160);

                JoueurRowController joueurRowController = fxmlLoader.getController();
                joueurRowController.setData(joueurs.get(i));
                gridJoueurs.addRow(row++,anchorPane);

                gridJoueurs.setMinWidth(1140);
                gridJoueurs.setPrefWidth(1140);
                gridJoueurs.setMaxWidth(1140);

                /*gridJoueurs.setMinHeight(300);
                gridJoueurs.setPrefHeight(300);
                gridJoueurs.setMaxHeight(300);*/

                gridJoueurs.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setChosenJoueur(Joueur joueur){

    }

}

