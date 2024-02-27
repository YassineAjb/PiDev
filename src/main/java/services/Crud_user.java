package services;

import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Crud_user {
    private Connection connection;


    public Crud_user() {
      connection= DB.getInstance().getConnection();

    }
    public void createUser(User user) {
        try {
            String sql = "INSERT INTO user (`email`, `mot_de_passe`, `date_creation`,`role`,`cin`) VALUES (?,?,?,?,?)";
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setString(1, user.getEmail());
            st.setString(2, user.getMot_de_passe());
            st.setDate(3,  new java.sql.Date(user.getDate_creation().getTime()));
            st.setString(4, user.getRole());
            st.setInt(5, user.getCin());
            st.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void deleteUser(int id)  {
   try {
    String sql = "delete from user where id_user = ?";
    PreparedStatement st = connection.prepareStatement(sql);


    st.setInt(1, id);
    st.executeUpdate();
   }catch (SQLException E)
{
    System.out.println(E.getMessage());
}}
    public boolean modifier(User usr)  {
        try {
            String sql = "Update user set email = ?, mot_de_passe= ? , role= ?,cin= ? where id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usr.getEmail());
            preparedStatement.setString(2, usr.getMot_de_passe());
            preparedStatement.setString(3, usr.getRole());
            preparedStatement.setInt(4,usr.getCin());
            preparedStatement.setInt(5,usr.getId());


            return  preparedStatement.executeUpdate()!=0;
        }catch (SQLException E)
        { System.out.println(E.getMessage());
    }
    return  false;
    }
    ObservableList<User> users = FXCollections.observableArrayList();

    public ObservableList<User> afficher() {
        ObservableList<User> users=FXCollections.observableArrayList();
         try {
            String sql = "SELECT * FROM user";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User p = new User();
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setMot_de_passe(rs.getString(3));
                p.setDate_creation(rs.getDate(4));
                p.setRole(rs.getString(5));
                p.setCin(rs.getInt(6));
                users.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return users;

    }
    public User Login(String email) {
       User user=new User();
        try {
            String sql = "SELECT * FROM user where email=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setMot_de_passe(rs.getString(3));
                user.setDate_creation(rs.getDate(4));
                user.setRole(rs.getString(5));
                user.setCin(rs.getInt(6));
                return user;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;

    }
    public boolean isUserExists(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            return count > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}