package com.example.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import static com.example.popularmovies.data.MoviesContract.isCollectionUri;

/**
 * Created by peace on 27/10/16.
 */

public class MoviesProvider extends ContentProvider {


    private MoviesDbHelper db = null;

    @Override
    public boolean onCreate() {
        db = new MoviesDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(MoviesContract.MoviesEntry.MOVIES_TABLE);

        Cursor cursor = queryBuilder.query(db.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        if (isCollectionUri(uri)) {
            return ("vnd.popularmovies.cursor.dir/movie");
        }
        return ("vnd.popularmovies.cursor.item/movie");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = db.getWritableDatabase().insert(MoviesContract.MoviesEntry.MOVIES_TABLE, null, values);

        if (rowID > 0) {
            Uri rowUri = ContentUris.withAppendedId(MoviesContract.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        throw new SQLiteException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int id = db.getWritableDatabase().delete(MoviesContract.MoviesEntry.MOVIES_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int id = db.getWritableDatabase().update(MoviesContract.MoviesEntry.MOVIES_TABLE, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return id;
    }
}
