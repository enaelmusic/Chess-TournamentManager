package com.example.swisstournament2.Model;

public class ClassementDTO {
    private int idPlayer;
    private String nom;
    private int point;

    public ClassementDTO(int idPlayer, String nom, int point){
        this.idPlayer=idPlayer;
        this.nom=nom;
        this.point=point;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public String getNom() {
        return nom;
    }

    public int getPoint() {
        return point;
    }
}
