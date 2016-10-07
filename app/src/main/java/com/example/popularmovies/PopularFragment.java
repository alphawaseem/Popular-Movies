package com.example.popularmovies;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    final static String TAG = PopularFragment.class.getSimpleName();
    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = ApiKey.getApiKey();
    RecyclerView.LayoutManager mLayoutManager;
    MoviesAdapter adapter;
    RecyclerView recyclerView;
    private List<Movie> movieList;

    static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recycle_view, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        int span = 2;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            span = 3;
        }
        mLayoutManager = new GridLayoutManager(getActivity(), span);
        recyclerView.setLayoutManager(mLayoutManager);

        RetrofitApiInterface apiService =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movieList = response.body().getResults();
                adapter = new MoviesAdapter(movieList, getContext());
                //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("MOVIE", movieList.get(position));
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        return rootView;
    }


}
