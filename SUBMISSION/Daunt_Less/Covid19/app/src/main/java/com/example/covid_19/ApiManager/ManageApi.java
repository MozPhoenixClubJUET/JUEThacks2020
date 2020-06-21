package com.example.covid_19.ApiManager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19.CovidApiPojo.CasesTimeSeries;
import com.example.covid_19.CovidApiPojo.State;
import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.CovidApiPojo.Tested;
import com.example.covid_19.District.DistrictDatum;
import com.example.covid_19.District.Example;
import com.example.covid_19.JsonPlaceHolderApi;
import com.example.covid_19.RetrofitClient.RetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageApi extends ViewModel {

    private MutableLiveData<List<Statewise>> stateLiveData;
    private MutableLiveData<List<Tested>> getLiveTotal;
    private MutableLiveData<List<DistrictDatum>> getDistrictLiveData;


    public void getStateDetails() {
        final JsonPlaceHolderApi jsonPlaceHolderApi = RetroClient.getRetroCient("https://api.covid19india.org/").create(JsonPlaceHolderApi.class);
        final Call<State> stateDetailsCall = jsonPlaceHolderApi.getstatedetails();

        stateDetailsCall.enqueue(new Callback<State>() {
            @Override
            public void onResponse(@NonNull Call<State> call, @NonNull Response<State> response) {
                assert response.body() != null;
                //stateDetails = response.body().getStatewise();
                stateLiveData.postValue(response.body().getStatewise());
                // getLiveTotal.postValue(response.body().getTested());


            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {
            }
        });
    }

    public void getDistricWiseData(final Context context, final String statename) {
        final JsonPlaceHolderApi jsonPlaceHolderApi = RetroClient.getRetroCient("https://api.covid19india.org/").create(JsonPlaceHolderApi.class);
        final Call<List<Example>> districtdatacall = jsonPlaceHolderApi.getdistricts();

        districtdatacall.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(@NonNull Call<List<Example>> call, @NonNull Response<List<Example>> response) {
                assert response.body() != null;

                List<Example> examples = response.body();
                for (int i = 0; i < examples.size(); i++) {
                    if (examples.get(i).getState().equalsIgnoreCase(statename)){
                        getDistrictLiveData.postValue(examples.get(i).getDistrictData());
                    }
                }
               // Toast.makeText(context, String.valueOf(examples.size()), Toast.LENGTH_SHORT).show();


                //getDistrictLiveData.postValue(response.body().get(po));
                //Log.e("getDistrict",response.body().getDistrictData().toString());
                // getLiveTotal.postValue(response.body().getTested());


            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }


        });
    }

    public void getTotalDetails() {
        final JsonPlaceHolderApi jsonPlaceHolderApi = RetroClient.getRetroCient("https://api.covid19india.org/").create(JsonPlaceHolderApi.class);

        final Call<State> getTotalcall = jsonPlaceHolderApi.getTotaltested();
        getTotalcall.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {

                //stateDetails = response.body().getStatewise();
                // stateLiveData.postValue(response.body().getStatewise());
                if (response.body() == null) {
                    return;
                } else {
                    getLiveTotal.postValue(response.body().getTested());
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }


    public MutableLiveData<List<Statewise>> getStateLiveData() {
        if (stateLiveData == null) {
            stateLiveData = new MutableLiveData<List<Statewise>>();
        }
        return stateLiveData;
    }

    public MutableLiveData<List<Tested>> GetLiveTotal() {
        if (getLiveTotal == null) {
            getLiveTotal = new MutableLiveData<List<Tested>>();
        }
        return getLiveTotal;
    }

    public MutableLiveData<List<DistrictDatum>> getDistrictLiveData() {
        if (getDistrictLiveData == null) {
            getDistrictLiveData = new MutableLiveData<List<DistrictDatum>>();
        }
        return getDistrictLiveData;
    }
}
