package controllers;

import controllers.Admin.Election.ModifierElectionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Election;

public class ELectionItemController implements Initializable {

    @FXML
    private Label nomElection;

    @FXML
    private ImageView imgElection;

    @FXML
    private Label periodeElection;

    @FXML
    private Label dateElection;

    private int electionId;

    @FXML
    private Label posteElection;

    public void setData(Election election) {
        nomElection.setText(election.getNomE());
        posteElection.setText(election.getPosteE());
        periodeElection.setText(election.getPeriodeP());
        dateElection.setText(String.valueOf(election.getDateE()));

        electionId = election.getIdE();




        String imagePath = election.getImgEpath();

        if (imagePath != null && !imagePath.isEmpty()) {
            // Load the image from the specified path
            try (InputStream stream = getClass().getResourceAsStream(imagePath)) {
                if (stream != null) {
                    Image image = new Image(stream);
                    imgElection.setImage(image);
                } else {
                    System.err.println("Image not found:" + imagePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading image:" + e.getMessage());
            }
        } else {
            // Handle the case when the image path is not provided in the database
            System.err.println("Image path not found in the database for election: " + election.getNomE());
        }














        /*String validImagePath = "/images/vote12.png";

        // Load image if the path is not null
        if (validImagePath != null) {
            try {
                InputStream stream = getClass().getResourceAsStream(validImagePath);
                if (stream != null) {
                    Image image = new Image(stream);
                    imgElection.setImage(image);
                } else {
                    // Handle the case when the stream is null (image not found)
                    System.err.println("Image not found:" + validImagePath);
                }
            } catch (Exception e) {
                // Handle exceptions related to image loading
                e.printStackTrace();
            }
        }*/



















       /* if (election.getImgEpath() != null) {
            Image image = new Image(getClass().getResourceAsStream(election.getImgEpath()));
            imgElection.setImage(image);
        }

       /* Image imgProfile;
        imgProfile = new Image(Objects.requireNonNull(getClass().getResourceAsStream(election.getImgEpath())));
        imgElection.setImage(imgProfile);*/

/*if (election.getImgEpath() != null) {
            String imagePath = Objects.requireNonNull(getClass().getResource(election.getImgEpath())).toExternalForm();
            Image image = new Image(imagePath);
            imgElection.setImage(image);
        }*/

        //System.out.println("hedha nom : "+election.getNomE());
    }

    @FXML
    void itemUpdate(ActionEvent event) {
        try {
            // Load the ModifierElection.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Election/ModifierElection.fxml"));
            Parent root = loader.load();

            // Pass the electionId to the ModifierElectionController
            ModifierElectionController modifierElectionController = loader.getController();
            modifierElectionController.setElectionId(electionId);


            // Set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    void itemSupprimer(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Election/SupprimerElection.fxml"));
            nomElection.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
