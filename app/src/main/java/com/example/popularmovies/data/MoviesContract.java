package com.example.popularmovies.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by peace on 27/10/16.
 */

public class MoviesContract {

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.popularmovies.data.MoviesProvider/movies");
    private static final int MOVIES = 1;
    private static final int MOVIE_ID = 2;
    private static final UriMatcher MATCHER;

    static {
        MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        MATCHER.addURI("com.example.popularmovies.data.MoviesProvider",
                "movies", MOVIES);
        MATCHER.addURI("com.example.popuarmovies.data.MoviesProvider",
                "movies/#", MOVIE_ID);
    }

    private MoviesContract() {

    }

    static boolean isCollectionUri(Uri url) {
        return (MATCHER.match(url) == MOVIES);
    }

    public static class MoviesEntry implements BaseColumns {
        public static final String MOVIES_TABLE = "movies";
        public static final String COL_MOVIE_OVERVIEW = "overview";
        public static final String COL_MOVIE_TITLE = "title";
        public static final String COL_MOVIE_ORIGINAL_TITLE = "original_title";
        public static final String COL_MOVIE_POSTER_PATH = "poster_path";
        public static final String COL_MOVIE_RELEASE_DATE = "release_date";
        public static final String COL_AVG_RATING = "avg_ratings";
    }
}
