package com.example.swisstournament2.Model;


public class ClassementTournois implements Comparable<ClassementTournois> {
    private int id;
    private int idTournois;
    private int idPlayer;
    private int round_gagner;
    private int round_perdu;
    private int round_null;
    private int point;


    @Override
    public int compareTo(ClassementTournois o) {
        return 0;
    }
}
