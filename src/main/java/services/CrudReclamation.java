package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Reclamation;
import utils.DB;

import java.sql.*;

public class CrudReclamation {
    private Connection connection;

    public CrudReclamation() {
        connection= DB.getInstance().getConnection();

    }
    public void create(Reclamation r) {
        try {
            String sql = "INSERT INTO reclamation (IdUser, Titre, Description, Etat) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, r.getIdUser());
            statement.setString(2, r.getTitre());
            statement.setString(3, r.getDescription());
            statement.setBoolean(4, r.getEtat());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Reclamation> getAllReclamation() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM reclamation";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setIdReclamation(resultSet.getInt("IdReclamation"));
                reclamation.setIdUser(resultSet.getInt("IdUser"));
                reclamation.setTitre(resultSet.getString("Titre"));
                reclamation.setDescription(resultSet.getString("Description"));
                reclamation.setEtat(resultSet.getBoolean("Etat"));
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reclamations;
    }

    public Reclamation getReclamationById(int id) {
        Reclamation reclamation = null;
        try {
            String sql = "SELECT * FROM reclamation WHERE IdReclamation = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reclamation = new Reclamation();
                reclamation.setIdReclamation(resultSet.getInt("IdReclamation"));
                reclamation.setIdUser(resultSet.getInt("IdUser"));
                reclamation.setTitre(resultSet.getString("Titre"));
                reclamation.setDescription(resultSet.getString("Description"));
                reclamation.setEtat(resultSet.getBoolean("Etat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reclamation;
    }

    public boolean updateReclamation(Reclamation r) {
        try {
            String sql = "UPDATE reclamation SET IdUser = ?, Titre = ?, Description = ?, Etat = ? WHERE IdReclamation = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, r.getIdUser());
            statement.setString(2, r.getTitre());
            statement.setString(3, r.getDescription());
            statement.setBoolean(4, r.getEtat());
            statement.setInt(5, r.getIdReclamation());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReclamation(int id) {
        try {
            String sql = "DELETE FROM reclamation WHERE IdReclamation = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    }

