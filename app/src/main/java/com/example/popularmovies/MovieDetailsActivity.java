package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by peace on 13/9/16.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detaiil);
        Intent intent = getIntent();
        Movies movie = intent.getParcelableExtra(MainActivity.MOVIE);
        ImageView imageView = (ImageView) findViewById(R.id.detail_movie_poster);
        Picasso.with(this).load(movie.getPosterUrl()).into(imageView);
    }
}
