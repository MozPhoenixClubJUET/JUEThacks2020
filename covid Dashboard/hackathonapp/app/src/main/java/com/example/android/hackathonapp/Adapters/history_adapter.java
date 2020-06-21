package com.example.android.hackathonapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.hackathonapp.R;
import com.example.android.hackathonapp.modals.history;

import java.util.List;

public class history_adapter extends RecyclerView.Adapter<history_adapter.ImageViewHolder> {

    private List<history> mData;

    public history_adapter(){
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        TextView date, totalconfirmed, totalrecovered, totaldeceased;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            totalconfirmed = itemView.findViewById(R.id.totalconfirmed);
            totalrecovered = itemView.findViewById(R.id.totalrecovered);
            totaldeceased = itemView.findViewById(R.id.totaldeceased);
        }

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int grid_item = R.layout.history_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(grid_item, parent, shouldAttach);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        holder.date.setText(mData.get(position).getDate());
        holder.totaldeceased.setText(mData.get(position).getTotaldeceased());
        holder.totalconfirmed.setText(mData.get(position).getTotalconfirmed());
        holder.totalrecovered.setText(mData.get(position).getTotalrecovered());

    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }else {
            return mData.size();
        }

    }

    public void setHistoryData(List<history> param){
        mData = param;
        notifyDataSetChanged();
    }

}
