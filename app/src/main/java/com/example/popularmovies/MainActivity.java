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
    }


}