package com.example.popularmovies.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by peace on 27/10/16.
 */

public class MoviesContract {

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.popularmovies.data.MoviesProvider/");
    public static final Uri MOVIES_URI = Uri.parse(CONTENT_URI + "movies");
    public static final String AUTHORITY = "com.example.popularmovies.data.MoviesProvider";
    private static final int MOVIES = 1;
    private static final int MOVIE_ITEM = 2;
    private static final UriMatcher MATCHER;

    static {
        MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        MATCHER.addURI(AUTHORITY,
                "movies", MOVIES);
        MATCHER.addURI(AUTHORITY,
                "movies/#", MOVIE_ITEM);
    }

    private MoviesContract() {

    }

    public static String getType(Uri uri) {
        String type = "";
        switch (MATCHER.match(uri)) {
            case MOVIES:
                type = "vnd.popularmovies.cursor.dir/movie";
                break;
            case MOVIE_ITEM:
                type = "vnd.popularmovies.cursor.item/movie";
                break;
        }
        return type;
    }

    public static class MoviesEntry implements BaseColumns {
        public static final String MOVIES_TABLE = "movies";
        public static final String COL_MOVIE_OVERVIEW = "overview";
        public static final String COL_MOVIE_TITLE = "title";
        public static final String COL_MOVIE_ORIGINAL_TITLE = "original_title";
        public static final String COL_MOVIE_POSTER_PATH = "poster_path";
        public static final String COL_MOVIE_RELEASE_DATE = "release_date";
        public static final String COL_VOTE_AVG = "vote_avg";

        public static final int INDEX_ID = 0;
        public static final int INDEX_TITLE = 1;
        public static final int INDEX_ORIGINAL_TITLE = 2;
        public static final int INDEX_OVERVIEW = 3;
        public static final int INDEX_RELEASE_DATE = 4;
        public static final int INDEX_VOTE_AVG = 5;
        public static final int INDEX_POSTER_PATH = 6;
    }
}
