package com.example.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.ButterKnife;


class MyUtils {


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



    static void showNoMoviesFoundMessage(View view) {
        TextView failed = ButterKnife.findById(view, R.id.failed_msg);
        failed.setText(R.string.no_movies);
    }

    static void showNoInternetMessage(View view) {
        TextView failed = ButterKnife.findById(view, R.id.failed_msg);
        failed.setText(R.string.no_internet);
    }
}
