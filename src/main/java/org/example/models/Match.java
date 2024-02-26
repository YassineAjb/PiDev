package org.example.models;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

public class Match {
    private int idMatch;
    private String AdversaireMatch ;
    private Date DateMatch;
    private int ScoreMatch;

    public Match(int idMatch, String adversaireMatch, Date dateMatch, int scoreMatch) {
        this.idMatch = idMatch;
        this.AdversaireMatch = adversaireMatch;
        this.DateMatch = dateMatch;
        this.ScoreMatch = scoreMatch;
    }

    public Match(String adversaireMatch, Date dateMatch, int scoreMatch) {
        this.AdversaireMatch = adversaireMatch;
        this.DateMatch = dateMatch;
        this.ScoreMatch = scoreMatch;
        
    }

    public Match() {

    }

    public Match(TextField adversaire, DatePicker date, TextField score) {
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public String getAdversaireMatch() {
        return AdversaireMatch;
    }

    public void setAdversaireMatch(String adversaireMatch) {
        AdversaireMatch = adversaireMatch;
    }

    public Date getDateMatch() {
        return DateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        DateMatch = dateMatch;
    }

    public int getScoreMatch() {
        return ScoreMatch;
    }

    public void setScoreMatch(int scoreMatch) {
        ScoreMatch = scoreMatch;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", AdversaireMatch='" + AdversaireMatch + '\'' +
                ", DateMatch=" + DateMatch +
                ", ScoreMatch=" + ScoreMatch +
                '}';
    }


}
