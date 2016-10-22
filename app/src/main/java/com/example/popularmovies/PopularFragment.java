package com.example.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.popularmovies.MyUtils.hideProgressBar;
import static com.example.popularmovies.MyUtils.isNetworkAvailable;
import static com.example.popularmovies.MyUtils.showMoviesInRecyclerView;
import static com.example.popularmovies.MyUtils.showNoInternetMessage;
import static com.example.popularmovies.MyUtils.showNoMoviesFoundMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    final static String TAG = PopularFragment.class.getSimpleName();
    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = ApiKey.getApiKey();


    static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.recycle_view, container, false);

        if (!isNetworkAvailable(getContext())) {

            hideProgressBar(rootView, R.id.progress_bar);
            showNoInternetMessage(rootView);
        } else {
            RetrofitApiInterface apiService =
                    RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

            Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                    hideProgressBar(rootView, R.id.progress_bar);
                    showMoviesInRecyclerView(rootView, response.body().getResults());
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    hideProgressBar(rootView, R.id.progress_bar);
                    showNoMoviesFoundMessage(rootView);
                    Log.e(TAG, t.toString());
                }
            });
        }
        return rootView;
    }


}
