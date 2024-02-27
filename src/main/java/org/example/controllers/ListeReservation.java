package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.models.Reservation;
import org.example.models.ReservationCell;
import org.example.models.Terrain;
import org.example.models.TerrainCell;
import org.example.services.ServiceReservation;
import org.example.services.ServiceTerrain;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListeReservation {

    @FXML
    private ListView<Reservation> affichage;

    private Button btnReserver;


    @FXML
    public void initialize() {
        ServiceReservation serviceProduit = new ServiceReservation();
        try {
            List<org.example.models.Reservation> produits = serviceProduit.afficher();
            ObservableList<org.example.models.Reservation> observableArrayList = FXCollections.observableArrayList(produits);

            affichage.setCellFactory(new Callback<ListView<org.example.models.Reservation>, ListCell<org.example.models.Reservation>>() {
                @Override
                public ListCell<org.example.models.Reservation> call(ListView<Reservation> listView) {
                    return new ReservationCell();
                }
            });

            affichage.setItems(observableArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void supprimerReservation(ActionEvent event) throws SQLException {
        ServiceReservation serviceReservation = new ServiceReservation();


        Reservation selectedReservation = affichage.getSelectionModel().getSelectedItem();
        System.out.println(selectedReservation);
        if (selectedReservation != null) {
            int idReservation = selectedReservation.getReservationID();
            System.out.println("ID du produit sélectionné : " + idReservation);
            serviceReservation.supprimer(idReservation);
            this.initialize();
        } else {
            System.out.println("Aucune Reservation sélectionné.");
        }


    }

    @FXML
    void modifierReservation(ActionEvent event) {
        Reservation selectedReservation = affichage.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierReservation.fxml"));
                Parent root = loader.load();
                ModifierReservation controller = loader.getController();
                controller.initData(selectedReservation); // Passer le Reservation sélectionné au contrôleur ModifierReservation
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }}


    @FXML
    private void handleReserverButtonAction(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Terrain.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}