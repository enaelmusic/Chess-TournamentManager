package com.example.swisstournament2.Model;

import java.util.Objects;

public class PlayerSwiss implements Comparable<PlayerSwiss> {
    private int id;
    private String nom,blitzRating,bulletRating,rapidRatting,stdRating;
    private int numFide;


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBlitzRating() {
        return blitzRating;
    }

    public void setBlitzRating(String blitzRating) {
        this.blitzRating = blitzRating;
    }

    public String getBulletRating() {
        return bulletRating;
    }

    public void setBulletRating(String bulletRating) {
        this.bulletRating = bulletRating;
    }

    public String getRapidRatting() {
        return rapidRatting;
    }

    public void setRapidRatting(String rapidRatting) {
        this.rapidRatting = rapidRatting;
    }

    public String getStdRating() {
        return stdRating;
    }

    public void setStdRating(String stdRating) {
        this.stdRating = stdRating;
    }

    public int getNumFide() {
        return numFide;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSwiss that = (PlayerSwiss) o;
        return id == that.id && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    @Override
    public int compareTo(PlayerSwiss o) {
        if(o.getNom().equals(this.nom)){
            return o.getId()-this.getId();
        }
        else{
            return this.getNom().compareTo(o.getNom());
        }
    }
}
