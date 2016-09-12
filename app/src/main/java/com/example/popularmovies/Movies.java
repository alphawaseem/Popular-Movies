package com.example.popularmovies;

import android.net.Uri;

/**
 * Created by hajira on 11/9/16.
 */
public class Movies {

    private final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
    private String mPosterUrl ;
    private String mOverview ;
    private String mReleaseDate ;
    private String mOriginalTitle ;
    private String mTitle ;
    private String mVoteAverage ;

    public Movies(String posterPath , String overview , String date , String orginalTitle , String title , String votes) {

        setPosterPath(posterPath);
        setOverview(overview);
        setReleaseDate(date);
        setOriginalTitle(orginalTitle);
        setTitle(title);
        setVoteAverage(votes);
    }

    private void setPosterPath( String posterPath ) {
        Uri posterUri = Uri.parse(POSTER_BASE_URL).buildUpon().appendEncodedPath(posterPath).build();
        mPosterUrl = posterUri.toString();
    }

    private void setVoteAverage(String voteCount) {
        mVoteAverage  = voteCount;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public String getReleaseDate(){
        return mReleaseDate ;
    }

    private void setReleaseDate(String date) {
        mReleaseDate = date;
    }

    public String getOverview(){
        return mOverview;
    }

    private void setOverview(String overview) {
        mOverview = overview;
    }

    public String getOriginalTitle(){
        return mOriginalTitle;
    }

    private void setOriginalTitle(String title) {
        mOriginalTitle = title;
    }

    public String getTitle(){
        return mTitle;
    }

    private void setTitle(String title) {
        mTitle = title;
    }

    public String getVoteCount(){
        return mVoteAverage;
    }

    @Override
    public String toString() {
        return "Movie : " + getTitle() + "\n" +
                "Release Date : " + getReleaseDate() + "\n" ;
    }
}
