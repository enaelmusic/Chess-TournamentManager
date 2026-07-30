package com.example.swisstournament2.Model;

public class PlayerTournois {
    private int idPlayer;
    private int idTournois;
    public PlayerTournois(int tournois, int player){
        this.idTournois=tournois;
        this.idPlayer=player;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public int getIdTournois() {
        return idTournois;
    }
}
