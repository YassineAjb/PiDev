package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.User;
import services.Crud_user;
import utils.Encryptor;
import utils.Session;

import java.util.Locale;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Pattern;


public class Login {
    @FXML
    private Button button_Sign_In;
    @FXML
    private TextField user_email;
    @FXML
    private TextField passwordTextfield;
    @FXML
    private PasswordField user_password;
    private final Crud_user cr = new Crud_user();
    @FXML
    private Label messageError;
    @FXML
    private CheckBox showpassword;
    @FXML
    private Hyperlink linkSignUp;
    @FXML
    private ImageView CloseWindow;
    @FXML
    private Hyperlink LinkForgotPassword;
    private Scene previousScene;


    @FXML
    public void Sign_In_OnClick(ActionEvent actionEvent) {
        String email = user_email.getText();
        String mot_de_passe = user_password.getText();
        if (email.isEmpty()) {
            messageError.setText("remplissez le champ");
        } else if (mot_de_passe.isEmpty()) {
            messageError.setText("remplissez le champ");
        }
        User loggedInUser = cr.Login(email);
        if (loggedInUser != null) {
            if(!Encryptor.TestPassword(loggedInUser.getMot_de_passe(),mot_de_passe))
            {
                messageError.setText("mot de passe invalide");
            }else {
                messageError.setText("Connexion réussie. Bienvenue, " + loggedInUser.getEmail());
                user_email.setText("");
                user_password.setText("");
                try {
                    Session.Start_session(loggedInUser);
                        if(loggedInUser.getRole().equals("admine")){

                        Parent root = FXMLLoader.load(getClass().getResource("Crud.fxml"));

                        button_Sign_In.getScene().setRoot(root);

                    }else{
                        Parent root = FXMLLoader.load(getClass().getResource("reclamationFront.fxml"));

                        button_Sign_In.getScene().setRoot(root);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }


        } else {

            messageError.setText("Email ou mot de passe incorrect");
        }
    }


    @FXML
    public void changeVisbility(ActionEvent actionEvent) {
        if (showpassword.isSelected()) {
            passwordTextfield.setText(user_password.getText());
            passwordTextfield.setVisible(true);
            user_password.setVisible(false);
            return;
        }
        user_password.setText(passwordTextfield.getText());
        user_password.setVisible(true);
        passwordTextfield.setVisible(false);
    }



    @FXML
    public void linkSignUp_OnClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            linkSignUp.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void closeWindow(Event event) {
        Platform.exit();
    }

    @FXML
    public void ForgotPassword(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
            LinkForgotPassword.getScene().setRoot(root);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }
}








