package com.pontes.andre.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = getMovie();

        TextView txtMovieName = (TextView) findViewById(R.id.textview_movie_name);
        txtMovieName.setText(movie.getTitle());

        TextView txtMovieSynopsis = (TextView) findViewById(R.id.textView_synopsis);
        txtMovieSynopsis.setText(movie.getSynopsis());

        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        rating.setRating(movie.getVoteAvg());

        ImageView imageView = (ImageView) findViewById(R.id.imageView_poster);

        Picasso.with(this)
                .load(movie.getCompletePosterUrl())
                .into(imageView);

        setTitle("Synopsis");
    }

    private Movie getMovie()
    {
        return (Movie) this.getIntent().getSerializableExtra("movie");
    }
}
