package com.example.popularmovies;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by peace on 3/10/16.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Movie> movieList;
    RecyclerView.LayoutManager mLayoutManager;
    MoviesAdapter adapter;
    RecyclerView recyclerView;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = ApiKey.getApiKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        int span = 2;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            span = 3;
        }

         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(this,span);
        recyclerView.setLayoutManager(mLayoutManager);

        RetrofitApiInterface apiService =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movieList = response.body().getResults();
                adapter = new MoviesAdapter(movieList,getApplicationContext());
                //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });



    }
}