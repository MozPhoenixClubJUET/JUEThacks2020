package com.example.covid_19.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.RecyclerViewitemClickListner;

public class StatewiseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView activecases;
    public TextView confirmedcases;
    public TextView deathcase;
    public TextView recoveredcase;
    public TextView statename;
     public RelativeLayout state;
     RecyclerViewitemClickListner recyclerViewitemClickListner;

    public StatewiseHolder(@NonNull View itemView) {
        super(itemView);
        this.state=itemView.findViewById(R.id.state_cardview);
        this.statename=itemView.findViewById(R.id.state_name);

        this.activecases=itemView.findViewById(R.id.activecasenumber);
        this.confirmedcases=itemView.findViewById(R.id.confirmedcasenumber);
        this.recoveredcase=itemView.findViewById(R.id.recoveredcasenumber);
        this.deathcase=itemView.findViewById(R.id.deathcasenumber);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.recyclerViewitemClickListner.onItemClick(v,getLayoutPosition());


    }


}
