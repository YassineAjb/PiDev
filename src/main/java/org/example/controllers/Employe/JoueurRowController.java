package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.models.Joueur;

public class JoueurRowController {

    @FXML
    private Label JoueurAge;

    @FXML
    private ImageView JoueurImg;

    @FXML
    private Label JoueurNom;

    @FXML
    private Label JoueurPrenom;

    @FXML
    private Button JoueurDetails;

    private Joueur joueur;

    public void setData(Joueur joueur){
        JoueurAge.setText(String.valueOf(joueur.getAge()));
        JoueurNom.setText(joueur.getNom());
        JoueurNom.setWrapText(true);
        JoueurPrenom.setText(joueur.getPrenom());
        Image image = new Image("images/"+joueur.getImagePath());
        JoueurImg.setImage(image);
        JoueurDetails.setText("Plus de details");
    }



}
