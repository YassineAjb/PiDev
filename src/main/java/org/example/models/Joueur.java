package org.example.models;

public class Joueur {
    private int id;
    private String Position;
    private int Hauteur;
    private int Poids;
    private String Piedfort;
    private String Nom;
    private String Prenom;
    private int Age;
    private String imagePath;

    public Joueur() {
    }

    public Joueur(int id, String position, int hauteur, int poids, String piedfort, String nom, String prenom, int Age) {
        this.id = id;
        Position = position;
        Hauteur = hauteur;
        Poids = poids;
        Piedfort = piedfort;
        Nom = nom;
        Prenom = prenom;
        this.Age = Age;
    }

    public Joueur(String position, int hauteur, int poids, String piedfort, String nom, String prenom, int Age) {
        Position = position;
        Hauteur = hauteur;
        Poids = poids;
        Piedfort = piedfort;
        Nom = nom;
        Prenom = prenom;
        this.Age = Age;
    }
    public Joueur(String position, int hauteur, int poids, String piedfort, String nom, String prenom, int Age,String imagePath) {
        Position = position;
        Hauteur = hauteur;
        Poids = poids;
        Piedfort = piedfort;
        Nom = nom;
        Prenom = prenom;
        this.Age = Age;
        this.imagePath = imagePath;
    }
    public Joueur(int id, String position, int hauteur, int poids, String piedfort, String nom, String prenom, int Age,String imagePath) {
        this.id = id;
        Position = position;
        Hauteur = hauteur;
        Poids = poids;
        Piedfort = piedfort;
        Nom = nom;
        Prenom = prenom;
        this.Age = Age;
        this.imagePath = imagePath;
    }


    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getHauteur() {
        return Hauteur;
    }

    public void setHauteur(int hauteur) {
        Hauteur = hauteur;
    }

    public int getPoids() {
        return Poids;
    }

    public void setPoids(int poids) {
        Poids = poids;
    }

    public String getPiedfort() {
        return Piedfort;
    }

    public void setPiedfort(String piedfort) {
        Piedfort = piedfort;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", Position='" + Position + '\'' +
                ", Hauteur=" + Hauteur +
                ", Poids=" + Poids +
                ", Piedfort=" + Piedfort +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Age=" + Age +
                '}';
    }
}
