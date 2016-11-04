package com.example.popularmovies.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

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
                String id = video.getKey();
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + id));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
        textView.setText(video.getName());
        return convertView;
    }

}
