package com.example.popularmovies.retrofit;

import com.example.popularmovies.models.MovieReviewsResponse;
import com.example.popularmovies.models.MoviesResponse;
import com.example.popularmovies.models.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by peace on 3/10/16.
 */

public interface RetrofitApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<VideosResponse> getMovieVidz(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<MovieReviewsResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);
}