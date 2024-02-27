package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.models.Contrat;
import org.example.models.ContratCell;
import org.example.models.Joueur;
import org.example.models.JoueurCell;
import org.example.services.ServiceContrat;
import org.example.services.ServiceJoueur;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class AfficherJoueur {
    private final ServiceJoueur serviceJoueur= new ServiceJoueur();
    private final ServiceContrat serviceContrat= new ServiceContrat();
    @FXML
    private GridPane gridJoueurs;
    @FXML
    public AnchorPane interfaceAjout;
    @FXML
    public AnchorPane interfaceAffichage;
    @FXML
    public AnchorPane interfaceSuppression;
    @FXML
    public AnchorPane interfaceModification;
    @FXML
    private TextField AgeTF;

    @FXML
    private TextField HauteurTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField PoidsTF;

    @FXML
    private TextField PrenomTF;
    @FXML
    private TextField Joueur;
    @FXML
    private TableColumn<Joueur, Integer> ageCol;
    @FXML
    private TableColumn<Joueur, Integer> hauteurCol;
    @FXML
    private TableColumn<Joueur, Integer> poidsCol;
    @FXML
    private TableColumn<Joueur, Integer> piedfortCol;
    @FXML
    private TableColumn<Joueur, String> positionCol;
    @FXML
    private TableColumn<Joueur, String> nomCol;
    @FXML
    private TableColumn<Joueur, String> prenomCol;
    @FXML
    private TableView<Joueur> tableView;
    @FXML
    private Button detailsbtn;
    @FXML
    private Label nomJoueur;
    @FXML
    private ImageView pfp;
    @FXML
    private Rectangle joueurbackground;
    @FXML
    private ListView<Joueur> listJoueur;
    @FXML
    private Button importerJoueur;
    private String filePath;
    @FXML
    private Button ajouterJoueur;
    @FXML
    private ImageView importedImage;
    @FXML
    private Button modifierJoueur;
    @FXML
    private Button supprimerJoueur;
    @FXML
    private TextField IdTF;
    @FXML
    private ComboBox<String> PositionTF;
    @FXML
    private CheckBox DroiteTF;
    @FXML
    private CheckBox GaucheTF;
    @FXML
    private TextField imageTF;
    private String PiedfortTF;
    @FXML
    private ListView<Contrat> listContrat;
    public void ajouterJoueur(ActionEvent actionEvent) {

        try {
            serviceJoueur.ajouter(new Joueur(PositionTF.getValue(),Integer.parseInt(HauteurTF.getText()),Integer.parseInt(PoidsTF.getText()),PiedfortTF,NomTF.getText(),PrenomTF.getText(),Integer.parseInt(AgeTF.getText())));

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void initialize() {

        AgeTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = event.getCharacter();
            if (!input.matches("[0-9]")) {
                event.consume();
            }
        });

        HauteurTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = event.getCharacter();
            if (!input.matches("[0-9]")) {
                event.consume();
            }
        });

        PoidsTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = event.getCharacter();
            if (!input.matches("[0-9]")) {
                event.consume();
            }
        });

        NomTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = event.getCharacter();
            if (!input.matches("[a-zA-Z ]")) {
                event.consume();
            }
        });

        PrenomTF.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = event.getCharacter();
            if (!input.matches("[a-zA-Z ]")) {
                event.consume();
            }
        });
        ObservableList<String> positionOptions = FXCollections.observableArrayList("GK", "DC","AL","AD ","MD","MC","MO","AD","AG","AP","SA");
        PositionTF.setItems(positionOptions);

        DroiteTF.setOnAction(event -> {
            if (DroiteTF.isSelected()) {
                PiedfortTF = "Droite";
                GaucheTF.setSelected(false); // Uncheck checkbox 2 if checkbox 1 is checked
            }
        });
        GaucheTF.setOnAction(event -> {
            if (GaucheTF.isSelected()) {
                PiedfortTF = "Gauche";
                DroiteTF.setSelected(false); // Uncheck checkbox 2 if checkbox 1 is checked
            }
        });
        try {
            try {
                recupererListJoueur();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            listJoueur.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                Joueur selectedJoueur = listJoueur.getSelectionModel().getSelectedItem();
                if (selectedJoueur != null) {
                    // Populate UI fields with selected joueur details
                    // Assuming NomJoueur, Position, Hauteur, Poids, Piedfort, Prenom, and Age are your UI elements
                    IdTF.setText(String.valueOf(selectedJoueur.getId()));
                    NomTF.setText(selectedJoueur.getNom());
                    PositionTF.setValue(selectedJoueur.getPosition());
                    HauteurTF.setText(String.valueOf(selectedJoueur.getHauteur()));
                    PoidsTF.setText(String.valueOf(selectedJoueur.getPoids()));
                    if(selectedJoueur.getPiedfort().equals("Droite")){
                        DroiteTF.setSelected(true);
                        GaucheTF.setSelected(false);
                    }else {
                        DroiteTF.setSelected(false);
                        GaucheTF.setSelected(true);
                    }
                    PrenomTF.setText(selectedJoueur.getPrenom());
                    AgeTF.setText(String.valueOf(selectedJoueur.getAge()));
                    imageTF.setText(String.valueOf("images/"+selectedJoueur.getImagePath()));
                    System.out.println("images/"+selectedJoueur.getImagePath());

                    //importedImage = new ImageView("images/"+selectedJoueur.getImagePath());
                    try {
                        Image image = new Image("images/" + selectedJoueur.getImagePath());
                        importedImage.setImage(image);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            });
            FileChooser fileChooser = new FileChooser();

            // Set title for FileChooser
            fileChooser.setTitle("Select File");
            // Set action for the button
            importerJoueur.setOnAction(e -> {
                // Show open dialog when the button is clicked
                File selectedFile = fileChooser.showOpenDialog(new Stage());

                // Check if a file is selected
                if (selectedFile != null) {
                    // Display the selected file path
                    System.out.println("Selected file: " + selectedFile.getPath());
                    String[] segments = selectedFile.getAbsolutePath().split("\\\\");

                    filePath = selectedFile.getName();
                    try {
                        Image image = new Image("images/" + filePath);
                        importedImage.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //importedImage = new ImageView(new Image("images/"+selectedFile.getName()));
                } else {
                    System.out.println("No file selected.");
                }
            });

            ajouterJoueur.setOnAction(e ->{
            try {
                if (NomTF.getText().isEmpty() || PrenomTF.getText().isEmpty() || AgeTF.getText().isEmpty() || PositionTF.getValue() == null || HauteurTF.getText().isEmpty() || PoidsTF.getText().isEmpty() || (!DroiteTF.isSelected() && !GaucheTF.isSelected())) {
                    // Display a warning or error message
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Empty Fields");
                    alert.setContentText("Please fill in all the required fields.");
                    alert.showAndWait();
                }else{
                    serviceJoueur.ajouter(new Joueur(PositionTF.getValue(),Integer.parseInt(HauteurTF.getText()),Integer.parseInt(PoidsTF.getText()),PiedfortTF,NomTF.getText(),PrenomTF.getText(),Integer.parseInt(AgeTF.getText()),filePath));

                }

                recupererListJoueur();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
            });

            modifierJoueur.setOnAction(e ->{
                try{
                    if (NomTF.getText().isEmpty() || PrenomTF.getText().isEmpty() || AgeTF.getText().isEmpty() || PositionTF.getValue() == null || HauteurTF.getText().isEmpty() || PoidsTF.getText().isEmpty() || (!DroiteTF.isSelected() && !GaucheTF.isSelected())) {
                        // Display a warning or error message
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Empty Fields");
                        alert.setContentText("Please fill in all the required fields.");
                        alert.showAndWait();
                    }else {
                        serviceJoueur.modifier(new Joueur(Integer.parseInt(IdTF.getText()), PositionTF.getValue(), Integer.parseInt(HauteurTF.getText()), Integer.parseInt(PoidsTF.getText()), PiedfortTF, NomTF.getText(), PrenomTF.getText(), Integer.parseInt(AgeTF.getText()), filePath));
                    }
                    recupererListJoueur();
                    System.out.println("modifier");
                }catch (SQLException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            });

            supprimerJoueur.setOnAction(e ->{
                try {
                    serviceJoueur.supprimer(Integer.parseInt(IdTF.getText()));
                    recupererListJoueur();
                }catch (SQLException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            });
            //gridJoueurs.setHgap(10); // Horizontal gap between nodes
            gridJoueurs.setVgap(20); // Vertical gap between nodes
            int rowIndex = 1;
            List<Joueur> joueurs = serviceJoueur.afficher();
            for (Joueur joueur : joueurs) {
                Button button = new Button("plus de details");
                button.setOnAction(event -> {
                    // Create a new stage for the popup window
                    Stage popupStage = new Stage();

                    // Create the content for the popup (you can use any layout)
                    VBox popupContent = new VBox();
                    popupContent.getChildren().add(new Button("Close Popup"));

                    // Create a scene with the popup content
                    Scene popupScene = new Scene(popupContent, 200, 100);

                    // Set the scene to the popup stage
                    popupStage.setScene(popupScene);

                    // Set modality to APPLICATION_MODAL to make it modal
                    //popupStage.initModality(Modality.APPLICATION_MODAL);

                    // Set the title of the popup window
                    popupStage.setTitle("Popup Window");

                    // Show the popup window
                    popupStage.show();
                });
                button.setPrefWidth(300);
                Label label = new Label(joueur.getNom()+"\n"+joueur.getPrenom()+"\n"+joueur.getAge());
                ImageView imageView = new ImageView(new Image(("images/icon.png")));
                imageView.setFitWidth(150); // Set the desired width
                imageView.setFitHeight(150); // Set the desired height
                gridJoueurs.setMargin(imageView, new Insets(20));
                gridJoueurs.addRow(rowIndex, imageView, label,button);
                rowIndex++;
            }
        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    private void recupererListJoueur() throws SQLException {
        List<Joueur> joueurs = serviceJoueur.afficher(); // Assuming serviceJoueur is your service class for managing Joueur
        ObservableList<Joueur> observableArrayList = FXCollections.observableArrayList(joueurs);

        listJoueur.setCellFactory(new Callback<ListView<Joueur>, ListCell<Joueur>>() {
            @Override
            public ListCell<Joueur> call(ListView<Joueur> listView) {
                return new JoueurCell();
            }
        });

        listJoueur.setItems(observableArrayList);
    }

    private void recupererListContrat() throws SQLException {
        List<Contrat> contrats = serviceContrat.afficher(); // Assuming serviceContrat is your service class for managing Contrat
        ObservableList<Contrat> observableArrayList = FXCollections.observableArrayList(contrats);

        listContrat.setCellFactory(new Callback<ListView<Contrat>, ListCell<Contrat>>() {
            @Override
            public ListCell<Contrat> call(ListView<Contrat> listView) {
                return new ContratCell();
            }
        });

        listContrat.setItems(observableArrayList);
    }

    public void naviguezVersAjouter(ActionEvent actionEvent) {
        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterJoueur.fxml"));
            tableView.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }*/
        }

    public void ajouterMouseClicked(MouseEvent mouseEvent) {
            interfaceAffichage.setVisible(false);
            interfaceAjout.setVisible(true);
            interfaceSuppression.setVisible(false);
            interfaceModification.setVisible(false);
    }

    public void afficherMouseClicked(MouseEvent mouseEvent) {
        interfaceAjout.setVisible(false);
        interfaceAffichage.setVisible(true);
        interfaceSuppression.setVisible(false);
        interfaceModification.setVisible(false);
    }
    public void supprimerMouseClicked(MouseEvent mouseEvent) {
        interfaceAjout.setVisible(false);
        interfaceAffichage.setVisible(false);
        interfaceSuppression.setVisible(true);
        interfaceModification.setVisible(false);
    }
    public void modifierMouseClicked(MouseEvent mouseEvent) {
        interfaceAjout.setVisible(false);
        interfaceAffichage.setVisible(false);
        interfaceSuppression.setVisible(false);
        interfaceModification.setVisible(true);
    }

}


