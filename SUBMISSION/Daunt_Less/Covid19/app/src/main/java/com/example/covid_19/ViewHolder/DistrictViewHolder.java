package com.example.covid_19.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;

public class DistrictViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView districtname;
   public TextView confirmedcase;
    public DistrictViewHolder(@NonNull View itemView) {
        super(itemView);
        this.districtname=itemView.findViewById(R.id.district_name);
        this.confirmedcase=itemView.findViewById(R.id.confirmed_cases);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
