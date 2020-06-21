package com.example.covid_19.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.covid_19.ApiManager.ManageApi;
import com.example.covid_19.CovidApiPojo.CasesTimeSeries;
import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.CovidApiPojo.Tested;
import com.example.covid_19.DietActivity;
import com.example.covid_19.R;
import com.example.covid_19.talk_to_us;
import com.example.covid_19.webActivity;
import com.example.covid_19.whoActivity;

import java.util.List;


public class DashboardFragment extends Fragment {
    private TextView allcase, confirmedcase, activecase, recoveredcase, deathcase,date_time;
    private ManageApi modelview;
    Button talk_to_us,nearby_hospital,who_ins,diet_btn,website;


    public DashboardFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(mainView);
        TotalObserverandsetter();
        return mainView;
    }

    private void initView(final ViewGroup mainView) {
        modelview = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ManageApi.class);
        modelview.getStateDetails();
        modelview.getTotalDetails();

        allcase=mainView.findViewById(R.id.allcasenumber);
        talk_to_us=mainView.findViewById(R.id.talk_to_us);
        nearby_hospital=mainView.findViewById(R.id.get_nearby_hospiatl);
        who_ins=mainView.findViewById(R.id.who_ins_btn);
        diet_btn=mainView.findViewById(R.id.diet_btn);
        website=mainView.findViewById(R.id.website_btn);

        activecase=mainView.findViewById(R.id.activecasenumber);
        recoveredcase=mainView.findViewById(R.id.recoveredcasenumber);
        confirmedcase=mainView.findViewById(R.id.confirmedcasenumber);
        deathcase=mainView.findViewById(R.id.deathcasenumber);
        date_time=mainView.findViewById(R.id.date_time);

        talk_to_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(2)
                        .pivotY(15f).playOn(talk_to_us);
                Intent intent= new Intent(getContext(), talk_to_us.class);
                startActivity(intent);

            }
        });

        nearby_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(2)
                        .pivotY(15f)
                        .playOn(nearby_hospital);
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        who_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(2).pivotY(15f)
                        .playOn(who_ins);
                Intent intent= new Intent(getContext(), whoActivity.class);
                startActivity(intent);

            }
        });

        diet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(2).pivotY(15f)
                        .playOn(diet_btn);
                Intent intent= new Intent(getContext(), DietActivity.class);
                startActivity(intent);
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(2).pivotY(15f)
                        .playOn(website);
                Intent intent= new Intent(getContext(), webActivity.class);
                startActivity(intent);
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


}
