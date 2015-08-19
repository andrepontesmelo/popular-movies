package com.pontes.andre.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pontes.andre.popularmovies.model.Movie;
import com.pontes.andre.popularmovies.net.FetchTrailerTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements ICompletableTask {


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
        rating.setRating(movie.getVoteAvgInFive());

        ImageView imageView = (ImageView) findViewById(R.id.imageView_poster);

        Picasso.with(this)
                .load(movie.getCompletePosterUrl())
                .into(imageView);

        setTitle("Synopsis");

        FetchTrailerTask task = new FetchTrailerTask(this);
        task.execute(movie.getId());
    }

    private Movie getMovie() {

        return (Movie) this.getIntent().getParcelableExtra("movie");
    }

    @Override
    public void onTaskCompleted(Object result) {

        if (result != null) {
            ArrayList<String> listUrls = (ArrayList<String>) result;

            for (final String url : listUrls) {

                ImageButton newButton = (ImageButton) getLayoutInflater().inflate(R.layout.button_youtube, null);

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_details);

                linearLayout.addView(newButton);

                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                });
            }

        } else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.no_internet), Toast.LENGTH_LONG);
        }
    }
}
