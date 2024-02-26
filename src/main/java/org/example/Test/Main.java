package org.example.Test;

import com.google.maps.model.AddressComponentType;
import com.google.maps.model.PlaceDetails;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.example.models.Terrain;
import org.example.services.ServiceTerrain;

import java.sql.SQLException;

public class Main
{

    public static void main(String[] args) {
        ServiceTerrain serviceTerrain = new ServiceTerrain();
        try {
            System.out.println(            serviceTerrain.afficherTerrain("rifed"));

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
}