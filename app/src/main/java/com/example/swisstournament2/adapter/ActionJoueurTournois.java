package com.example.swisstournament2.adapter;

import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerSwissDTO;

public interface ActionJoueurTournois {
    void modifierJoueurT(PlayerSwissDTO player);
    void supprimerPlayerT(PlayerSwissDTO player);
}
