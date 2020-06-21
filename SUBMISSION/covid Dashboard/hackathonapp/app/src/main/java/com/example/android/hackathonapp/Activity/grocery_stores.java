package com.example.android.hackathonapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hackathonapp.Adapters.grocery_store_adapter;
import com.example.android.hackathonapp.NetworkUtils.news_networktask;
import com.example.android.hackathonapp.NetworkUtils.store_networktask;
import com.example.android.hackathonapp.R;
import com.example.android.hackathonapp.NetworkUtils.store_JsonParse;
import com.example.android.hackathonapp.modals.store;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class grocery_stores extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<store>>, grocery_store_adapter.onClickHandler {

    private RecyclerView rv_grocery;
    private grocery_store_adapter mGroceryAdapter;
    private TextView tv_error;
    private ProgressBar mProgressBar;
    int loaderId = 22;
    SharedPreferences prefs = null;
    String lati = "";
    String lon = "";
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_stores);

        loadingLocation();

        search = findViewById(R.id.search);
        tv_error = findViewById(R.id.tv_error_message_display);
        mProgressBar = findViewById(R.id.pb_loading_indicator);
        rv_grocery = findViewById(R.id.rv_grocery);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        search.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_grocery.setLayoutManager(linearLayoutManager);
        rv_grocery.setHasFixedSize(true);

        mGroceryAdapter = new grocery_store_adapter(this);
        rv_grocery.setAdapter(mGroceryAdapter);

        getSupportLoaderManager().initLoader(loaderId, null, this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setVisibility(View.INVISIBLE);
                loadingGrocery();
            }
        });


    }

    private void loadingGrocery() {
        rv_grocery.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.INVISIBLE);

        String apiKey = "MtwNKGlgxU339MjAi00-Qzz0i6WWqEaPWHL2vdrlZs4";
        String urlc =
                "https://discover.search.hereapi.com/v1/discover?at="+lati+","+lon+"&q=general+store&apiKey="+apiKey;

        Bundle bundle = new Bundle();
        bundle.putString("url", urlc);

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<store> storeSearchLoader = loaderManager.getLoader(loaderId);
        if (storeSearchLoader == null) {
            loaderManager.initLoader(loaderId, bundle, this);
        } else {
            loaderManager.restartLoader(loaderId, bundle, this);
        }
    }

    @NonNull
    @Override
    public Loader<List<store>> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<List<store>>(getApplicationContext()) {
            List<store> mGroceryData = null;

            @Override
            protected void onStartLoading() {
                if (mGroceryData != null) {
                    deliverResult(mGroceryData);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List<store> loadInBackground() {
                try {
                    String jsonResponse = news_networktask.getJsonResponse(args.getString("url"));
                    return store_JsonParse.parse(jsonResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(List<store> data) {
                mGroceryData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<store>> loader, List<store> data) {
        mProgressBar.setVisibility(View.INVISIBLE);

        if (data != null) {
            mGroceryAdapter.setStoreData(data);
            rv_grocery.setVisibility(View.VISIBLE);
            tv_error.setVisibility(View.INVISIBLE);
        }else{
            rv_grocery.setVisibility(View.INVISIBLE);
            tv_error.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<store>> loader) {

    }

    @Override
    public void onClicked(store currentStore) {
        Uri mapUri = Uri.parse("geo:0,0?q="+currentStore.getLatitude()+","+currentStore.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 && grantResults.length > 0){
            getCurrentLocation();
        }else{
            Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadingLocation(){
        if(ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    grocery_stores.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1
            );
        }else{
            getCurrentLocation();
        }
    }

    private void getCurrentLocation(){
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(grocery_stores.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(grocery_stores.this)
                                .removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0){
                            int index = locationResult.getLocations().size()-1;
                            double latitude = locationResult.getLocations().get(index).getLatitude();
                            double longitude = locationResult.getLocations().get(index).getLongitude();
                            lati = String.valueOf(latitude);
                            lon = String.valueOf(longitude);
                            search.setVisibility(View.VISIBLE);
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }, Looper.getMainLooper());
    }

}
