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
import com.example.android.hackathonapp.modals.news;
import com.squareup.picasso.Picasso;

import java.util.List;

public class news_adapter extends RecyclerView.Adapter<news_adapter.ImageViewHolder> {

    private List<news> mData;
    private onClickHandler mOnClickHandler;

    public interface onClickHandler{
        void onClicked(news currentNews);
    }

    public news_adapter(onClickHandler onClickParam){
        mOnClickHandler = onClickParam;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView news_headline, news_date;
        ImageView news_image;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            news_date = itemView.findViewById(R.id.news_date);
            news_headline = itemView.findViewById(R.id.news_headline);
            news_image = itemView.findViewById(R.id.news_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            news currentNews = mData.get(getAdapterPosition());
            mOnClickHandler.onClicked(currentNews);
        }

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int grid_item = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(grid_item, parent, shouldAttach);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull news_adapter.ImageViewHolder holder, int position) {
        holder.news_headline.setText(mData.get(position).getNews_headline());
        holder.news_date.setText(mData.get(position).getNews_date());
        Picasso.get().load(mData.get(position).getNews_image()).into(holder.news_image);
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }else {
            return mData.size();
        }

    }

    public void setNewsData(List<news> param){
        mData = param;
        notifyDataSetChanged();
    }

}
