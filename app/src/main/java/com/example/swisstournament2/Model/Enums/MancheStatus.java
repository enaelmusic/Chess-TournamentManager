package com.example.swisstournament2.Model.Enums;

public enum MancheStatus {
    ENATTENT(0),
    ENCOURS(1),
    FINI(2);

    private int tyni;
    private MancheStatus(int i){
        this.tyni=i;
    }

    public int getTyni() {
        return tyni;
    }
}
