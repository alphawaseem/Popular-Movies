package com.example.popularmovies;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movies>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String orderBy = sharedPreferences.getString(
                getString(R.string.order_type_key), getString(R.string.order_by_popular));
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.order_type_key), orderBy);
        getLoaderManager().initLoader(0, bundle, this);


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
        String orderBy = bundle.getString(getString(R.string.order_type_key), getString(R.string.order_by_popular));
        return new MoviesDBLoader(this, orderBy);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movies>> loader, ArrayList<Movies> movies) {
        Log.v("    MOVIE :   ",movies.get(0).toString());
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movies>> loader) {

    }
}
