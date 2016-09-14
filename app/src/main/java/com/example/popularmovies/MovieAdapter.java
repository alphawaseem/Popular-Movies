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
 * Created by waseem on 12/9/16.
 */
public class MovieAdapter extends ArrayAdapter<Movies> {

    private final Picasso imageLoader;
    Context mContext;

    public MovieAdapter(Context context, ArrayList<Movies> movies) {
        super(context, 0, movies);
        mContext = context;
        imageLoader = Picasso.with(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_view, parent, false);
        }

        Movies movie = getItem(position); //get the movie at position
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_poster); //find view
        imageLoader.load(movie.getPosterUrl()).into(imageView); // load image into that view
        return convertView; //

    }
}
