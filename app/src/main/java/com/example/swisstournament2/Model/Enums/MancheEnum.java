package com.example.swisstournament2.Model.Enums;

public enum MancheEnum {
    ENATTENT(0),
    ENCOURS(1),
    FINI(2),
    ANNULE(3);

    private int tyni;
    private MancheEnum(int i){
        this.tyni=i;
    }

    public int getTyni() {
        return tyni;
    }
}
