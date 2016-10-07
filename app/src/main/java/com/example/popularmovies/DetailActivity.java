package com.example.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);

        if (detailFragment == null) {
            detailFragment = DetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, detailFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
