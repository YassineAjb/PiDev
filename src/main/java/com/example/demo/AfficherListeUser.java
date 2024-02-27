package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import org.w3c.dom.Document;
import services.Crud_user;
import javafx.print.PrinterJob;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import utils.Encryptor;

public class AfficherListeUser implements Initializable {

    @FXML
    private TableView tableuser;
    @FXML
    private TableColumn roleCol;
    Crud_user us = new Crud_user();
    @FXML
    private TableColumn mot_de_passe_col;
    @FXML
    private TableColumn date_creationcol;
    @FXML
    private TableColumn cincol;
    @FXML
    private TableColumn emailcol;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField usercin;
    @FXML
    private TextField userrole;
    @FXML
    private TextField UserEmail;
    @FXML
    private DatePicker Datecreation;
    @FXML
    private TextField Password;
    @FXML
    private Label novalid2;
    @FXML
    private Label novalid;
    @FXML
    private Button modiferbutton;
    private boolean isAddingMode = true;
    private User selectedUserToUpdate;
    @FXML
    private ImageView downloadPdf;
    @FXML
    private TextField Search;
    private FilteredList<User> filteredList;
    private ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML
    private ImageView Redirect;
    private Scene previousScene;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        mot_de_passe_col.setCellValueFactory(new PropertyValueFactory<>("mot_de_passe"));
        date_creationcol.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        cincol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        //  tableuser.getColumns().addAll(roleCol,mot_de_passe_col,date_creationcol,cincol,emailcol);
        UserEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                novalid.setText("Invalid email format");
            } else {
                novalid.setText("");
            }
        });
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            if (change.isContentChange()) {
                if (change.getControlNewText().matches("\\d{0,8}")) {
                    return change;
                }
            }
            return null;
        });
        usercin.setTextFormatter(formatter);
        refresh();
        // Créer une FilteredList à partir de la liste observable userList
        filteredList = new FilteredList<>(userList, p -> true);

        // Attacher un écouteur d'événements au champ de recherche pour détecter les changements de texte
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterUsers(newValue);
        });


    }

    public void refresh() {
        this.tableuser.setItems(us.afficher());
        this.tableuser.refresh();
    }

    @FXML
    public void DeleteUseronClick(ActionEvent actionEvent) {

        Object selectedItem = tableuser.getSelectionModel().getSelectedItem();
        if (selectedItem instanceof User) {
            User selectedUser = (User) selectedItem;
            int userId = selectedUser.getId();
            System.out.println("User ID to delete: " + userId);
            us.deleteUser(userId);
            refresh();
        } else {
            System.out.println("rien n'est selectionne");
        }
    }

    @FXML
    public void SaveUser(ActionEvent actionEvent) {
        String email = UserEmail.getText();
        String password = Password.getText();
        String role = userrole.getText();
        Date dateCreation = new Date();
        String encryptedPassword = Encryptor.encryptPassword(password);
        int cin;
        try {
            cin = Integer.parseInt(usercin.getText());
        } catch (NumberFormatException e) {
            System.out.println("Le champ CIN doit contenir un entier valide");
            return;
        }
        if (role == null || role.isEmpty() || email.isEmpty() || password.isEmpty()) {
            novalid2.setText("All fields are required");
            return;
        }
        User user = new User(email, encryptedPassword, dateCreation, role, cin);
        if (isAddingMode) {
            // Mode ajout
            us.createUser(user);
            novalid2.setText("Nouvel utilisateur ajouté avec succès !");
        } else {
            user.setId(selectedUserToUpdate.getId());
            // Mode modification
            if (us.modifier(user)) {
                novalid2.setText("Utilisateur modifié avec succès !");
                selectedUserToUpdate = null; // Réinitialiser l'utilisateur sélectionné après modification


            }

        }
        refresh();


    }

    @FXML
    public void modiferUser(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
            Parent root = loader.load();
            ModifyUser controller=loader.getController();
            User selectedItem = (User)tableuser.getSelectionModel().getSelectedItem();
            controller.setUser(selectedItem);
            modiferbutton.getScene().setRoot(root);



        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    @FXML
    public void exportToPdf(MouseEvent event) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
            if (printerJob.showPrintDialog(tableuser.getScene().getWindow())) {
                PageLayout pageLayout = printerJob.getPrinter().getDefaultPageLayout();
                double scaleX = pageLayout.getPrintableWidth() / tableuser.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / tableuser.getBoundsInParent().getHeight();
                double scale = Math.min(scaleX, scaleY);
                Scale printScale = new Scale(scale, scale);
                tableuser.getTransforms().add(printScale);
                boolean success = printerJob.printPage(tableuser);
                if (success) {
                    showSuccessMessage("Le PDF a été téléchargé avec succès !");
                    printerJob.endJob();
                }else{  showErrorMessage("Une erreur s'est produite lors du téléchargement du PDF.");}

                tableuser.getTransforms().remove(printScale); // Réinitialiser la transformation après l'impression
            }
        }
    }



    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void filterUsers(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            refresh();
        } else {
            ObservableList<User> filteredList = FXCollections.observableArrayList();
            for (Object item : tableuser.getItems()) {
                User user = (User) item;
                if (user.getEmail().toLowerCase().contains(keyword.toLowerCase()) || user.getRole().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(user);
                }
            }
            tableuser.setItems(filteredList);
        }
    }


    @FXML
    public void returnToPreviousScene(MouseEvent event) {
      try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Crud.fxml"));
          Parent root = loader.load();

          Crud controller = loader.getController();
          controller.setPreviousScene(((Node) event.getSource()).getScene());

          Scene scene = new Scene(root);
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          stage.setScene(scene);

    } catch (
    IOException e) {
        System.err.println(e.getMessage());
    }
    }
    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

}















