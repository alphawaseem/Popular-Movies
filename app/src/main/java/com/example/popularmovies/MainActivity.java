package com.example.popularmovies;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import io.karim.MaterialTabs;

/**
 * Created by peace on 3/10/16.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_pager);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MoviePagerAdapter(this, getSupportFragmentManager()));
        MaterialTabs tabs = (MaterialTabs) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }
}