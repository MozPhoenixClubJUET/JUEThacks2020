package com.example.covid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.Adapter.Districtadapter;
import com.example.covid_19.Adapter.StatewiseAdapter;
import com.example.covid_19.ApiManager.ManageApi;
import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.District.DistrictDatum;

import java.util.List;

public class DistrictActivity extends AppCompatActivity {
    TextView statename;
    Districtadapter districtadapter;
    RecyclerView district_recycler_view;
    private ManageApi modelview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        statename = findViewById(R.id.statename2);
        district_recycler_view=findViewById(R.id.district_recycler_view);
        final String name = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        statename.setText(name +"'s districts");
        modelview = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ManageApi.class);
        modelview.getDistricWiseData(this , name);
        TotalObserverandsetter();
    }

    private void TotalObserverandsetter() {

        final Observer<List<DistrictDatum>> nameObserver = new Observer<List<DistrictDatum>>() {
            @Override
            public void onChanged(@Nullable final List<DistrictDatum> districtDetails) {
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                district_recycler_view.setLayoutManager(layoutManager);
                districtadapter = new Districtadapter(districtDetails,DistrictActivity.this);
                district_recycler_view.setAdapter(districtadapter);



            }
        };

        modelview.getDistrictLiveData().observe(this, nameObserver);


    }
}
