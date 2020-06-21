package com.example.android.hackathonapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hackathonapp.Adapters.news_adapter;
import com.example.android.hackathonapp.NetworkUtils.news_JsonParse;
import com.example.android.hackathonapp.NetworkUtils.news_networktask;
import com.example.android.hackathonapp.R;
import com.example.android.hackathonapp.modals.news;

import java.util.List;

public class news_home extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<news>>, news_adapter.onClickHandler {

    private RecyclerView rv_news;
    private news_adapter mNewsAdapter;
    private TextView tv_error;
    private ProgressBar mProgressBar;
    int loaderId = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_home);

        tv_error = findViewById(R.id.tv_error_message_display);
        mProgressBar = findViewById(R.id.pb_loading_indicator);
        rv_news = findViewById(R.id.rv_news);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_news.setLayoutManager(linearLayoutManager);
        rv_news.setHasFixedSize(true);

        mNewsAdapter = new news_adapter(this);
        rv_news.setAdapter(mNewsAdapter);

        getSupportLoaderManager().initLoader(loaderId, null, this);
        loadingNews();

    }

    private void loadingNews() {
        rv_news.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.INVISIBLE);

        String apiKey = "d1e3cee35c0040a995c79212a059296e";
        String urlc =
                "https://newsapi.org/v2/top-headlines?country=in&?q=COVID&category=health&sortBy=publishedAt&apiKey="+apiKey;

        Bundle bundle = new Bundle();
        bundle.putString("url", urlc);

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<news> newsSearchLoader = loaderManager.getLoader(loaderId);
        if (newsSearchLoader == null) {
            loaderManager.initLoader(loaderId, bundle, this);
        } else {
            loaderManager.restartLoader(loaderId, bundle, this);
        }
    }

    @Override
    public void onClicked(news currentNews) {
        try {
            Uri webpage = Uri.parse(currentNews.getNews_url());
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Loader<List<news>> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<List<news>>(getApplicationContext()) {
            List<news> mNewsData = null;

            @Override
            protected void onStartLoading() {
                if (mNewsData != null) {
                    deliverResult(mNewsData);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List<news> loadInBackground() {
                try {
                    String jsonResponse = news_networktask.getJsonResponse(args.getString("url"));
                    return news_JsonParse.parse(jsonResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(List<news> data) {
                mNewsData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<news>> loader, List<news> data) {
        mProgressBar.setVisibility(View.INVISIBLE);

        if (data != null) {
            mNewsAdapter.setNewsData(data);
            rv_news.setVisibility(View.VISIBLE);
            tv_error.setVisibility(View.INVISIBLE);
        }else{
            rv_news.setVisibility(View.INVISIBLE);
            tv_error.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<news>> loader) {

    }
}
