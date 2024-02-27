package org.example.models;

import java.sql.Date;

public class Contrat {
    private int id;
    private int id_Employe;
    private Date date_debut;
    private Date date_fin;
    private int salaire;

    public Contrat() {
    }

    public Contrat(int id_Employe, Date date_debut, Date date_fin, int salaire) {
        this.id_Employe = id_Employe;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.salaire = salaire;
    }

    public Contrat(int id, int id_Employe, Date date_debut, Date date_fin, int salaire) {
        this.id = id;
        this.id_Employe = id_Employe;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Employe() {
        return id_Employe;
    }

    public void setId_Employe(int id_Employe) {
        this.id_Employe = id_Employe;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", id_Employe=" + id_Employe +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", salaire=" + salaire +
                '}';
    }
}
