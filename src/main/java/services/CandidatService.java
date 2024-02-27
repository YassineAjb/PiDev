package services;

        import models.Candidat;
        import utils.MyDatabase;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class CandidatService implements IService<Candidat> {

    private Connection connection;

    public CandidatService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Candidat candidat) throws SQLException {
        String sql = "INSERT INTO candidat (nomC, prenomC, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidat.getNomC());
            preparedStatement.setString(2, candidat.getPrenomC());
            preparedStatement.setInt(3, candidat.getAge());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifier(Candidat candidat) throws SQLException {
        String sql = "update candidat set nomC = ?, prenomC = ?, age = ? where idC = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidat.getNomC());
            preparedStatement.setString(2, candidat.getPrenomC());
            preparedStatement.setInt(3, candidat.getAge());
            preparedStatement.setInt(4, candidat.getIdC());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void supprimer(String nom) throws SQLException {

    }

    //  @Override
    /*public void supprimer(int idC) throws SQLException {
        String sql = "delete from candidat where idC = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idC);
            preparedStatement.executeUpdate();
        }
    }*/

    @Override
    public List<Candidat> recuperer() throws SQLException {
        String sql = "select * from candidat";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            List<Candidat> candidats = new ArrayList<>();
            while (rs.next()) {
                Candidat candidat = new Candidat();
                candidat.setIdC(rs.getInt("idC"));
                candidat.setNomC(rs.getString("nomC"));
                candidat.setPrenomC(rs.getString("prenomC"));
                candidat.setAge(rs.getInt("age"));

                candidats.add(candidat);
            }
            return candidats;
        }
    }




}
