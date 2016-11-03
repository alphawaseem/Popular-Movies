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
    private static final int REVIEWS = 3;
    private static final int REVIEW_ITEM = 4;
    private static final int VIDEOS = 5;
    private static final int VIDEO_ITEM = 6;
    private static final UriMatcher MATCHER;

    static {
        MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        MATCHER.addURI(AUTHORITY,
                "movies", MOVIES);
        MATCHER.addURI(AUTHORITY,
                "movies/#", MOVIE_ITEM);
        MATCHER.addURI(AUTHORITY, "reviews", REVIEWS);
        MATCHER.addURI(AUTHORITY, "reviews/#", REVIEW_ITEM);
        MATCHER.addURI(AUTHORITY, "videos", VIDEOS);
        MATCHER.addURI(AUTHORITY, "videos/#", VIDEO_ITEM);

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
            case REVIEWS:
                type = "vnd.popularmovies.cursor.dir/review";
                break;
            case REVIEW_ITEM:
                type = "vnd.popularmovies.cursor.item/review";
                break;
            case VIDEOS:
                type = "vnd.popularmovies.cursor.dir/video";
                break;
            case VIDEO_ITEM:
                type = "vnd.popularmovies.cursor.item/video";
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

    public static class ReviewsEntry implements BaseColumns {
        public static final String REVIEWS_TABLE = "reviews";
        public static final String COL_AUTHOR = "author";
        public static final String COL_REVIEW = "review";
        public static final String COL_MOVIE_ID = "movie_id";

        public static final int INDEX_ID = 0;
        public static final int INDEX_AUTHOR = 1;
        public static final int INDEX_REVIEW = 2;
        public static final int INDEX_MOVIE_ID = 3;
    }

    public static class TrailerEntry implements BaseColumns {
        public static final String TRAILER_TABLE = "trailers";
        public static final String COL_KEY = "key";
        public static final String COL_NAME = "name";
        public static final String COL_MOVIE_ID = "movie_id";

        public static final int INDEX_ID = 0;
        public static final int INDEX_KEY = 1;
        public static final int INDEX_NAME = 2;
        public static final int INDEX_MOVIE_ID = 3;
    }


}
