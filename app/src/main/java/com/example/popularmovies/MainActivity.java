package com.example.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.Stetho;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initialize(Stetho.newInitializerBuilder(this).enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build())
        ;

        setContentView(R.layout.main_view);

       /* ContentValues values = new ContentValues();
        values.put(MoviesContract.MoviesEntry._ID,123);
        values.put(MoviesContract.MoviesEntry.COL_MOVIE_TITLE,"Captain");
        values.put(MoviesContract.MoviesEntry.COL_MOVIE_CATEGORY,"popular");
        values.put(MoviesContract.MoviesEntry.COL_MOVIE_ORIGINAL_TITLE,"Captain AI");
        values.put(MoviesContract.MoviesEntry.COL_MOVIE_RELEASE_DATE,"12/10/2013");
        values.put(MoviesContract.MoviesEntry.COL_AVG_RATING,7.8);
        values.put(MoviesContract.MoviesEntry.COL_MOVIE_POSTER_PATH,"/path_to_poster");

        //database.insert(MoviesContract.MoviesEntry.MOVIES_TABLE,null,values);
        String[] selection = { MoviesContract.MoviesEntry.COL_MOVIE_TITLE};

        getContentResolver().insert(MoviesContract.CONTENT_URI,values);
        Cursor cursor = getContentResolver().query(MoviesContract.CONTENT_URI,selection,null,null,null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_MOVIE_TITLE));
        Toast.makeText(this,title, Toast.LENGTH_SHORT).show();
        cursor.close();*/
    }

}