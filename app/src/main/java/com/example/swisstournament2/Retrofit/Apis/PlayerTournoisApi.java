package com.example.swisstournament2.Retrofit.Apis;

import com.example.swisstournament2.Model.PlayerSwissDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlayerTournoisApi {
    @POST("/modifierParticipant")
    Call<Void> modifierParticipant( @Body PlayerSwissDTO player);

    @POST("/supprimerParticipant{idPlayer}")
    Call<Void> supprimerParticipant(@Path("idPlayer") int idPlayer);

}
