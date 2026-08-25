package com.example.swisstournament2.Retrofit.Apis;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlayerTournoisApi {
    @POST("/modifierParticipant{id}")
    Call<String> modifierParticipant(@Path("id") String id);

    @POST("/supprimerParticipant{idPlayer}")
    Call<Void> supprimerParticipant(@Path("idPlayer")int idPlayer);

}
