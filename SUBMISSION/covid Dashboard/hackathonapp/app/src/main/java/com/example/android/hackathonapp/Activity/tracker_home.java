package com.example.android.hackathonapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.hackathonapp.NetworkUtils.info_JsonParse;
import com.example.android.hackathonapp.NetworkUtils.store_JsonParse;
import com.example.android.hackathonapp.NetworkUtils.store_networktask;
import com.example.android.hackathonapp.R;
import com.example.android.hackathonapp.modals.store;

import org.json.JSONObject;

import java.util.List;

public class tracker_home extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String[]> {

    ProgressBar mProgressBar;
    TextView changes_confirmed, total_confirmed, changes_active, total_active,
            changes_recovered, total_recovered, changes_deceased, total_deceased;
    LinearLayout counter_cases, cases;
    String[] info = null;
    int loaderId = 26;
    ImageView refresh;

    CardView past_data, state_wise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_home);

        mProgressBar = findViewById(R.id.pb_loading_indicator);
        changes_confirmed = findViewById(R.id.changes_confirmed);
        total_confirmed = findViewById(R.id.total_confirmed);
        changes_active = findViewById(R.id.changes_active);
        total_active = findViewById(R.id.total_active);
        changes_recovered = findViewById(R.id.changes_recovered);
        total_recovered = findViewById(R.id.total_recovered);
        changes_deceased = findViewById(R.id.changes_deceased);
        total_deceased = findViewById(R.id.total_deceased);

        counter_cases = findViewById(R.id.counter_cases);

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingInfo();
            }
        });

        past_data = findViewById(R.id.past_data_tab);
        past_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tracker_home.this, history_home.class));
            }
        });

        state_wise = findViewById(R.id.state_result_tab);
        state_wise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tracker_home.this, state_wise_home.class));
            }
        });

        cases = findViewById(R.id.cases);
        cases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tracker_home.this, history_home.class));
            }
        });

        getSupportLoaderManager().initLoader(loaderId, null, this);

        loadingInfo();

    }

    private void loadingInfo() {
        counter_cases.setVisibility(View.INVISIBLE);

        String urlc =
                "https://api.covid19india.org/data.json";

        Bundle bundle = new Bundle();
        bundle.putString("url", urlc);

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String[]> infoSearchLoader = loaderManager.getLoader(loaderId);
        if (infoSearchLoader == null) {
            loaderManager.initLoader(loaderId, bundle, this);
        } else {
            loaderManager.restartLoader(loaderId, bundle, this);
        }
    }

    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<String[]>(getApplicationContext()) {
            String[] mInfo = null;

            @Override
            protected void onStartLoading() {
                if (mInfo != null) {
                    deliverResult(mInfo);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String[] loadInBackground() {
                try {
                    JSONObject jsonResponse = store_networktask.getJsonResponse(args.getString("url"));
                    return info_JsonParse.parse(jsonResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(String[] data) {
                mInfo = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {
        mProgressBar.setVisibility(View.INVISIBLE);

        if (data != null) {
            info = data;
            setdata();
            counter_cases.setVisibility(View.VISIBLE);
        }else{
            setdata2();
            counter_cases.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {

    }

    private void setdata(){
        changes_confirmed.setText("+" + info[0]);
        total_confirmed.setText(info[1]);
        changes_active.setText(info[2]);
        total_active.setText(info[3]);
        changes_recovered.setText("+" + info[4]);
        total_recovered.setText(info[5]);
        changes_deceased.setText("+" + info[6]);
        total_deceased.setText(info[7]);
    }

    private void setdata2(){
        changes_confirmed.setText("NA");
        total_confirmed.setText("NA");
        changes_active.setText("NA");
        total_active.setText("NA");
        changes_recovered.setText("NA");
        total_recovered.setText("NA");
        changes_deceased.setText("NA");
        total_deceased.setText("NA");
    }

}
