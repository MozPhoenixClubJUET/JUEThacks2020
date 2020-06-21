package com.example.covid_19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.CovidApiPojo.Statewise;
import com.example.covid_19.DistrictActivity;
import com.example.covid_19.R;
import com.example.covid_19.RecyclerViewitemClickListner;
import com.example.covid_19.ViewHolder.StatewiseHolder;

import java.util.ArrayList;
import java.util.List;

public class StatewiseAdapter extends RecyclerView.Adapter<StatewiseHolder> {
    List<Statewise> stateDetails = new ArrayList<>();
    Context c;
    private RecyclerViewitemClickListner recyclerViewitemClickListner;

    public StatewiseAdapter(Context c, List<Statewise> stateDetails) {
        this.c = c;
        this.stateDetails = stateDetails;
    }

    public StatewiseAdapter(List<Statewise> stateDetails, RecyclerViewitemClickListner recyclerViewitemClickListner) {
        this.stateDetails = stateDetails;
        this.recyclerViewitemClickListner = recyclerViewitemClickListner;
    }

    @NonNull
    @Override
    public StatewiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        return new StatewiseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatewiseHolder holder, final int position) {


            holder.statename.setText(stateDetails.get(position).getState());
            holder.activecases.setText(stateDetails.get(position).getActive());

            holder.confirmedcases.setText(stateDetails.get(position).getConfirmed());

            holder.recoveredcase.setText(stateDetails.get(position).getRecovered());

            holder.deathcase.setText(stateDetails.get(position).getDeaths());


        holder.state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statename2 = stateDetails.get(position).getState();
                if(stateDetails.get(position).getState().equalsIgnoreCase("Total")) {
                    Toast.makeText(c, "Click on particular state name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(c, DistrictActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, statename2);
                    c.startActivity(intent); // put context infront
                }

              //  Toast.makeText(v.getContext(), String.valueOf(stateDetails.get(position)) ,  Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return stateDetails.size();
    }

    public void filterList(List<Statewise> filteredlist) {
        stateDetails = filteredlist;
        notifyDataSetChanged();
    }
}
