package com.example.swisstournament2.Model;

import java.util.TreeSet;

public class Tournois {

    private int id;
    private String name;
    private String cadence;
    private int statusTournois;

    public Tournois(){
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCadence() {
        return cadence;
    }

    public int getStatusTournois() {
        return statusTournois;
    }

    public void setStatusTournois(int statusTournois) {
        this.statusTournois = statusTournois;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCadence(String cadence) {
        this.cadence = cadence;
    }


}
