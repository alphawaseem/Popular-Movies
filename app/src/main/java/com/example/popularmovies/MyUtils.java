package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;


class MyUtils {

    private static int colSpan = 2;

    static boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    static void hideProgressBar(View view, int id) {
        if (view != null) {
            ProgressBar progressBar = ButterKnife.findById(view, id);
            if (progressBar != null)
                progressBar.setVisibility(View.INVISIBLE);
        }
    }

    static void showMoviesInRecyclerView(View view, final List<Movie> movieList) {

        RecyclerView.LayoutManager mLayoutManager;
        MoviesAdapter adapter;
        RecyclerView recyclerView;
        recyclerView = ButterKnife.findById(view, R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(view.getContext(), colSpan);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new MoviesAdapter(movieList, view.getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("MOVIE", movieList.get(position));
                view.getContext().startActivity(intent);
            }
        }));
    }

    static void showNoMoviesFoundMessage(View view) {
        TextView failed = ButterKnife.findById(view, R.id.failed_msg);
        failed.setText(R.string.no_movies);
    }

    static void showNoInternetMessage(View view) {
        TextView failed = ButterKnife.findById(view, R.id.failed_msg);
        failed.setText(R.string.no_internet);
    }
}
