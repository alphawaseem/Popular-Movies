package com.example.popularmovies;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmovies.data.MoviesContract;
import com.example.popularmovies.models.Movie;
import com.example.popularmovies.myrecyclerview.RecyclerItemClickListener;
import com.example.popularmovies.myrecyclerview.SingleChoiceMode;
import com.example.popularmovies.recyclercursor.CursorMovieAdapter;

import butterknife.ButterKnife;

import static com.example.popularmovies.MyUtils.hideProgressBar;
import static com.example.popularmovies.MyUtils.showFailureMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    final static String TAG = FavFragment.class.getSimpleName();
    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = ApiKey.getApiKey();
    static int mPos = -1;
    CursorMovieAdapter adapter;

    static FavFragment newInstance() {
        FavFragment fragment = new FavFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.recycle_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
    }

    void showMoviesInRecyclerView(View view, final Cursor cursor, final FragmentManager fragmentManager) {

        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView recyclerView;
        recyclerView = ButterKnife.findById(view, R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(view.getContext(), view.getResources().getInteger(R.integer.no_of_columns));
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new CursorMovieAdapter(cursor, view.getContext(), new SingleChoiceMode(), recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        if (mPos != -1) {
            mLayoutManager.scrollToPosition(mPos);
            adapter.onChecked(mPos, true);
        }
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                View detailFragment = ButterKnife.findById(view.getRootView(), R.id.detail_fragment_container);
                mPos = position;
                boolean isCheckedNow = adapter.isChecked(position);
                cursor.moveToPosition(position);
                if (!isCheckedNow) {
                    adapter.onChecked(position, true);
                    view.setActivated(true);
                }
                if (detailFragment != null) {

                    fragmentManager.beginTransaction().replace(R.id.detail_fragment_container, DetailFragment.newInstance(Movie.getFromCursor(cursor))).commit();

                } else {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("MOVIE", Movie.getFromCursor(cursor));
                    view.getContext().startActivity(intent);
                }
            }
        }));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(getContext(), MoviesContract.MOVIES_URI, null,
                null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null) {

            hideProgressBar(getView(), R.id.progress_bar);
            showFailureMessage(getView(), getString(R.string.no_fav_movies));
        } else {
            hideProgressBar(getView(), R.id.progress_bar);
            showMoviesInRecyclerView(getView(), cursor, getActivity().getSupportFragmentManager());
        }
    }


    @Override
    public void onLoaderReset(Loader loader) {
        loader.reset();
    }
}
