package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.Election;

public class CardController {


    @FXML
    private ImageView electionImage;

    @FXML
    private Label electionName;

    public void setData(Election election){
        electionName.setText(election.getNomE());
    }

}
