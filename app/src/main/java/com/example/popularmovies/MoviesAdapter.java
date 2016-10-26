package com.example.popularmovies;

import android.content.Context;
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

    Picasso imageLoader;
    private List<Movie> movies;
    private Context context;

    public MoviesAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
        imageLoader = Picasso.with(context);
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
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView movieTitle;
        TextView voteCount;
        ImageView overflow;


        public MovieViewHolder(View v) {
            super(v);
            poster = (ImageView) v.findViewById(R.id.thumbnail);
            movieTitle = (TextView) v.findViewById(R.id.movie_title);
            voteCount = (TextView) v.findViewById(R.id.vote_count);
            overflow = (ImageView) v.findViewById(R.id.star);
        }
    }


}