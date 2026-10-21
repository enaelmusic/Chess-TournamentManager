package com.example.swisstournament2.Model;

import java.util.Objects;

public class PlayerTournois {
    private int id;
    private int idPlayer;
    private int idTournois;
    private int status;


    public int getId() {
        return this.id;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getIdTournois() {
        return idTournois;
    }

    public void setIdTournois(int idTournois) {
        this.idTournois = idTournois;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerTournois that = (PlayerTournois) o;
        return id == that.id && idPlayer == that.idPlayer && idTournois == that.idTournois;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPlayer, idTournois);
    }
}
