package com.example.popularmovies;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.popularmovies.MyUtils.hideProgressBar;
import static com.example.popularmovies.MyUtils.isNetworkAvailable;
import static com.example.popularmovies.MyUtils.showNoInternetMessage;
import static com.example.popularmovies.MyUtils.showNoMoviesFoundMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment {


    final static String TAG = TopRatedFragment.class.getSimpleName();
    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = ApiKey.getApiKey();
    static int mPos = -1;
    MoviesAdapter adapter;

    static TopRatedFragment newInstance() {
        TopRatedFragment fragment = new TopRatedFragment();
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

            Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                    hideProgressBar(rootView, R.id.progress_bar);
                    showMoviesInRecyclerView(rootView, response.body().getResults(), getActivity().getSupportFragmentManager());
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

    void showMoviesInRecyclerView(View view, final List<Movie> movieList, final FragmentManager fragmentManager) {

        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView recyclerView;
        recyclerView = ButterKnife.findById(view, R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(view.getContext(), view.getResources().getInteger(R.integer.no_of_columns));
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new MoviesAdapter(movieList, view.getContext(), new SingleChoiceMode(), recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        if (mPos != -1) {
            mLayoutManager.scrollToPosition(mPos);
            adapter.onChecked(mPos, true);
        }
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                View detailFragment = ButterKnife.findById(view.getRootView(), R.id.detail_fragment_container);
                mPos = position;
                boolean isCheckedNow = adapter.isChecked(position);

                if (!isCheckedNow) {
                    adapter.onChecked(position, true);
                    view.setActivated(true);
                }
                if (detailFragment != null) {

                    fragmentManager.beginTransaction().replace(R.id.detail_fragment_container, DetailFragment.newInstance(movieList.get(position))).commit();

                } else {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("MOVIE", movieList.get(position));
                    view.getContext().startActivity(intent);
                }
            }
        }));
    }
}
