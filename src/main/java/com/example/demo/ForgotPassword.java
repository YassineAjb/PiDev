package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.Crud_user;
import utils.EmailSender;
import utils.Encryptor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPassword implements Initializable {
    @javafx.fxml.FXML
    private TextField code;
    @javafx.fxml.FXML
    private TextField EmailForgotPassword;
    @javafx.fxml.FXML
    private Button buttonForgotPassword;
    String GeneratedCode = "";

    private int process;
    private Crud_user crudUser = new Crud_user();
    @javafx.fxml.FXML
    private ImageView returnLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        code.setDisable(true);
        process = 1;

    }

    @javafx.fxml.FXML
    public void ForgotPasswordOn_Click(ActionEvent event) {
        if (process == 1) {
            String email = EmailForgotPassword.getText();
            if (crudUser.isUserExists(email)) {
                GeneratedCode = Encryptor.generateCode(6);
                EmailSender emailSender = new EmailSender();
                emailSender.sendEmail(email, "RESET PASSWORD", "Votre code est : " + GeneratedCode + "");


                code.setDisable(false);
                EmailForgotPassword.setDisable(true);
                process = 2;

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email Not Valid");
                alert.setHeaderText("NOT VALID EMAIL ");
                alert.setContentText("Account NON VALID");
                alert.showAndWait();
            }

        } else {
            if (GeneratedCode.equals(code.getText())) {

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CODE Not Valid");
                alert.setHeaderText("NOT VALID CODE ");
                alert.setContentText("NOT VALID CODE");
                alert.showAndWait();
            }
        }

    }

    @javafx.fxml.FXML
    public void returnToPreviousScene(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Login controller = loader.getController();
            controller.setPreviousScene(((Node) event.getSource()).getScene());

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (
                IOException e) {
            System.err.println(e.getMessage());
        }
    }
}