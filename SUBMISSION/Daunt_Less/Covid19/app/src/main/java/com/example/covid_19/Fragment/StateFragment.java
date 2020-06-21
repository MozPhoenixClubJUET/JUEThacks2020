package com.example.covid_19.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.covid_19.ApiManager.ManageApi;
import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.R;
import com.example.covid_19.RecyclerViewitemClickListner;
import com.example.covid_19.Adapter.StatewiseAdapter;

import java.util.ArrayList;
import java.util.List;


public class StateFragment extends Fragment implements RecyclerViewitemClickListner {
    RecyclerView mrecyclerview;
    StatewiseAdapter statewiseAdapter;
    EditText search;



    private ManageApi modelview;


    public StateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_state, container, false);
        initViews(viewGroup);
        TotalObserverandsetter();
        return viewGroup;
    }

    private void initViews(ViewGroup viewGroup) {


        mrecyclerview = (RecyclerView)viewGroup.findViewById(R.id.my_recycler_view);
        search = viewGroup.findViewById(R.id.search_state_box);
        modelview = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ManageApi.class);
        modelview.getStateDetails();

    }

    private void TotalObserverandsetter() {

        final Observer<List<Statewise>> nameObserver = new Observer<List<Statewise>>() {
            @Override
            public void onChanged(@Nullable final List<Statewise> stateDetails) {
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mrecyclerview.setLayoutManager(layoutManager);
                statewiseAdapter = new StatewiseAdapter(getContext(),stateDetails);
                mrecyclerview.setAdapter(statewiseAdapter);
                searchfunctionality(stateDetails);


            }
        };

        modelview.getStateLiveData().observe(this, nameObserver);


    }

    private void searchfunctionality(final List<Statewise> stateDetails) {

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filterList(s.toString(),stateDetails);
            }
        });

    }

    private void filterList(String text, List<Statewise> stateDetails) {
        List<Statewise> filteredlist = new ArrayList<>();
        List<Statewise> statenameList = new ArrayList<>();
        statenameList.addAll(stateDetails);
        for (Statewise state : statenameList) {
            if (state.getState().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(state);
            }

        }
        statewiseAdapter.filterList(filteredlist);

    }





    @Override
    public void onItemClick(View v, int position) {

    }

    @Override
    public void onLongItemClick(View v, int position) {

    }
}

