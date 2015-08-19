package com.pontes.andre.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.pontes.andre.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;

    private Context context;

    public ImageAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public int getCount() {
        return movies.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 266));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(parent.getContext())
                .load(movies.get(position).getCompletePosterUrl())
                .into(imageView);

        return imageView;
    }
}
