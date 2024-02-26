package org.example.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.models.TerrainCell;
import org.example.services.ServiceTerrain; // Assuming your service class is named ServiceTerrain

import java.io.IOException;
import java.sql.SQLException;
import org.example.models.Terrain;


import java.util.List;

public class Reservation {
//Affichage liste de terrains dediée pour ADMIN Apres il y'a crud Terrains  :

    @FXML
    private ListView<Terrain> affichage;

    @FXML
    private Button btnAjouter ;

    @FXML
    private Button btnModifier ;

    @FXML
    public void initialize() {
        ServiceTerrain serviceProduit = new ServiceTerrain();
        try {
            List<Terrain> produits = serviceProduit.afficher();
            ObservableList<Terrain> observableArrayList = FXCollections.observableArrayList(produits);

            affichage.setCellFactory(new Callback<ListView<Terrain>, ListCell<Terrain>>() {
                @Override
                public ListCell<Terrain> call(ListView<Terrain> listView) {
                    return new TerrainCell();
                }
            });

            affichage.setItems(observableArrayList);
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }

    }

    @FXML
    void supprimerTerrain(ActionEvent event) throws SQLException {
        ServiceTerrain serviceTerrain = new ServiceTerrain();


        Terrain selectedTerrain = affichage.getSelectionModel().getSelectedItem();
        System.out.println(selectedTerrain);
        if (selectedTerrain != null) {
            int idTerrain = selectedTerrain.getId(); // Supposons que getId() renvoie l'ID du produit
            System.out.println("ID du produit sélectionné : " + idTerrain);
            serviceTerrain.supprimer(idTerrain);
            this.initialize();
        } else {
            // Aucun produit sélectionné, gestion de l'erreur ou notification à l'utilisateur
            System.out.println("Aucun produit sélectionné.");
        }


    }



    @FXML
    private void handleAjouterButtonAction(ActionEvent event)
    {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterTerrain.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage from the ActionEvent
            Stage stage = (Stage) btnAjouter.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    void modifierTerrain(ActionEvent event) {
        Terrain selectedTerrain = affichage.getSelectionModel().getSelectedItem();
        if (selectedTerrain != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierTerrain.fxml"));
                Parent root = loader.load();
                ModifierTerrain controller = loader.getController();
                controller.initData(selectedTerrain); // Passer le produit sélectionné au contrôleur ModifierProduit
                Stage stage = (Stage) btnModifier.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Gérer le cas où aucun produit n'est sélectionné
        }}





    }


















