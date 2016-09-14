package com.example.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by waseem on 9/10/16.
 * This class is used to build movieDB query url
 * Just call getMoviesDbURL function by giving OrderType/SortType as input
 */
public final class MovieUriBuilder {

    private static final String TOP_RATED = "top_rated";
    private static final String POPULAR = "popular";
    private static final String LOG_TAG = MovieUriBuilder.class.getSimpleName();

    // private class which contains my api key replace it with your api key
    private static String mApiKey = ApiKey.getApiKey();

    private static String mOrderType = POPULAR; //default set to popular

    /**
     * private constructor
     */
    private MovieUriBuilder() {

    }


    /**
     * Given the sort type i.e[popular/top_rated] as input to this function it builds the
     * query url  and returns the query url of type URL
     *
     * @param orderBy sort type in the movieDB url [ popular / top_rated ] used to build query url
     * @return returns the complete Url of the query of type URL
     */
    public static URL getMoviesDbURL(String orderBy) {

        //set the sort type
        setOrderType(orderBy);
        URL url = null;
        try {
            //get the url based on above sort type
            url = new URL(getUri().toString());
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }
        return url;
    }

    /**
     * This function will build the moviedb query url based upon mOrderType
     * and api key
     *
     * @return the query url in Uri format
     */
    private static Uri getUri() {
        String mBaseUrl = "http://api.themoviedb.org/3/movie/";
        return Uri.parse(mBaseUrl).buildUpon().appendPath(mOrderType)
                .appendQueryParameter("api_key", mApiKey).build();

    }

    /**
     * Sets the orderType of the query url
     *
     * @param orderType order type either [top_rated or popular]
     */
    private static void setOrderType(String orderType) {
        if (orderType.equals(TOP_RATED)) {
            mOrderType = TOP_RATED;
        } else { // if order type is other than TOP_RATED then set to POPULAR
            mOrderType = POPULAR;
        }
    }

}
