package org.example.controllers;

import com.dlsc.gemsfx.TimePicker;
import com.dlsc.gemsfx.daterange.DateRangePicker;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import org.example.services.ServiceTerrain;
import org.example.models.Terrain ;
import java.sql.SQLException;

public class AjoutTerrain {

    @FXML
    private VBox Emplacement;

    @FXML
    private TextField GeoX;

    @FXML
    private TextField GeoY;

    private MapView mapView;

    @FXML
    private TextField nom_Terrain;

    @FXML
    private TextField adresse;

    @FXML
    private TextField description;

    @FXML
    private TimePicker ouverture;

    @FXML
    private TimePicker fermeture;

    @FXML
    private DateRangePicker datedispo;

    @FXML
    private DatePicker datePicker ;

    public void initialize() {
        mapView = createMapView();
        Emplacement.getChildren().add(mapView);
        VBox.setVgrow(mapView, Priority.ALWAYS);
        // Set initial values for GeoX and GeoY
        GeoX.setText("36.90367103784889");
        GeoY.setText("10.190358937780948");
        updateMapPoint(); // Update initially
    }

    @FXML
    private void addTerrain() {
        String nomTerrain = nom_Terrain.getText();
        String adresseValue = adresse.getText();
        String descriptionValue = description.getText();
        double GeoXValue = Double.parseDouble(GeoX.getText());
        double GeoYValue = Double.parseDouble(GeoY.getText());
        String ouvertureValue = String.valueOf(ouverture.getTime());
        String fermetureValue = String.valueOf(fermeture.getTime());
        String datedispoValue = datedispo.getValue().toString();

        Terrain terrain = new Terrain(nomTerrain, adresseValue, descriptionValue, GeoXValue, GeoYValue, ouvertureValue, fermetureValue , datedispoValue);

        // Call the ServiceTerrain method to add terrain
        ServiceTerrain serviceTerrain = new ServiceTerrain();
        try {
            serviceTerrain.ajouter(terrain);
            System.out.println(datedispo.getValue().toString());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }


    private MapView createMapView() {
        MapView mapView = new MapView();
        mapView.setPrefSize(400, 400);
        mapView.setZoom(16);
        updateMapPoint();

        return mapView;
    }

    private void updateMapPoint() {
        try {
            double x = Double.parseDouble(GeoX.getText());
            double y = Double.parseDouble(GeoY.getText());
            MapPoint newPoint = new MapPoint(x, y);
            mapView.flyTo(0, newPoint, 0.1);
        } catch (NumberFormatException e) {
            // Handle the case where input is not a valid double
            System.out.println("Invalid input for GeoX or GeoY");
        }
    }

    @FXML
    private void onVerifyButtonClicked() {
        updateMapPoint();
        // Add any additional verification logic here
    }

    @FXML
    void onVerifyButtonClicked(ActionEvent event) {

        addTerrain();
    }
}


