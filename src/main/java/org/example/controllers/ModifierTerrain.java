package org.example.controllers;

import com.dlsc.gemsfx.TimePicker;
import com.dlsc.gemsfx.daterange.DateRangePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.models.Terrain;
import org.example.services.ServiceTerrain;

import java.sql.SQLException;
import java.time.LocalTime;

public class ModifierTerrain {

    @FXML
    private VBox Emplacement;

    @FXML
    private TextField GeoX;

    @FXML
    private TextField GeoY;

    @FXML
    private Button Verifier;

    @FXML
    private TextField adresse;

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
    private Button btncalendrier;

    @FXML
    private DateRangePicker datedispo;

    @FXML
    private TextField description;

    @FXML
    private TimePicker fermeture;

    @FXML
    private TextField nom_Terrain;

    @FXML
    private TimePicker ouverture;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;
    private final ServiceTerrain sr = new ServiceTerrain();

    private int idTerrain;
    private Terrain terrain;

    @FXML
    void modifierTerrain(ActionEvent event) {
        try {
            sr.modifier(new Terrain(idTerrain,nom_Terrain.getText(),adresse.getText(),description.getText(),Double.parseDouble(GeoX.getText()),Double.parseDouble(GeoY.getText()),String.valueOf(ouverture.getTime()),String.valueOf(fermeture.getTime()),datedispo.getValue().toString()));
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }



    }

    @FXML
    void onVerifyButtonClicked(ActionEvent event) {

    }
    //    public Terrain(int id, String nomTerrain, String adresse, String description, double geoX, double geoY, String ouverture, String fermeture, String datedispo)
    public void initData(Terrain selectedTerrain) {
        if (selectedTerrain != null) {
            this.terrain = selectedTerrain;
            idTerrain = terrain.getId();
            nom_Terrain.setText(terrain.getNomTerrain());
            adresse.setText(String.valueOf(terrain.getAdresse()));
            ouverture.setTime(LocalTime.parse(String.valueOf(terrain.getOuverture())));
            fermeture.setTime(LocalTime.parse(String.valueOf(terrain.getFermeture())));

            String tooltipText = String.valueOf(terrain.getDatedispo());
            Tooltip tooltip = new Tooltip(tooltipText);
            datedispo.setTooltip(tooltip);

            description.setText(String.valueOf(terrain.getDescription()));
            GeoX.setText(String.valueOf(terrain.getGeoX()));
            GeoY.setText(String.valueOf(terrain.getGeoY()));
        } else {
            System.out.println("Selected terrain is null.");
        }
    }

}
