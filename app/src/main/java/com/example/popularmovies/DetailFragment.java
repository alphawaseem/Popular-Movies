package com.example.popularmovies;


import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies.adapters.ReviewAdapter;
import com.example.popularmovies.adapters.TrailerAdapter;
import com.example.popularmovies.data.MoviesContract;
import com.example.popularmovies.models.Movie;
import com.example.popularmovies.models.MovieReviewsResponse;
import com.example.popularmovies.models.VideosResponse;
import com.example.popularmovies.retrofit.RetrofitApiClient;
import com.example.popularmovies.retrofit.RetrofitApiInterface;
import com.getbase.android.db.cursors.FluentCursor;
import com.getbase.android.db.provider.ProviderAction;
import com.squareup.picasso.Picasso;

import org.chalup.microorm.MicroOrm;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    static List<VideosResponse.Video> trailers;
    static List<MovieReviewsResponse.MovieReviews> reviews;
    static Movie movie;
    View rootView;
    ImageView poster;
    TextView title;
    TextView overview;
    TextView releaseDate;
    TextView votes;
    ListView trailerList;
    ListView reviewList;
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
        ReviewAdapter adapter = new ReviewAdapter(getContext(), reviews);
        reviewList.setAdapter(adapter);

    }

    private void updateTrailersInfo() {

        TrailerAdapter adapter = new TrailerAdapter(getContext(), trailers);
        trailerList.setAdapter(adapter);


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
        trailerList = ButterKnife.findById(rootView, R.id.trailer_placeholder);
        reviewList = ButterKnife.findById(rootView, R.id.reviews_placeholder);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isTrue = super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add_favourite:
                addMovieToDB(movie);
                isTrue = true;
                break;
        }
        return isTrue;
    }

    private long addMovieToDB(Movie movie) {
        long id = 0;
        if (!isMovieInDB(movie.getId())) {
            MicroOrm orm = new MicroOrm();
            ContentValues values = orm.toContentValues(movie);
            Uri uri = ProviderAction.insert(MoviesContract.MOVIES_URI).values(values).perform(getActivity().getContentResolver());
            id = Long.parseLong(uri.getLastPathSegment());
            Toast.makeText(getContext(), "Movie added with ID:" + id, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Already Present", Toast.LENGTH_SHORT).show();
        }
        return id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
    }

    /**
     * Search movie in database by id and return true if present else false
     *
     * @param movieId id of the movie
     * @return true if movie in db else false
     */
    public boolean isMovieInDB(int movieId) {
        FluentCursor cursor = ProviderAction.query(MoviesContract.MOVIES_URI).projection(MoviesContract.MoviesEntry._ID)
                .where(MoviesContract.MoviesEntry._ID + "=?", movie.getId()).perform(getContext().getContentResolver());
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
