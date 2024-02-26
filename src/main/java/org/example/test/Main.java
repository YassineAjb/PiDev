package org.example.test;


import org.example.models.Article;
import org.example.models.Match;
import org.example.services.ArticleService;
import org.example.services.MatchService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world !");
        MatchService match = new MatchService();
        ArticleService article = new ArticleService() ;
        Date date = Date.valueOf(LocalDate.now());

        try {
//            ps.ajouter(new Joueur("cb", 5, 6,8));
//            System.out.println(match.getall());
//            Match match1 = new Match("TEST" , date , 2);
//            match.add(match1);


            Article article1 = new Article();
            article.modify(new Article("aa","osss","test" , 1));
            article.getall();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }}



//
//        try {
//            ps.modifier(new Joueur("czzzzb", 5, 6,8));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            System.out.println(ps.afficher());
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//
//    }

