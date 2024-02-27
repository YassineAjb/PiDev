package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import models.Election;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListElectionController implements Initializable {
    @FXML
    private Button btnSettings1;

    @FXML
    private Button btnStaff;

    @FXML
    private Button btnBoutique;

    @FXML
    private Button btnSignout;

    @FXML
    private GridPane ElectionContainer;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btncalendrier;

    @FXML
    private Button btnJoueurs;

    @FXML
    private Button btnAcceuil1;

    private List<Election> recommended;

    /******************************************************/


    private List<Election> getData() {
        List<Election> election = new ArrayList<>();

        Election recommended1 = new Election();


        recommended1.setNomE("Kiwi");
      //  recommended1.setDescription("eeeeee");
        recommended1.setDateE(LocalDate.of(2022, 12, 12));
        election.add(recommended1);

        Election recommended2 = new Election();
        recommended2.setIdE(0);
        recommended2.setNomE("Kiwi");
       // recommended2.setDescription("eeeeee");
        recommended2.setDateE(LocalDate.of(2022, 12, 12));
        election.add(recommended2);

        return election;

    }


    /***********************************************************/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recommended = getData();
        int column = 0;
        int row = 0;

        try {
            for (Election el : recommended) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Card.fxml"));
                Pane cardBox = fxmlLoader.load();
                CardController electionController = fxmlLoader.getController();
                electionController.setData(el);

                ElectionContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));

                // Check if the current row is filled, and increment the row
                if (column == 2) {
                    column = 0;
                    ++row;
                    // Move these lines outside the loop
                    // Set grid width
                    ElectionContainer.setMinWidth(100);
                    ElectionContainer.setPrefWidth(100);
                    ElectionContainer.setMaxWidth(100);

                    // Set grid height
                    ElectionContainer.setMinHeight(100);
                    ElectionContainer.setPrefHeight(100);
                    ElectionContainer.setMaxHeight(100);
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}