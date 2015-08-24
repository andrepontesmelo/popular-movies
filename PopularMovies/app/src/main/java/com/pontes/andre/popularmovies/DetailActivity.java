package com.pontes.andre.popularmovies;

import android.content.Intent;
import android.graphics.Color;
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

import com.pontes.andre.popularmovies.model.Favorites;
import com.pontes.andre.popularmovies.model.Movie;
import com.pontes.andre.popularmovies.model.Review;
import com.pontes.andre.popularmovies.net.FetchReviewTask;
import com.pontes.andre.popularmovies.net.FetchTrailerTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements OnReviewFetchListener, OnTrailerFetchListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Movie movie = getMovie();

        final ImageButton favorite = (ImageButton) findViewById(R.id.imageButton_favorite);
        updateFavoriteStar(favorite,
                Favorites.getInstance().isFavorite(movie.getId(), favorite.getContext()));

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Favorites db = Favorites.getInstance();

                boolean isFavorite = db.isFavorite(movie.getId(), favorite.getContext());

                if (isFavorite)
                    db.remove(movie.getId(), favorite.getContext());
                else
                    db.insert(movie.getId(), favorite.getContext());

                isFavorite = !isFavorite;

                updateFavoriteStar(favorite, isFavorite);
            }
        });

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

        setTitle(getString(R.string.synopsis));

        FetchTrailerTask taskTrailer = new FetchTrailerTask(this);
        taskTrailer.execute(movie.getId());

        FetchReviewTask taskReview = new FetchReviewTask(this);
        taskReview .execute(movie.getId());
    }

    private void updateFavoriteStar(ImageButton btn, boolean isFavorite) {

        btn.setColorFilter(isFavorite ? Color.RED : Color.WHITE);
    }

    private Movie getMovie() {

        return (Movie) this.getIntent().getParcelableExtra("movie");
    }

    @Override
    public void onTrailerTaskCompleted(ArrayList<String> listUrls) {

        if (listUrls != null) {

            LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.linear_details);

            for (final String url : listUrls) {

                ImageButton newButton = (ImageButton) getLayoutInflater().inflate(R.layout.button_youtube, null);

                mainLinearLayout.addView(newButton);

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

    @Override
    public void onReviewTaskCompleted(ArrayList<Review> reviews) {

        if (reviews != null) {

            LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.linear_details);

            for (final Review review : reviews) {

                LinearLayout newLayout = (LinearLayout)
                        getLayoutInflater().inflate(R.layout.linear_review_item, null);

                TextView author = (TextView) newLayout.findViewById(R.id.textview_review_author);
                author.setText(review.getAuthor());

                TextView content = (TextView) newLayout.findViewById(R.id.textview_review_content);
                content.setText(review.getContent());

                mainLinearLayout.addView(newLayout);
            }

        } else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.no_internet), Toast.LENGTH_LONG);
        }
    }
}
