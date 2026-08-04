package com.example.swisstournament2.Retrofit;

import com.example.swisstournament2.Model.PlayerSwiss;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlayerApi {
    @POST("/sauverPlayerSwiss")
    Call<PlayerSwiss> sauverJoueur(@Body PlayerSwiss playerSwiss);

    @POST("/getNamePlayer/{id}")
    Call<String> getNamePlayer(@Path("id") int id);
}
