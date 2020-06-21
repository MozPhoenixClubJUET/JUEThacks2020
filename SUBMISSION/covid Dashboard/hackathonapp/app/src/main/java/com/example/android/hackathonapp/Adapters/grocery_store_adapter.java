package com.example.android.hackathonapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.hackathonapp.R;
import com.example.android.hackathonapp.modals.store;

import java.util.List;

public class grocery_store_adapter extends RecyclerView.Adapter<grocery_store_adapter.ImageViewHolder> {

    private List<store> mData;
    private onClickHandler mOnClickHandler;

    public interface onClickHandler{
        void onClicked(store currentStore);
    }

    public grocery_store_adapter(onClickHandler onClickParam){
        mOnClickHandler = onClickParam;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView storeName, storeAddress, storeDistance;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.store_name);
            storeAddress = itemView.findViewById(R.id.store_address);
            storeDistance = itemView.findViewById(R.id.store_distance);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            store currentStore = mData.get(getAdapterPosition());
            mOnClickHandler.onClicked(currentStore);
        }

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int grid_item = R.layout.grocery_store_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(grid_item, parent, shouldAttach);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.storeName.setText(mData.get(position).getName());
        holder.storeAddress.setText(mData.get(position).getAddress());
        holder.storeDistance.setText(mData.get(position).getDistance() + " meters");
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }else {
            return mData.size();
        }

    }

    public void setStoreData(List<store> param){
        mData = param;
        notifyDataSetChanged();
    }

}
