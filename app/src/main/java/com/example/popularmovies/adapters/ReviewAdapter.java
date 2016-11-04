package com.example.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.models.MovieReviewsResponse;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by peace on 4/11/16.
 */

public class ReviewAdapter extends ArrayAdapter<MovieReviewsResponse.MovieReviews> {

    Context context;
    MovieReviewsResponse.MovieReviews review;

    public ReviewAdapter(Context context, List<MovieReviewsResponse.MovieReviews> reviewsList) {
        super(context, 0, reviewsList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reviews_view, parent, false);
        }
        review = getItem(position);
        TextView author = ButterKnife.findById(convertView, R.id.author);
        TextView reviewText = ButterKnife.findById(convertView, R.id.review);
        author.setText(review.getAuthor());
        reviewText.setText(review.getContent());
        return convertView;
    }

}
