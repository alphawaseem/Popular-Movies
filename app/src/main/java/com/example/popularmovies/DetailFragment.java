package com.example.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    static List<VideosResponse.Videos> videos;
    static Movie movie;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    public static DetailFragment newInstance(Movie curMovie) {
        movie = curMovie;
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.detail_movie_card, container, false);

        ImageView photo = ButterKnife.findById(view, R.id.detail_poster);
        TextView title = ButterKnife.findById(view, R.id.detail_title);
        TextView overview = ButterKnife.findById(view, R.id.overview);
        TextView votes = ButterKnife.findById(view, R.id.detail_vote);
        TextView releaseDate = ButterKnife.findById(view, R.id.release_date);

        // if fragment is started by intent then receive movie object by intent
        // else it must  be initialized in newInstance method
        if (movie != null) {
            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).fit().into(photo);
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            votes.setText(movie.getVoteAverage());
            releaseDate.setText(movie.getReleaseDate());
            RetrofitApiInterface apiService =
                    RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

            Call<VideosResponse> call = apiService.getMovieVidz(movie.getId(), ApiKey.getApiKey());
            call.enqueue(new Callback<VideosResponse>() {
                @Override
                public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                    videos = response.body().getResults();
                    TextView textView = ButterKnife.findById(view, R.id.trailer1);
                    textView.setText(videos.get(0).getName());
                }

                @Override
                public void onFailure(Call<VideosResponse> call, Throwable t) {
                    TextView textView = ButterKnife.findById(view, R.id.trailer1);
                    textView.setText(getString(R.string.no_trailer));
                }
            });
        }
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
