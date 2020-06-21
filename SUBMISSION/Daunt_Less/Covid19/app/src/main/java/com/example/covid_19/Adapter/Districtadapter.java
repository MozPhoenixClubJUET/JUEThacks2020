package com.example.covid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.District.DistrictDatum;
import com.example.covid_19.R;
import com.example.covid_19.ViewHolder.DistrictViewHolder;
import com.example.covid_19.ViewHolder.StatewiseHolder;

import java.util.ArrayList;
import java.util.List;

public class Districtadapter extends RecyclerView.Adapter<DistrictViewHolder> {
    List<DistrictDatum> districtDetails = new ArrayList<>();
    Context c;

    public Districtadapter(List<DistrictDatum> districtDetails, Context c) {
        this.districtDetails = districtDetails;
        this.c = c;
    }

    @NonNull
    @Override
    public DistrictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_district, null);
        return new DistrictViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictViewHolder holder, int position) {
        DistrictDatum districtDatum = districtDetails.get(position);
        holder.districtname.setText(districtDatum.getDistrict());
        holder.confirmedcase.setText(String.valueOf(districtDatum.getActive()));
    }

    @Override
    public int getItemCount() {
        return districtDetails.size();

    }
}
