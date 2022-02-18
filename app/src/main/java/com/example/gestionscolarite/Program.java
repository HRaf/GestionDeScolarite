package com.example.gestionscolarite;

public class Program {
    private String idProgram;
    private String nameProgram;

    public Program() {
    }
    public Program( String nameProgram) {
        this.nameProgram = nameProgram;
    }
    public Program(String idProgram, String nameProgram) {
        this.idProgram = idProgram;
        this.nameProgram = nameProgram;
    }

    public String getIdProgram() {
        return idProgram;
    }

    public String getNameProgram() {
        return nameProgram;
    }


    //setters

    public void setIdProgram(String idProgram) {
        this.idProgram = idProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }
}
