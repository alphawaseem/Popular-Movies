package com.example.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by peace on 3/10/16.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Picasso imageLoader;
    private List<Movie> movies;
    private ChoiceMode choiceMode;
    private RecyclerView rv;

    public MoviesAdapter(List<Movie> movies, Context context, ChoiceMode choiceMode, RecyclerView rv) {
        this.movies = movies;
        imageLoader = Picasso.with(context);
        this.rv = rv;
        this.choiceMode = choiceMode;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Movie currentMovie = movies.get(position) ;
        imageLoader.load("http://image.tmdb.org/t/p/w185/"+currentMovie.getPosterPath()).fit().into(holder.poster);
        holder.movieTitle.setText(currentMovie.getOriginalTitle());
        holder.voteCount.setText(currentMovie.getVoteAverage());
        holder.setChecked(isChecked(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void onChecked(int position, boolean isChecked) {
        if (choiceMode.isSingleChoice()) {
            int checked = choiceMode.getCheckedPosition();

            if (checked >= 0) {
                MovieViewHolder row =
                        (MovieViewHolder) rv.findViewHolderForAdapterPosition(checked);

                if (row != null) {
                    row.setChecked(false);
                }
            }
        }

        choiceMode.setChecked(position, isChecked);
    }

    boolean isChecked(int position) {
        return (choiceMode.isChecked(position));
    }

    void onSaveInstanceState(Bundle state) {
        choiceMode.onSaveInstanceState(state);
    }

    void onRestoreInstanceState(Bundle state) {
        choiceMode.onRestoreInstanceState(state);
    }

    @Override
    public void onViewAttachedToWindow(MovieViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        if (holder.getAdapterPosition() != choiceMode.getCheckedPosition()) {
            (holder).setChecked(false);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView movieTitle;
        TextView voteCount;
        ImageView overflow;
        View view;

        public MovieViewHolder(View v) {
            super(v);
            poster = (ImageView) v.findViewById(R.id.thumbnail);
            movieTitle = (TextView) v.findViewById(R.id.movie_title);
            voteCount = (TextView) v.findViewById(R.id.vote_count);
            overflow = (ImageView) v.findViewById(R.id.star);
            view = v;
        }

        void setChecked(boolean isChecked) {
            view.setActivated(isChecked);
        }
    }


}