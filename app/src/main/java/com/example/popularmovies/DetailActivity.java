package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.popularmovies.models.Movie;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("MOVIE");
        setTitle(movie.getTitle());
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (detailFragment == null) {

            detailFragment = DetailFragment.newInstance(movie);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, detailFragment)
                    .commit();
        }
    }

}
