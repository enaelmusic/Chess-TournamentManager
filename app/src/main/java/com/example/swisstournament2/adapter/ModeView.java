package com.example.swisstournament2.adapter;

public enum ModeView {
    INSCRIPTION(0),
    TOURNOISENCOUR(1);

    private int tyni;
    private ModeView(int i){
        this.tyni=i;
    }

    public int getTyni() {
        return tyni;
    }
}
