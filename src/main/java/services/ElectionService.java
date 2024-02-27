package services;

import models.Election;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ElectionService implements IService<Election> {

    private Connection connection;

    public ElectionService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Election election) throws SQLException {

        String sql = "INSERT INTO evenement (nomE, dateE, posteE, periodeP, imgEpath) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, election.getNomE());
            preparedStatement.setDate(2, java.sql.Date.valueOf(election.getDateE()));
            preparedStatement.setString(3, election.getPosteE());
            preparedStatement.setString(4, election.getPeriodeP());
            preparedStatement.setString(5, election.getImgEpath());
            preparedStatement.executeUpdate();
        }
    }


    @Override
    public void modifier(Election election) throws SQLException {
        String sql = "UPDATE evenement SET nomE = ?, dateE = ?, posteE = ?, periodeP = ?, imgEpath = ? WHERE idE = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, election.getNomE());
            preparedStatement.setDate(2, java.sql.Date.valueOf(election.getDateE()));
            preparedStatement.setString(3, election.getPosteE());
            preparedStatement.setString(4, election.getPeriodeP());
            preparedStatement.setString(5, election.getImgEpath());
            preparedStatement.setInt(6, election.getIdE());
            preparedStatement.executeUpdate();
        }
    }

  /*  public void modifierbyid(Election election,int idE) throws SQLException {
        System.out.println("hKKK");

        String sql = "UPDATE evenement SET nomE = ?, dateE = ?, posteE = ?, periodeP = ?, imgEpath = ? WHERE idE = ?";
        System.out.println("hhh");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, election.getNomE());
            preparedStatement.setDate(2, java.sql.Date.valueOf(election.getDateE()));
            preparedStatement.setString(3, election.getPosteE());
            preparedStatement.setString(4, election.getPeriodeP());
            preparedStatement.setString(5, election.getImgEpath());
            preparedStatement.setInt(6, election.getIdE());
            preparedStatement.executeUpdate();
        }
    }*/


    @Override
    public void supprimer(String nomE) throws SQLException {
        String sql = "delete from evenement where NomE = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nomE);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Election> recuperer() throws SQLException {
        String sql = "SELECT * FROM evenement";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Election> elections = new ArrayList<>();
        while (rs.next()) {
            Election p = new Election();
            p.setIdE(rs.getInt("idE"));
            p.setNomE(rs.getString("nomE"));
            Date sqlDate = rs.getDate("dateE");
            p.setDateE(sqlDate.toLocalDate());
            p.setPosteE(rs.getString("posteE"));
            p.setPeriodeP(rs.getString("periodeP"));
            p.setImgEpath(rs.getString("imgEpath"));
            elections.add(p);
        }
        return elections;
    }

    public int recupererByid(String nomElection) throws SQLException {
        String sql = "SELECT idE FROM evenement WHERE nomE = ?";

        int id = 0;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nomElection);

        // Execute the prepared statement without passing the SQL string again
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            id = rs.getInt("idE");
            System.out.println(id);
        }


        return id;
    }

   /* public Election getElectionById(int electionId) throws SQLException {
        String sql = "SELECT * FROM evenement WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, electionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Election election = new Election();
                    election.setIdE(resultSet.getInt("id"));
                    election.setNomE(resultSet.getString("nomE"));
                    election.setDateE(resultSet.getDate("dateE").toLocalDate());
                    election.setPosteE(resultSet.getString("posteE"));
                    election.setPeriodeP(resultSet.getString("periodeP"));
                    election.setImgEpath(resultSet.getString("imgEpath"));
                    // Set other properties as needed

                    return election;
                }
            }
        }

        // Return null if no election is found with the given ID
        return null;
    }*/



/***************************
    public Election getElectionById(int electionId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM evenement WHERE id = ?")) {
            statement.setInt(1, electionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Election election = new Election();
                    election.setIdE(resultSet.getInt("id"));
                    election.setNomE(resultSet.getString("nomE"));
                    election.setDateE(resultSet.getDate("dateE").toLocalDate());
                    election.setPosteE(resultSet.getString("posteE"));
                    election.setPeriodeP(resultSet.getString("periodeP"));
                    election.setImgEpath(resultSet.getString("imgEpath"));
                    // Set other properties as needed

                    return election;
                }
            }
        }

        // Return null if no election is found with the given ID
        return null;
    }*/



}


