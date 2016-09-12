package com.example.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hajira on 12/9/16.
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

        Movies movie = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_poster);
        imageLoader.load(movie.getPosterUrl()).error(R.mipmap.ic_launcher).into(imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.movie_title);
        textView.setText(movie.getTitle());
        return convertView;

    }
}
