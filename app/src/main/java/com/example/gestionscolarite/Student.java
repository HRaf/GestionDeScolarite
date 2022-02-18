package com.example.gestionscolarite;

public class Student {
    private String cne;
    private String nom;
    private String prenom;
    private String idFiliere;
    private String niveau;
    // Empty constructor
    public Student() {
    }
    // Constructor
    public Student(String cne, String nom, String prenom, String idFiliere, String niveau) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.idFiliere = idFiliere;
        this.niveau = niveau;
    }
    //getters


    public String getCne() {
        return cne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getIdFiliere() {
        return idFiliere;
    }

    public String getNiveau() {
        return niveau;
    }

    //setters

    public void setCne(String cne) {
        this.cne = cne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setIdFiliere(String idFiliere) {
        this.idFiliere = idFiliere;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
