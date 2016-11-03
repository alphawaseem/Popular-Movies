package com.example.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by peace on 27/10/16.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";

    private static String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MoviesContract.MoviesEntry.MOVIES_TABLE +
            " ( " + MoviesContract.MoviesEntry._ID + " INTEGER PRIMARY KEY ," +
            MoviesContract.MoviesEntry.COL_MOVIE_TITLE + " TEXT NOT NULL, " +
            MoviesContract.MoviesEntry.COL_MOVIE_ORIGINAL_TITLE + " TEXT NOT NULL, " +
            MoviesContract.MoviesEntry.COL_MOVIE_OVERVIEW + " TEXT NOT NULL, " +
            MoviesContract.MoviesEntry.COL_MOVIE_RELEASE_DATE + " TEXT NOT NULL, " +
            MoviesContract.MoviesEntry.COL_VOTE_AVG + " REAL ," +
            MoviesContract.MoviesEntry.COL_MOVIE_POSTER_PATH + " TEXT NOT NULL" +
            ");";

    private static String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + MoviesContract.ReviewsEntry.REVIEWS_TABLE +
            "( " + MoviesContract.ReviewsEntry._ID + " INTEGER PRIMARY KEY ," +
            MoviesContract.ReviewsEntry.COL_AUTHOR + " TEXT ," +
            MoviesContract.ReviewsEntry.COL_REVIEW + " TEXT NOT NULL, " +
            MoviesContract.ReviewsEntry.COL_MOVIE_ID + " INTEGER NOT NULL , " +
            "FOREIGN KEY(" + MoviesContract.ReviewsEntry.COL_MOVIE_ID + ")" +
            "REFERENCES " + MoviesContract.MoviesEntry.MOVIES_TABLE + "(" +
            MoviesContract.MoviesEntry._ID + ")" + ");";

    private static String SQL_CREATE_TRAILER_TABLE = "CREATE TABLE " + MoviesContract.TrailerEntry.TRAILER_TABLE +
            "( " + MoviesContract.TrailerEntry._ID + " INTEGER PRIMARY KEY ," +
            MoviesContract.TrailerEntry.COL_KEY + " TEXT ," +
            MoviesContract.TrailerEntry.COL_NAME + " TEXT NOT NULL, " +
            MoviesContract.TrailerEntry.COL_MOVIE_ID + " INTEGER NOT NULL , " +
            "FOREIGN KEY(" + MoviesContract.TrailerEntry.COL_MOVIE_ID + ")" +
            "REFERENCES " + MoviesContract.MoviesEntry.MOVIES_TABLE + "(" +
            MoviesContract.MoviesEntry._ID + ")" + ");";




    private static String SQL_DELETE_MOVIES_TABLE = "DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.MOVIES_TABLE;
    private static String SQL_DELETE_REVIEWS_TABLE = "DROP TABLE IF EXISTS " + MoviesContract.ReviewsEntry.REVIEWS_TABLE;
    private static String SQL_DELETE_TRAILER_TABLE = "DROP TABLE IF EXISTS " + MoviesContract.TrailerEntry.TRAILER_TABLE;


    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_REVIEW_TABLE);
        db.execSQL(SQL_CREATE_TRAILER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MOVIES_TABLE);
        db.execSQL(SQL_DELETE_REVIEWS_TABLE);
        db.execSQL(SQL_DELETE_TRAILER_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
