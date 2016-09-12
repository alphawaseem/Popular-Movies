package com.example.popularmovies;

import android.net.Uri;

import java.net.URI;

/**
 * Created by hajira on 11/9/16.
 */
public class Movies {

    private String mPosterUrl ;
    private String mOverview ;
    private String mReleaseDate ;
    private String mOriginalTitle ;
    private String mTitle ;
    private String mVoteAverage ;
    private final String POSTER_BASE_URL =" http://image.tmdb.org/t/p/w185" ;

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

    private void setOverview(String overview) {
        mOverview = overview;
    }

    private void setReleaseDate(String date) {
        mReleaseDate = date ;
    }

    private void setOriginalTitle(String title) {
        mOriginalTitle = title ;
    }

    private void setTitle(String title) {
        mTitle = title ;
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

    public String getOverview(){
        return mOverview;
    }

    public String getOriginalTitle(){
        return mOriginalTitle;
    }

    public String getTitle(){
        return mTitle;
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
