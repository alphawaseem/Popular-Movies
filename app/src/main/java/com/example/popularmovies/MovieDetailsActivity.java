package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by peace on 13/9/16.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Intent intent = getIntent();
        Movies movie = intent.getParcelableExtra(MainActivity.MOVIE);


        ImageView imageView = (ImageView) findViewById(R.id.detail_movie_poster);
        TextView titleView = (TextView) findViewById(R.id.title_field);
        TextView originalView = (TextView) findViewById(R.id.original_title_field);
        TextView releaseDateView = (TextView) findViewById(R.id.date_field);
        TextView voteView = (TextView) findViewById(R.id.votes_field);
        TextView overviewField = (TextView) findViewById(R.id.overview_field);

        Picasso.with(this).load(movie.getPosterUrl()).fit().into(imageView);

        titleView.setText(movie.getTitle());
        originalView.setText(movie.getOriginalTitle());
        releaseDateView.setText(movie.getReleaseDate());
        voteView.setText(movie.getVoteCount());
        overviewField.setText(movie.getOverview());


    }
}
