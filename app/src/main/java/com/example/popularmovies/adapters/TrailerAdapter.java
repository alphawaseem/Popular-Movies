package com.example.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies.R;
import com.example.popularmovies.models.VideosResponse;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by peace on 4/11/16.
 */

public class TrailerAdapter extends ArrayAdapter<VideosResponse.Video> {

    Context context;
    VideosResponse.Video video;

    public TrailerAdapter(Context context, List<VideosResponse.Video> videos) {
        super(context, 0, videos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_view, parent, false);
        }
        video = getItem(position);
        TextView textView = ButterKnife.findById(convertView, R.id.name);
        ImageButton imageButton = ButterKnife.findById(convertView, R.id.play_trailer);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, video.getKey(), Toast.LENGTH_SHORT).show();
            }
        });
        textView.setText(video.getName());
        return convertView;
    }

}
