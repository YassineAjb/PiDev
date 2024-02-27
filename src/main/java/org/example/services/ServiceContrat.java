package org.example.services;

import org.example.models.Contrat;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceContrat implements IService<Contrat> {
    private Connection connection;

    public ServiceContrat() {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Contrat contrat) throws SQLException {
        String sql = "INSERT INTO `contrat`(`id_Employe`, `date_debut`, `date_fin`, `salaire`) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contrat.getId_Employe());
        preparedStatement.setDate(2, contrat.getDate_debut());
        preparedStatement.setDate(3, contrat.getDate_fin());
        preparedStatement.setInt(4, contrat.getSalaire());
        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Contrat contrat) {
        try {
            String sql = "UPDATE contrat SET id_Employe=?, date_debut=?, date_fin=?, salaire=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, contrat.getId_Employe());
            preparedStatement.setDate(2, contrat.getDate_debut());
            preparedStatement.setDate(3, contrat.getDate_fin());
            preparedStatement.setInt(4, contrat.getSalaire());
            preparedStatement.setInt(5, contrat.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful!");
            } else {
                System.out.println("No rows affected. ID not found or values unchanged.");
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM `contrat` WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Contrat> afficher() throws SQLException {
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrat";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Contrat contrat = new Contrat();
            contrat.setId(rs.getInt("id"));
            contrat.setId_Employe(rs.getInt("id_Employe"));
            contrat.setDate_debut(rs.getDate("date_debut"));
            contrat.setDate_fin(rs.getDate("date_fin"));
            contrat.setSalaire(rs.getInt("salaire"));
            contrats.add(contrat);
        }
        return contrats;
    }
}