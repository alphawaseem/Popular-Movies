package com.example.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movies>> {

    public static final String MOVIE = "Movie";
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            ProgressBar pBar = (ProgressBar) findViewById(R.id.progress_bar);
            pBar.setVisibility(View.GONE);
            TextView textView = (TextView) findViewById(R.id.empty_text);
            textView.setText(getText(R.string.no_internet));

        }


    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                openSetting();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSetting() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader<ArrayList<Movies>> onCreateLoader(int i, Bundle bundle) {
        return new MoviesDBLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movies>> loader, ArrayList<Movies> movies) {

        ProgressBar pBar = (ProgressBar) findViewById(R.id.progress_bar);
        pBar.setVisibility(View.GONE);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        if (movies != null) {
            movieAdapter = new MovieAdapter(MainActivity.this, movies);
            gridView.setAdapter(movieAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movies movie = (Movies) parent.getItemAtPosition(position);
                    //Toast.makeText(getBaseContext(), movie.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                    intent.putExtra(MOVIE, movie);
                    startActivity(intent);
                }
            });
        } else {
            TextView textView = (TextView) findViewById(R.id.empty_text);
            textView.setText(getText(R.string.empty_text));
            gridView.setEmptyView(textView);


        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movies>> loader) {
        if (movieAdapter != null) {
            movieAdapter.clear();
        }
    }

}
