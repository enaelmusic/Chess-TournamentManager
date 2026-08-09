package com.example.swisstournament2.Retrofit;

import com.example.swisstournament2.Model.ClassementDTO;
import com.example.swisstournament2.Model.ClassementTournois;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClassementApi {

    @GET("/getClassementTournois/{id}")
    Call<ArrayList<ClassementDTO>> getClassementTournois(@Path("id") int id);
}
