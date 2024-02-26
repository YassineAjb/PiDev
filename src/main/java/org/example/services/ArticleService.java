package org.example.services;

import org.example.models.Article;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleService implements IService<Article> {

    private final Connection connection;

    public ArticleService() {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(Article article) throws SQLException {
        String sql = "INSERT INTO article ( IdJournaliste,Titre, Description,Image) VALUES ( ?,?,?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, article.getIdJournaliste());
            preparedStatement.setString(2, article.getTitreArticle());
            preparedStatement.setString(3, article.getContenuArticle());
            preparedStatement.setString(4, article.getImageArticle());

            preparedStatement.executeUpdate();
        }
    }


    @Override
    public void modify(Article article) throws SQLException {
        String sql = "update article set  IdJournaliste = ?, Titre = ? , Description=?, Image=?  where idArticle = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,article.getIdJournaliste());
        preparedStatement.setString(2, article.getTitreArticle());
        preparedStatement.setString(3, article.getContenuArticle());
        preparedStatement.setString(4, article.getImageArticle());
        preparedStatement.setInt(5, article.getIdArticle());

        preparedStatement.executeUpdate();
    }


    @Override
    public void remove(int id) throws SQLException {
        String sql = "delete from article where IdArticle = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Article> getall() throws SQLException {
        String sql = "select * from article";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Article> article = new ArrayList<>();
        while (rs.next()) {
            Article a = new Article();
            a.setIdArticle(rs.getInt("IdArticle"));
            a.setTitreArticle(rs.getString("Titre"));
            a.setContenuArticle(rs.getString("Description"));
            a.setImageArticle(rs.getString("Image"));


            article.add(a);
        }
        return article;
    }

}
