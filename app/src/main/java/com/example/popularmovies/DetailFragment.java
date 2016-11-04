package com.example.popularmovies;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.data.MoviesContract;
import com.example.popularmovies.models.Movie;
import com.example.popularmovies.models.MovieReviewsResponse;
import com.example.popularmovies.models.VideosResponse;
import com.example.popularmovies.retrofit.RetrofitApiClient;
import com.example.popularmovies.retrofit.RetrofitApiInterface;
import com.getbase.android.db.cursors.FluentCursor;
import com.getbase.android.db.provider.ProviderAction;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    static List<VideosResponse.Videos> trailers;
    static List<MovieReviewsResponse.MovieReviews> reviews;
    static Movie movie;
    View rootView;
    ImageView poster;
    TextView title;
    TextView overview;
    TextView releaseDate;
    TextView votes;
    RetrofitApiInterface apiService =
            RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

    public static DetailFragment newInstance(Movie curMovie) {
        movie = curMovie;
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.detail_movie_card, container, false);

        findViews(rootView);
        updateMovieInfo();
        getTrailers();
        getReviews();

        return rootView;

    }

    /**
     *
     */
    private void updateReviewsInfo() {

    }

    private void updateTrailersInfo() {

    }


    private void getReviews() {
        Call<MovieReviewsResponse> call = apiService.getMovieReviews(movie.getId(), ApiKey.getApiKey());
        call.enqueue(new Callback<MovieReviewsResponse>() {
            @Override
            public void onResponse(Call<MovieReviewsResponse> call, Response<MovieReviewsResponse> response) {
                reviews = response.body().getResults();
                updateReviewsInfo();
            }

            @Override
            public void onFailure(Call<MovieReviewsResponse> call, Throwable t) {

            }
        });
    }

    private void getTrailers() {
        Call<VideosResponse> call = apiService.getMovieVidz(movie.getId(), ApiKey.getApiKey());
        call.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                trailers = response.body().getResults();
                updateTrailersInfo();
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {
                //showTrailerNotFound(rootView);
            }
        });
    }

    /**
     * update the views with the movie object
     */
    private void updateMovieInfo() {
        if (movie != null) {
            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).fit().into(poster);
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            votes.setText(movie.getVoteAverage());
            releaseDate.setText(movie.getReleaseDate());
        }
    }

    /**
     * Find the required views from the given view
     *
     * @param view is the root view which contains required views
     */
    private void findViews(View view) {

        poster = ButterKnife.findById(view, R.id.detail_poster);
        title = ButterKnife.findById(view, R.id.detail_title);
        overview = ButterKnife.findById(view, R.id.overview);
        releaseDate = ButterKnife.findById(view, R.id.release_date);
        votes = ButterKnife.findById(view, R.id.detail_vote);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        FluentCursor cursor = ProviderAction.query(MoviesContract.MOVIES_URI).projection(MoviesContract.MoviesEntry._ID)
                .where(MoviesContract.MoviesEntry._ID + "=?", movie.getId()).perform(getContext().getContentResolver());
        if (!isMovieIdInCursor(movie.getId(), cursor))
            inflater.inflate(R.menu.add_menu, menu);
    }

    public boolean isMovieIdInCursor(int movieId, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(MoviesContract.MoviesEntry.INDEX_ID);
            if (id == movieId) {
                return true;
            }
        }
        return false;
    }
}
