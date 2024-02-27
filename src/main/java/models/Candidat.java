package models;
public class Candidat {
    private int idC;
    private String nomC;
    private String prenomC;
    private int age;

    public Candidat() {
    }

    public Candidat(int idC, String nomC, String prenomC, int age) {
        this.idC = idC;
        this.nomC = nomC;
        this.prenomC = prenomC;
        this.age = age;
    }

    public Candidat(String nomC, String prenomC, int age) {
        this.nomC = nomC;
        this.prenomC = prenomC;
        this.age = age;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getPrenomC() {
        return prenomC;
    }

    public void setPrenomC(String prenomC) {
        this.prenomC = prenomC;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Candidat{" +
                "idC=" + idC +
                ", nomC='" + nomC + '\'' +
                ", prenomC='" + prenomC + '\'' +
                ", age=" + age +
                '}';
    }
}
