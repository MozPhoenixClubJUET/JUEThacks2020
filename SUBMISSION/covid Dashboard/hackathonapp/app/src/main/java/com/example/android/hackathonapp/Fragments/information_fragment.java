package com.example.android.hackathonapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.hackathonapp.Activity.grocery_splash;
import com.example.android.hackathonapp.Activity.handwash_splash;
import com.example.android.hackathonapp.Activity.news_splash;
import com.example.android.hackathonapp.Activity.tracker_splash;
import com.example.android.hackathonapp.R;

public class information_fragment extends Fragment {

    ImageView cases, news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        cases = view.findViewById(R.id.cases);
        cases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), tracker_splash.class);
                startActivity(intent);
            }
        });

        news = view.findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), news_splash.class);
                startActivity(intent);
            }
        });

        return view;

    }

}
