package com.example.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hajira on 12/9/16.
 */
public final  class MovieJsonParser {

    private final static String LOG_TAG = MovieJsonParser.class.getSimpleName();

    private MovieJsonParser() {

    }

    public static ArrayList<Movies> parseMoviesJson(String jsonResponse) {

        ArrayList<Movies> movies = new ArrayList<Movies>();
        try {
            JSONObject rootObject = new JSONObject(jsonResponse);
            JSONArray moviesArray = rootObject.getJSONArray("results");
            for( int i = 0 , len = moviesArray.length() ; i < len ; i++  ) {
                JSONObject movie = moviesArray.getJSONObject(i);
                String posterPath = movie.getString("poster_path");
                String overview = movie.getString("overview");
                String releaseDate = movie.getString("release_date");
                String originalTitle = movie.getString("original_title");
                String title = movie.getString("title");
                String voteAverage = movie.getString("vote_average");
                movies.add(new Movies(posterPath,overview,releaseDate,originalTitle,title,voteAverage));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG,e.getLocalizedMessage());
        }
        return movies;
    }

}
