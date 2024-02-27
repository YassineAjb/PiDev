package controllers.Admin.Election;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import models.Election;
import services.ElectionService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ModifierElectionController {
            @FXML
            private Button chooseEimg;

            @FXML
            private Button btnSettings1;

            @FXML
            private Button btnStaff;

            @FXML
            private Button btnSignout;

            @FXML
            private Button btnReservation;

            @FXML
            private ImageView imageEModif;

            @FXML
            private Button btncalendrier;

            @FXML
            private Button btnBoutique;

            @FXML
            private TextField nomTFModfif;

            @FXML
            private TextField PosteTFModfif;

            @FXML
            private DatePicker dateTFModfif;

            @FXML
            private Button btnJoueurs;

            @FXML
            private Button btnAcceuil1;

            @FXML
            private TextField PeriodeTFModfif;
    private int electionId;

    // Custom setter for electionId
    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }


    @FXML
            void e7e5e5(ActionEvent event) {

            }

            @FXML
            void chosirImageE(ActionEvent event) {

            }
    private final ElectionService ps = new ElectionService();


          //  @FXML
           // void modifierElection(ActionEvent event) {

           // }
        /*   @FXML
           void modifierElection(ActionEvent event) throws SQLException {
               // Get the electionId from wherever you have stored it
              // int electionId = getElectionId(); // Implement this method to retrieve the electionId

               // Use the electionId to fetch the election data from the database
               ElectionService electionService = new ElectionService(); // Assuming you have a service class
               Election existingElection = electionService.getElectionById(this.electionId); // Implement this method

               // Populate your form fields or UI components with the existing data
               if (existingElection != null) {
                   // Assuming you have TextField fields for the election properties
                   nomTFModfif.setText(existingElection.getNomE());
                   dateTFModfif.setValue(existingElection.getDateE());
                   PosteTFModfif.setText(existingElection.getPosteE());
                   PeriodeTFModfif.setText(existingElection.getPeriodeP());

                   // You might need to handle image loading separately
                   // You can use existingElection.getImgEpath() to get the image path

               } else {
                   // Handle the case where the election with the given id is not found
                   System.err.println("Election not found with id: " + electionId);
               }

               try {
                   int id = this.electionId;
                   String nom = nomTFModfif.getText();
                   LocalDate date = dateTFModfif.getValue();
                   String poste = PosteTFModfif.getText();
                   String periode = PeriodeTFModfif.getText();


                   // Vérifiez si tous les champs nécessaires sont remplis
                   if (nom.isEmpty() || date == null || poste.isEmpty() || periode.isEmpty()) {
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setContentText("Veuillez remplir tous les champs.");
                       alert.showAndWait();
                       return; // Arrêtez l'exécution si des champs sont vides
                   }

                   // Créez un objet Election avec les nouvelles données
                   Election election = new Election(id, nom, date, poste, periode);

                   // Utilisez le service ElectionService pour modifier l'élection
                   ps.modifier(election);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Succès");
                       alert.setContentText("L'élection a été modifiée avec succès.");
                       alert.showAndWait();
                   } catch (NumberFormatException e) {
                // Gestion de l'exception si la conversion de l'ID échoue
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("L'ID doit être un nombre entier.");
                alert.showAndWait();
            } catch (SQLException e) {
                // Gestion des erreurs SQL
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
           }

*/




  /*  @FXML
    public void modifierElection(ActionEvent actionEvent) {
        ElectionService electionService = new ElectionService();

        try {
            String nom = nomTFModfif.getText();
            LocalDate date = dateTFModfif.getValue();
            String poste = PosteTFModfif.getText();
            String periode = PeriodeTFModfif.getText();

            // Check for null values or any other validations

            Election modifiedElection = new Election(11, nom, date, poste, periode);

            // Use the service ElectionService to modify the election
            ps.modifier(modifiedElection);

            // Show a success alert
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Succès");
//            alert.setContentText("L'élection a été modifiée avec succès.");
//            alert.showAndWait();
        }
        catch (SQLException e) {
            // Handle other SQL exceptions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.out.println(e.getMessage());
        }
    }*/

    /**************Modifier avec controle de saisie *********************/
    @FXML
    public void modifierElection(ActionEvent actionEvent) {
        // Input validation
        String nom = nomTFModfif.getText();
        LocalDate date = dateTFModfif.getValue();
        String poste = PosteTFModfif.getText();
        String periode = PeriodeTFModfif.getText();

        // Validate nom (3 to 20 characters)
        if (nom.isEmpty() || nom.length() < 3 || nom.length() > 20) {
            showAlert("Error", "Le nom doit avoir entre 3 et 20 caractères.");
            return;
        }
        nomTFModfif.addEventFilter(KeyEvent.KEY_TYPED, eventE -> {
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
        PosteTFModfif.addEventFilter(KeyEvent.KEY_TYPED, eventE -> {
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
        PeriodeTFModfif.addEventFilter(KeyEvent.KEY_TYPED, eventE -> {
            String input = eventE.getCharacter();
            if (!input.matches("[a-zA-Z ]")) {
                eventE.consume();
            }
        });

        // All input is valid, proceed with modifying the election
        try {
            Election modifiedElection = new Election(electionId, nom, date, poste, periode);
            // Use the service ElectionService to modify the election
            ps.modifier(modifiedElection);
            // Show a success alert
            showAlert("Succès", "L'élection a été modifiée avec succès.");
        } catch (SQLException e) {
            // Handle other SQL exceptions
            showAlert("Error", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }




  /*  @FXML
        void naviguezVersAffichage(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Election/AfficherElection.fxml"));
                nomTFModfif.getScene().setRoot(root);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }*/

    @FXML
    void naviguezMVersAffichage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Election/AfficherElection.fxml"));
            PosteTFModfif.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }




    }


