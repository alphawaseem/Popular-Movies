package com.example.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hajira on 11/9/16.
 */
public class MoviesDBLoader extends AsyncTaskLoader<ArrayList<Movies>> implements Preference.OnPreferenceChangeListener {


    private static final String LOG_TAG = MoviesDBLoader.class.getSimpleName();

    public MoviesDBLoader(Context context) {

        super(context);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Movies> loadInBackground() {

        String jsonResponse = DownloadMoviesJson();
        if (jsonResponse != null) {
             return MovieJsonParser.parseMoviesJson(jsonResponse);
        }
        return null;
    }

    private String DownloadMoviesJson() {

        String jsonResponse = null;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String orderBy = sharedPreferences.getString(
                getContext().getString(R.string.order_type_key), getContext().getString(R.string.order_by_popular));
        URL url = DownloadUtils.getMoviesDbURL(orderBy);
        if (url != null) {
            jsonResponse = DownloadUtils.makeHttpRequest(url);
        }
        return jsonResponse;
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        loadInBackground();
        return true;
    }
}
