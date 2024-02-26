// Terrain.java
package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.models.Reservation;
import org.example.models.ReservationCell;
import org.example.services.ServiceReservation;
import org.example.services.ServiceTerrain;
import org.example.utils.MyDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Terrain {

    @FXML
    private ChoiceBox<String> Choixterrain;

    @FXML
    private ListView<String> DateReservation;

    @FXML
    private TextField Note;

    @FXML
    private Button addReservation;

    @FXML
    private VBox Emplacement;

    private final ServiceReservation serviceReservation = new ServiceReservation();

    private final String GET_TERRAINS_QUERY = "SELECT nom_terrain FROM terrain";

    private String[] lol = {"9-10:30", "10-11:30", "12-13:30", "14-15:30", "16 - 17:30", "17:30-19"};


    @FXML
    private void initialize() {

        populateTerrainChoiceBox();

        DateReservation.getItems().addAll(lol);
        }

        @FXML
        private void handleReserverButtonAction() throws SQLException {
            // This method will be called when the "Reserver" button is pressed
            ServiceTerrain serviceTerrain = new ServiceTerrain();
            int id = serviceTerrain.afficherTerrain(Choixterrain.getValue());

            // Get data from controls
            int choixTerrain = id;
            String dateReservation = DateReservation.getSelectionModel().getSelectedItem();
            String note = Note.getText();
            // You may need to handle Emplacement as well

            // Create a Reservation object with the obtained data
            Reservation reservation = new Reservation(id, dateReservation, note, null);  // Set null for Emplacement for now

            // Add the reservation to the database
            serviceReservation.addReservation(reservation,Choixterrain.getValue());

        }

    private void populateTerrainChoiceBox() {
        try {
            PreparedStatement preparedStatement = MyDataBase.getInstance().getConnection().prepareStatement(GET_TERRAINS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> terrainList = new ArrayList<>();

            while (resultSet.next()) {
                String nomTerrain = resultSet.getString("nom_terrain");
                terrainList.add(nomTerrain);
            }

            Choixterrain.getItems().addAll(terrainList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
