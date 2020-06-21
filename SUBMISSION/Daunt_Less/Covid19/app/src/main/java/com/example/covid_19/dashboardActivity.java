package com.example.covid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.ApiManager.ManageApi;
import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.CovidApiPojo.Tested;

import java.util.List;

public class dashboardActivity extends AppCompatActivity {
    private TextView allcase, confirmedcase, activecase, recoveredcase, deathcase,date_time;
    private ManageApi modelview;
    CardView needhelp,feelingsick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        initView();
        TotalObserverandsetter();

    }

    private void initView() {
        modelview = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ManageApi.class);
        modelview.getStateDetails();
        modelview.getTotalDetails();

        allcase=findViewById(R.id.allcasenumber);
        activecase=findViewById(R.id.activecasenumber);
        recoveredcase=findViewById(R.id.recoveredcasenumber);
        confirmedcase=findViewById(R.id.confirmedcasenumber);
        deathcase=findViewById(R.id.deathcasenumber);
        date_time=findViewById(R.id.date_time);
        needhelp=findViewById(R.id.needHelp);
        needhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dashboardActivity.this, "hiii", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void TotalObserverandsetter() {
        final Observer<List<Statewise>> nameObserver = new Observer<List<Statewise>>() {
            @Override
            public void onChanged(@Nullable final List<Statewise> stateDetails) {
                activecase.setText(stateDetails.get(0).getActive());
                recoveredcase.setText(stateDetails.get(0).getRecovered());
                deathcase.setText(stateDetails.get(0).getDeaths());
                confirmedcase.setText(stateDetails.get(0).getConfirmed());
                date_time.setText(stateDetails.get(0).getLastupdatedtime());

            }
        };
        final Observer<List<Tested>> nameobserverfortotal = new Observer<List<Tested>>() {
            @Override
            public void onChanged(List<Tested> Tested) {

                allcase.setText(Tested.get(Tested.size()-1).getTotalsamplestested());
            }
        };
        modelview.getStateLiveData().observe(this, nameObserver);
        modelview.GetLiveTotal().observe(this,nameobserverfortotal);

    }
    public void talk_to_us(View view){

    }

    public void nearby_hospital(View view){
        Toast.makeText(this, "hii", Toast.LENGTH_SHORT).show();
    }

}
