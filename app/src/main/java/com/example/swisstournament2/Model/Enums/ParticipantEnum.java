package com.example.swisstournament2.Model.Enums;

public enum ParticipantEnum {
    PARTICIPE(0),
    PARTICIPEPAS(1),
    PARTICIPEPLUS(2);

    private int tyni;
    private ParticipantEnum(int i){
        this.tyni=i;
    }

    public int getTyni() {
        return tyni;
    }
}
