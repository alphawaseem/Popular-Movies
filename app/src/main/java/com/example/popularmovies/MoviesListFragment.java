package com.example.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.karim.MaterialTabs;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesListFragment extends Fragment {


    static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.movie_pager, container, false);

        ViewPager pager;
        MaterialTabs tabs;

        pager = ButterKnife.findById(rootView, R.id.pager);
        pager.setAdapter(new MoviePagerAdapter(getActivity(), getActivity().getSupportFragmentManager()));
        tabs = ButterKnife.findById(rootView, R.id.tabs);
        tabs.setViewPager(pager);

        return rootView;
    }

}
