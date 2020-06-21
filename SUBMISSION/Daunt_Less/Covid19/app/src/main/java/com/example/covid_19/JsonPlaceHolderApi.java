package com.example.covid_19;

import com.example.covid_19.CovidApiPojo.State;
import com.example.covid_19.District.DistrictDatum;
import com.example.covid_19.District.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("data.json")
    Call<State> getstatedetails();
    @GET("data.json")
    Call<State> getTotaltested();
    @GET("v2/state_district_wise.json")
    Call<List<Example>> getdistricts();
}
