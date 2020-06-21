package com.example.android.hackathonapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.hackathonapp.Adapters.history_adapter;
import com.example.android.hackathonapp.NetworkUtils.history_jsonParse;
import com.example.android.hackathonapp.NetworkUtils.state_jsonParse;
import com.example.android.hackathonapp.NetworkUtils.store_networktask;
import com.example.android.hackathonapp.R;
import com.example.android.hackathonapp.modals.history;

import org.json.JSONObject;

import java.util.List;

public class state_wise_home extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<history>> {

    private RecyclerView rv_history;
    private history_adapter mHistoryAdapter;
    private TextView tv_error;
    private ProgressBar mProgressBar;
    int loaderId = 33;
    private ImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_wise_home);

        tv_error = findViewById(R.id.tv_error_message_display);
        mProgressBar = findViewById(R.id.pb_loading_indicator);
        rv_history = findViewById(R.id.rv_history);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_history.setLayoutManager(linearLayoutManager);
        rv_history.setHasFixedSize(true);
        mHistoryAdapter = new history_adapter();
        rv_history.setAdapter(mHistoryAdapter);

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingHistory();
            }
        });

        getSupportLoaderManager().initLoader(loaderId, null, this);
        loadingHistory();

    }

    private void loadingHistory() {
        rv_history.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.INVISIBLE);

        String urlc =
                "https://api.covid19india.org/data.json";

        Bundle bundle = new Bundle();
        bundle.putString("url", urlc);

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<history> historySearchLoader = loaderManager.getLoader(loaderId);
        if (historySearchLoader == null) {
            loaderManager.initLoader(loaderId, bundle, this);
        } else {
            loaderManager.restartLoader(loaderId, bundle, this);
        }
    }

    @NonNull
    @Override
    public Loader<List<history>> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<List<history>>(getApplicationContext()) {
            List<history> mHistoryData = null;

            @Override
            protected void onStartLoading() {
                if (mHistoryData != null) {
                    deliverResult(mHistoryData);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List<history> loadInBackground() {
                try {
                    JSONObject jsonResponse = store_networktask.getJsonResponse(args.getString("url"));
                    return state_jsonParse.parse(jsonResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(List<history> data) {
                mHistoryData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<history>> loader, List<history> data) {
        mProgressBar.setVisibility(View.INVISIBLE);

        if (data != null) {
            mHistoryAdapter.setHistoryData(data);
            rv_history.setVisibility(View.VISIBLE);
            tv_error.setVisibility(View.INVISIBLE);
        }else{
            rv_history.setVisibility(View.INVISIBLE);
            tv_error.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<history>> loader) {

    }

}
