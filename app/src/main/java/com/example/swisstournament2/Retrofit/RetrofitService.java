package com.example.swisstournament2.Retrofit;
import com.example.swisstournament2.BuildConfig;
import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit retrofit;
    private RetrofitService(){
    }

    /**
     * FOR PARAM BuildConfig.BASE_URL see app/src/build.gradle.kts comment BASE URL
     */
    public static Retrofit getRetrofit() {
        if(retrofit==null){
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        }
        return retrofit;
    }
}