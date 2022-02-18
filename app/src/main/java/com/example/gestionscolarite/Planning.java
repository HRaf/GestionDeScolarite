package com.example.gestionscolarite;

public class Planning {
    private int idModule;
    private String nameModule;
    private String idProgram;
    private String niveau;

    public Planning() {
    }

    public Planning(int idModule, String nameModule, String idProgram, String niveau) {
        this.idModule = idModule;
        this.nameModule = nameModule;
        this.idProgram = idProgram;
        this.niveau = niveau;
    }
    public Planning(String nameModule, String idProgram, String niveau) {
        this.nameModule = nameModule;
        this.idProgram = idProgram;
        this.niveau = niveau;
    }
    //getters
    public int getIdModule() {
        return idModule;
    }

    public String getNameModule() {
        return nameModule;
    }

    public String getIdProgram() {
        return idProgram;
    }

    public String getNiveau() {
        return niveau;
    }
    //setters

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public void setIdProgram(String idProgram) {
        this.idProgram = idProgram;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
