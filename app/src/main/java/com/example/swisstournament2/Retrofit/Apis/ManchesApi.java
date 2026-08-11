package com.example.swisstournament2.Retrofit.Apis;

import com.example.swisstournament2.Model.MatchSwiss;

import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ManchesApi {
    @POST("/nextround")
    Call<TreeSet<MatchSwiss>> nextManche(@Body TreeSet<MatchSwiss> matches);
}
