package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.ApiManager.ManageApi;
import com.example.covid_19.CovidApiPojo.State;
import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.RetrofitClient.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    List<Statewise> stateDetails = new ArrayList<>();
    private ManageApi modelview;
    TextView active_text,confirmed_text,decresed_text,recovered_text,date_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        statewiseObserverAndDataSetter();

    }

    private void initView() {
        spinner = findViewById(R.id.spinner);
        modelview = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ManageApi.class);
        modelview.getStateDetails();
        active_text=findViewById(R.id.text_active);
        confirmed_text=findViewById(R.id.text_confirmed);
        recovered_text=findViewById(R.id.text_recovered);
        decresed_text=findViewById(R.id.text_decresed);
        date_text=findViewById(R.id.date);

    }

    private void setSpinnerData(final List<Statewise> statewises) {
        List<String> stringList = new ArrayList<>();
        for(int i=0;i<statewises.size();i++)
        {
            stringList.add(statewises.get(i).getState());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for(int i=0;i<statewises.size();i++)
                {
                    if(parent.getItemAtPosition(position).toString().equalsIgnoreCase(statewises.get(i).getState()))
                    {
                        date_text.setText(statewises.get(i).getLastupdatedtime());
                        active_text.setText(statewises.get(i).getActive());
                        recovered_text.setText(statewises.get(i).getRecovered());
                        decresed_text.setText(statewises.get(i).getDeaths());
                        confirmed_text.setText(statewises.get(i).getConfirmed());
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    private void statewiseObserverAndDataSetter() {
        final Observer<List<Statewise>> nameObserver = new Observer<List<Statewise>>() {
            @Override
            public void onChanged(@Nullable final List<Statewise> stateDetails) {
                setSpinnerData(stateDetails);
            }
        };
        modelview.getStateLiveData().observe(this, nameObserver);

    }

}
