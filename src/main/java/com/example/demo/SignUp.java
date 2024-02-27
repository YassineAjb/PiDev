package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.User;
import services.Crud_user;
import utils.Encryptor;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    @javafx.fxml.FXML
    public Hyperlink Haveaccount;
    @javafx.fxml.FXML
    private Button SaveButton;
    private final Crud_user cr = new Crud_user();
    @javafx.fxml.FXML
    private TextField UserEmail;
    @javafx.fxml.FXML
    private PasswordField Password;
    @javafx.fxml.FXML
    private PasswordField ConfirmPassword;
    @javafx.fxml.FXML
    private Label noValid;
    @javafx.fxml.FXML
    private Label noValid1;
    @javafx.fxml.FXML
    private DatePicker dateCreation;
    @javafx.fxml.FXML
    private ComboBox roleCombo;
    @javafx.fxml.FXML
    private TextField CIN;
    @FXML
    private ImageView imageClosewindow;
    @FXML
    private Label novalid2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleCombo.getItems().addAll("Journaliste","membre","admin");
        roleCombo.setValue("");
        ConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(Password.getText())) {
                noValid1.setText("Passwords do not match");
            } else {
                noValid1.setText("");
            }
        });
        Password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isStrongPassword(newValue)) {
                noValid1.setText("Password must be strong");
            } else if (!newValue.equals(ConfirmPassword.getText())) {
                noValid1.setText("Passwords do not match");
            } else {
                noValid1.setText("");
            }
        });
        UserEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                noValid.setText("Invalid email format");
            } else {
                noValid.setText("");
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
        CIN.setTextFormatter(formatter);

    }

    @javafx.fxml.FXML
    public void linkHaveAccount_OnClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Haveaccount.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


        @FXML
        public void SubmitButton_OnClick(ActionEvent actionEvent){
            String role = (String) roleCombo.getValue();
            String email = UserEmail.getText();
            String password = Password.getText();
            int cin;
            try {
                cin = Integer.parseInt(CIN.getText());
            } catch (NumberFormatException e) {
                // Gérer le cas où le texte ne peut pas être converti en entier
                System.out.println("Le champ CIN doit contenir un entier valide");
                return; // Sortir de la méthode si la conversion échoue
            }
            if (role == null || role.isEmpty() || email.isEmpty() || password.isEmpty()) {
                noValid.setText("All fields are required");
                return;
            }
            if (!password.equals(ConfirmPassword.getText())) {
                noValid1.setText("Passwords do not match");
                return;
            }
            if (!isStrongPassword(password)) {
                noValid1.setText("Password must be strong");
                return;
            }
            String encryptedPassword = Encryptor.encryptPassword(password);
            Date dateCreation = new Date();
            User user = new User(email, encryptedPassword, dateCreation, role, cin);
            if(cr.isUserExists(user.getEmail())) {
                System.out.println("l utilistaeur existe deja");
                novalid2.setText("You hava already account!");
               return;
            }else {cr.createUser(user);
                novalid2.setText("Now you have account");}
        }
    private boolean isStrongPassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");
    }

    @FXML
    public void CloseWindow_OnClick(Event event) {
        Platform.exit();
    }




}



