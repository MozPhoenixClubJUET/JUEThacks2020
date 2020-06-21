package com.example.android.hackathonapp.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.android.hackathonapp.Activity.grocery_splash;
import com.example.android.hackathonapp.Activity.grocery_stores;
import com.example.android.hackathonapp.Activity.handwash_splash;
import com.example.android.hackathonapp.R;

public class tools_fragment extends Fragment{

    ImageView handwash, grocery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, container, false);

        grocery = view.findViewById(R.id.grocery);
        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), grocery_splash.class);
                startActivity(intent);
            }
        });

        handwash = view.findViewById(R.id.handwash);
        handwash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), handwash_splash.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
