package com.example.popularmovies;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.karim.MaterialTabs;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ViewPager pager;
    MaterialTabs tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_pager);

        setupMovieViewPager();

    }

    private void setupMovieViewPager() {
        pager = ButterKnife.findById(this, R.id.pager);
        pager.setAdapter(new MoviePagerAdapter(this, getSupportFragmentManager()));
        tabs = ButterKnife.findById(this, R.id.tabs);
        tabs.setViewPager(pager);
    }
}