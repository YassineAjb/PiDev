package controllers.Admin.Election;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Election;
import services.ElectionService;

import java.time.LocalDate;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.nio.file.Files;

import javafx.scene.control.DatePicker;

public class AjouterElectionController {

    private String selectedImagePath;  // Declare a class-level variable
    private String elpaaath;  // Declare a class-level variable



    private final ElectionService ps = new ElectionService();
    @FXML
    private TextField nomTF;
    @FXML
    private DatePicker dateTF;
    @FXML
    private TextField PeriodeTF;
    @FXML
    private Button chooseEimg;
    @FXML
    private TextField PosteTF;
    Boolean cvPressed = false;
    FileChooser fc = new FileChooser();
    Election election = new Election();
   @FXML
    private void chosirImageE(ActionEvent event) {
        cvPressed = true;
       fc.setInitialDirectory(new File("C:\\Users\\yassi\\IdeaProjects\\SuiviPi1\\src\\main\\resources\\images\\"));
       fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fc.showOpenDialog(null);
        Path p1 = Paths.get("C:/Users/yassi/IdeaProjects/SuiviPi1/src/main/resources/images/" + UUID.randomUUID().toString().substring(0, 7) + file.getName());
        Path p2 = Paths.get(file.getAbsolutePath());
        try {
            Files.copy(p2, p1);

            Path relativePath = p1.relativize(p2);
            System.out.println(relativePath);
            String selectedImagePath = relativePath.toString().replace("..\\", "/images/");
            System.out.println(selectedImagePath);


            election.setImgEpath(selectedImagePath.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*@FXML
    void chosirImageE(ActionEvent event) {
        Stage stage = (Stage) chooseEimg.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG image", "*.png"),
                new FileChooser.ExtensionFilter("All images", "*.str")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            selectedImagePath = selectedFile.getAbsolutePath();
            Path selectedPath = Paths.get(selectedImagePath);
            Path resourcePath = Paths.get("C:/Users/yassi/IdeaProjects/SuiviPi1/src/main/resources/images");
            Path relativePath = resourcePath.relativize(selectedPath);

            String elpaaaath = "/images/"+relativePath;


            System.out.println(selectedImagePath);
            System.out.println(relativePath);
            System.out.println(elpaaaath);





            //Image image = new Image("file:" + selectedImagePath);
            //System.out.println(image);


        }
    }*/


   /* @FXML
    void ajouterElection(ActionEvent event) {
        selectedImagePath = election.getImgEpath();
        try {
            ps.ajouter(new Election(nomTF.getText(), dateTF.getValue(), PosteTF.getText(), PeriodeTF.getText(),selectedImagePath));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }*/
    /***************Ajout controle de saisie****************/
    @FXML
    void ajouterElection(ActionEvent event) {
        // Validate input
        String nom = nomTF.getText();
        LocalDate date = dateTF.getValue();
        String poste = PosteTF.getText();
        String periode = PeriodeTF.getText();

        // Validate nom (3 to 20 characters)
        if (nom.isEmpty() || nom.length() < 3 || nom.length() > 20) {
            showAlert("Error", "Le nom doit avoir entre 3 et 20 caractères.");
            return;
        }
        nomTF.addEventFilter(KeyEvent.KEY_TYPED, eventE -> {
            String input = eventE.getCharacter();
            if (!input.matches("[a-zA-Z ]")) {
                eventE.consume();
            }
        });

        // Validate poste (3 to 20 characters)
        if (poste.isEmpty() || poste.length() < 3 || poste.length() > 20) {
            showAlert("Error", "La poste doit avoir entre 3 et 20 caractères.");
            return;
        }
        PosteTF.addEventFilter(KeyEvent.KEY_TYPED, eventE -> {
            String input = eventE.getCharacter();
            if (!input.matches("[a-zA-Z ]")) {
                eventE.consume();
            }
        });

        // Validate periode (integer followed by "ans")
        if (!periode.matches("\\d+ans")) {
            showAlert("Error", "La période doit être un entier suivi de 'ans'.");
            return;
        }
       PeriodeTF.addEventFilter(KeyEvent.KEY_TYPED, eventE -> {
            String input = eventE.getCharacter();
            if (!input.matches("[a-zA-Z ]")) {
                eventE.consume();
            }
        });

        // All input is valid, proceed with adding the election
        selectedImagePath = election.getImgEpath();
        try {
            ps.ajouter(new Election(nom, date, poste, periode, selectedImagePath));
            // Show a success alert if the addition is successful
            showAlert("Succès", "L'élection a été ajoutée avec succès.");
        } catch (SQLException e) {
            // Show an error alert for database-related issues
            showAlert("Error", e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }




    @FXML
    void naviguezVersAffichage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Election/AfficherElection.fxml"));
            nomTF.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }




}
