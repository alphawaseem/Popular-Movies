package com.example.popularmovies;

import android.net.Uri;


/**
 * Created by waseem on 9/10/16.
 */
public class MovieUriBuilder {

    private static String mApiKey = ApiKey.getApiKey();


    public static Uri getUri(String orderType) {
        String mBaseUrl = "http://api.themoviedb.org/3/movie/";
        Uri uri = Uri.parse(mBaseUrl).buildUpon().appendPath(orderType)
                .appendQueryParameter("api_key", mApiKey).build();
        return uri;
    }


}
