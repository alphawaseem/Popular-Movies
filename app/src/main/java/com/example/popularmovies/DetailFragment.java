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

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_movie_card, container, false);

        ImageView photo = ButterKnife.findById(view, R.id.detail_poster);
        TextView title = ButterKnife.findById(view, R.id.detail_title);

        Movie movie = getActivity().getIntent().getParcelableExtra("MOVIE");
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).fit().into(photo);
        title.setText(movie.getTitle());

        RetrofitApiInterface apiService =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

        Call<VideosResponse> call = apiService.getMovieVidz(movie.getId(), PopularFragment.API_KEY);
        call.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                videos = response.body().getResults();
                int size = videos.size();
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {

            }
        });
        return view;
    }
}
