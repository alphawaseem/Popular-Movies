package com.example.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by waseem on 11/9/16.
 * This class is used to get list of movies by making http request to movieDb url
 * in background
 */
public class MoviesDBLoader extends AsyncTaskLoader<ArrayList<Movies>> implements Preference.OnPreferenceChangeListener {


    public MoviesDBLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This function will download movies json and parse the movies from json
     * and returns the list of Movies object in background thread
     *
     * @return list of Movies object
     */
    @Override
    public ArrayList<Movies> loadInBackground() {

        String jsonResponse = DownloadMoviesJson();
        if (jsonResponse != null) {
            return MovieJsonParser.parseMoviesJson(jsonResponse);
        }
        return null;
    }

    /**
     * This function will download movies json and returns as string
     *
     * @return json response in string
     */
    private String DownloadMoviesJson() {

        String jsonResponse = null;

        //get order type from preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String orderBy = sharedPreferences.getString(
                getContext().getString(R.string.order_type_key), getContext().getString(R.string.order_by_popular));

        // get query url based on order type read from preference
        URL url = MovieUriBuilder.getMoviesDbURL(orderBy);
        if (url != null) {
            // download the response in string by calling helper function
            jsonResponse = DownloadUtils.makeHttpRequest(url);
        }
        return jsonResponse;
    }

    // if preference is changed then again load new movies
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        loadInBackground();
        return true;
    }
}
