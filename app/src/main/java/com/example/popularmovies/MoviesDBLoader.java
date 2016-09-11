package com.example.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.List;

/**
 * Created by hajira on 11/9/16.
 */
public class MoviesDBLoader extends AsyncTaskLoader<List<Movies>> {


    private static final String LOG_TAG = MoviesDBLoader.class.getSimpleName();
    private String mOrderBy;

    public MoviesDBLoader(Context context, String orderBy) {

        super(context);
        mOrderBy = orderBy;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movies> loadInBackground() {

        String jsonResponse = DownloadMoviesJson();
        if (jsonResponse != null) {
            // MovieJsonParser.parseJsonResponse(jsonResponse);
        }
        return null;
    }

    private String DownloadMoviesJson() {

        String jsonResponse = null;
        URL url = DownloadUtils.getMoviesDbURL(mOrderBy);
        if (url != null) {
            jsonResponse = DownloadUtils.makeHttpRequest(url);
        }
        return jsonResponse;
    }
}
