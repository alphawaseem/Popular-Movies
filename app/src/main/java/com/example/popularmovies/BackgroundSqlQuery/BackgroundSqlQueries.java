package com.example.popularmovies.BackgroundSqlQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import com.example.popularmovies.Movie;
import com.example.popularmovies.data.MoviesContract;
import com.getbase.android.db.cursors.FluentCursor;
import com.getbase.android.db.provider.ProviderAction;

import org.chalup.microorm.MicroOrm;

import java.util.List;

/**
 * Created by peace on 1/11/16.
 */

public class BackgroundSqlQueries extends AsyncTaskLoader<FluentCursor> {

    private List<Movie> movies;
    private Context context;

    public BackgroundSqlQueries(Context context, List<Movie> movies) {
        super(context);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public FluentCursor loadInBackground() {
        MicroOrm orm = new MicroOrm();
        ContentValues values;
        for (int i = 0, n = movies.size(); i < n; i++) {
            if (movieInDatabase(movies.get(0))) {
                values = orm.toContentValues(movies.get(i));
                ProviderAction.insert(MoviesContract.CONTENT_URI).values(values).perform(context.getContentResolver());
            }
        }
        return ProviderAction.query(MoviesContract.CONTENT_URI).perform(context.getContentResolver());
    }

    private boolean movieInDatabase(Movie movie) {
        Cursor cursor = ProviderAction.query(MoviesContract.CONTENT_URI).projection(MoviesContract.MoviesEntry._ID)
                .where(MoviesContract.MoviesEntry._ID + "=?", movie.getId()).perform(getContext().getContentResolver());
        return (cursor != null && cursor.moveToFirst());
    }
}
