package test;

import models.Candidat;
import models.Election;
import services.CandidatService;
import services.ElectionService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {


    public static void main(String[] args) throws SQLException {
        ElectionService ps = new ElectionService();
        LocalDate electionDate = LocalDate.of(2022, 7, 12);

        ps.modifier(new Election("nom2", electionDate, "cccc", "cvn","/images/pfp.png"));

       /* try {
            ps.ajouter(new Candidat("hmed", "mhemed", 44));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/

      /*  try {
            ps.modifier(new Election(, "yy", electionDate, "cvn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

       /* try {
            ps.supprimer(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        try {
            System.out.println(ps.recuperer());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
