package com.example.swisstournament2.Retrofit;

import com.example.swisstournament2.Model.PlayerSwiss;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PlayerApi {
    @POST("/sauverPlayerSwiss")
    Call<PlayerSwiss> sauverJoueur(@Body PlayerSwiss playerSwiss);
}
