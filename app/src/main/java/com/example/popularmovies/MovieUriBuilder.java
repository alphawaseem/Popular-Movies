package com.example.popularmovies;

import android.net.Uri;


/**
 * Created by waseem on 9/10/16.
 */
public class MovieUriBuilder {

    private static final String TOP_RATED = "top_rated";
    private static final String POPULAR = "popular";
    private static String mApiKey = ApiKey.getApiKey();
    private static String mOrderType = POPULAR;

    public static Uri getUri() {
        String mBaseUrl = "http://api.themoviedb.org/3/movie/";
        return Uri.parse(mBaseUrl).buildUpon().appendPath(mOrderType)
                .appendQueryParameter("api_key", mApiKey).build();

    }

    public static void setOrderType(String orderType) {
        if (orderType.equals(TOP_RATED)) {
            mOrderType = TOP_RATED;
        } else {
            mOrderType = POPULAR;
        }
    }

}
