package com.example.covid_19.RetrofitClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    public static Retrofit getRetroCient(String baseURl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


}
