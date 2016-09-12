package com.example.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hajira on 12/9/16.
 */
public class MovieAdapter extends ArrayAdapter<Movies> {

    public MovieAdapter(Context context, ArrayList<Movies> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if (gridView == null) {
            gridView = LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_view, parent, false);
        }

        Movies movie = getItem(position);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.movie_poster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).into(imageView);
        return gridView;

    }
}
