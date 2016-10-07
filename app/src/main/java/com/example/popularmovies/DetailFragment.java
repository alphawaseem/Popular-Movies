package com.example.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.detail_poster)
    ImageView poster;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_movie_card, container, false);
        ImageView photo = ButterKnife.findById(view, R.id.detail_poster);

        Movie movie = getActivity().getIntent().getParcelableExtra("MOVIE");
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).fit().into(photo);
        return view;
    }
}
