package com.example.popularmovies.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.ChoiceMode;
import com.example.popularmovies.Movie;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

/**
 * Created by peace on 1/11/16.
 */

public class MyMoviesAdapter extends RecyclerCursorAdapter<MyMoviesAdapter.MyMovieHolder> {

    private ChoiceMode choiceMode;
    private RecyclerView rv;
    private Context mContext;

    public MyMoviesAdapter(Cursor c, Context context, ChoiceMode choiceMode, RecyclerView rv) {
        super(c);
        this.rv = rv;
        this.choiceMode = choiceMode;
        mContext = context;

    }

    @Override
    public void onBindViewHolder(MyMovieHolder holder, Cursor cursor, int position) {

        Movie curMovie = Movie.getFromCursor(cursor);
        holder.bindItem(curMovie, mContext, position);
        holder.setChecked(isChecked(position));

    }

    @Override
    public MyMovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MyMoviesAdapter.MyMovieHolder(view);
    }

    public void onChecked(int position, boolean isChecked) {
        if (choiceMode.isSingleChoice()) {
            int checked = choiceMode.getCheckedPosition();

            if (checked >= 0) {
                MyMovieHolder row =
                        (MyMovieHolder) rv.findViewHolderForAdapterPosition(checked);

                if (row != null) {
                    row.setChecked(false);
                }
            }
        }

        choiceMode.setChecked(position, isChecked);
    }

    public boolean isChecked(int position) {
        return (choiceMode.isChecked(position));
    }


    @Override
    public void onViewAttachedToWindow(MyMovieHolder holder) {
        super.onViewAttachedToWindow(holder);

        if (holder.getAdapterPosition() != choiceMode.getCheckedPosition()) {
            (holder).setChecked(false);
        }
    }

    static class MyMovieHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView movieTitle;
        TextView voteCount;
        View view;

        MyMovieHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        void bindItem(Movie movie, Context context, int position) {
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).fit().into(poster);
            movieTitle.setText(movie.getOriginalTitle());
            voteCount.setText(movie.getVoteAverage());
        }

        void setChecked(boolean isChecked) {
            view.setActivated(isChecked);
        }
    }

}
