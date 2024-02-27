package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.Crud_user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Crud implements Initializable {
    @javafx.fxml.FXML
    private Pane pnlOrders;
    Crud_user us = new Crud_user();
    @javafx.fxml.FXML
    private Pane pnlCustomer;
    @javafx.fxml.FXML
    private Button btnSignout1;
    @javafx.fxml.FXML
    private Pane pnlMenus;
    @javafx.fxml.FXML
    private Button AddButton;
    private Scene previousScene;
    @javafx.fxml.FXML
    private TableColumn mot_de_passe_col;
    @javafx.fxml.FXML
    private TableView tableuser;
    @javafx.fxml.FXML
    private TableColumn date_creationcol;
    @javafx.fxml.FXML
    private TableColumn cincol;
    @javafx.fxml.FXML
    private TableColumn emailcol;
    @javafx.fxml.FXML
    private TableColumn roleCol;
    @javafx.fxml.FXML
    private Button btnreturnSign;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        mot_de_passe_col.setCellValueFactory(new PropertyValueFactory<>("mot_de_passe"));
        date_creationcol.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        cincol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        refresh();
    }
    @javafx.fxml.FXML
    public void AddButton(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherListeUser.fxml"));
            AddButton.getScene().setRoot(root);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }
    public void refresh() {
        this.tableuser.setItems(us.afficher());
        this.tableuser.refresh();
    }

    public void exportToPdf(MouseEvent mouseEvent) {
    }

    @javafx.fxml.FXML
    public void returnSign(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Login controller = loader.getController();
            controller.setPreviousScene(((Node)actionEvent.getSource()).getScene());

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (
                IOException e) {
            System.err.println(e.getMessage());
        }

}}
