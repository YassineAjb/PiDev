package org.example.services;

import org.example.models.Staff;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceStaff implements IService<Staff> {
    private Connection connection;
    public ServiceStaff(){
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Staff Staff) throws SQLException {
        String sql ="INSERT INTO `Staff`(`nom`, `prenom`, `age`) VALUES ('"+Staff.getNom()+"','"+Staff.getPrenom()+"','"+Staff.getAge()+"')";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Override
    public void modifier(Staff Staff) throws SQLException {
        String sql = "UPDATE Staff SET nom= ?,prenom= ?,age=? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Staff.getNom());
        preparedStatement.setString(2, Staff.getPrenom());
        preparedStatement.setInt(3,Staff.getAge());
        preparedStatement.setInt(4,Staff.getId());
        preparedStatement.executeUpdate();
        System.out.println("qdqdqssqdqs");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM `Staff` WHERE id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
    }

    @Override
    public List<Staff> afficher() throws SQLException {
        List<Staff> Staffs = new ArrayList<>();
        String sql = "select * from Staff";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.getResultSet();
        while(rs.next()){
            Staff p = new Staff();
            p.setId(rs.getInt("id"));
            p.setAge(rs.getInt("age"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            Staffs.add(p);
        }
        return Staffs;
    }
}
