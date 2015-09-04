package com.pontes.andre.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MovieDetailFragment extends Fragment implements OnReviewFetchListener, OnTrailerFetchListener  {

    public static final String TRAILER = "trailer";
    public static final String REVIEWS = "reviews";

    private static final String LOG_TAG = MovieDetailFragment.class.getSimpleName();

    private ArrayList<String> listUrls;
    private ArrayList<Review> reviews;

    private View view;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = getContext();

        this.view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        final Movie movie = getMovie();

        final ImageButton favorite = (ImageButton) view.findViewById(R.id.imageButton_favorite);


        updateFavoriteStar(favorite,
                Favorites.getInstance().isFavorite(movie.getId(), context));

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Favorites db = Favorites.getInstance();

                boolean isFavorite = db.isFavorite(movie.getId(), context);

                if (isFavorite)
                    db.remove(movie.getId(), context);
                else
                    db.insert(movie.getId(), context);

                isFavorite = !isFavorite;

                updateFavoriteStar(favorite, isFavorite);
            }
        });

        TextView txtMovieName = (TextView) view.findViewById(R.id.textview_movie_name);
        txtMovieName.setText(movie.getTitle());

        TextView txtMovieSynopsis = (TextView) view.findViewById(R.id.textView_synopsis);
        txtMovieSynopsis.setText(movie.getSynopsis());

        RatingBar rating = (RatingBar) view.findViewById(R.id.ratingBar);
        rating.setRating(movie.getVoteAvgInFive());

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_poster);

        Picasso.with(context)
                .load(movie.getCompletePosterUrl())
                .into(imageView);

        getActivity().setTitle(getString(R.string.synopsis));

        if (savedInstanceState == null) {
            Log.v(LOG_TAG, "savedInstance is null. Do the requests =>>> ");

            FetchTrailerTask taskTrailer = new FetchTrailerTask(this);
            taskTrailer.execute(movie.getId());

            FetchReviewTask taskReview = new FetchReviewTask(this);
            taskReview.execute(movie.getId());
        } else
        {
            Log.v(LOG_TAG, "Instance is saved... request is not needed.");

            ArrayList<String> listUrls = savedInstanceState.getStringArrayList(TRAILER);
            ArrayList<Review> reviews = savedInstanceState.getParcelableArrayList(REVIEWS);

            onTrailerTaskCompleted(listUrls);
            onReviewTaskCompleted(reviews);
        }

        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(TRAILER, listUrls);
        outState.putParcelableArrayList(REVIEWS, reviews);
    }

    private void updateFavoriteStar(ImageButton btn, boolean isFavorite) {

        btn.setColorFilter(isFavorite ? Color.RED : Color.WHITE);
    }

    private Movie getMovie() {

        Movie movieTwoPane = this.getArguments().getParcelable("movie");
        Movie movieOnePane = this.getActivity().getIntent().getParcelableExtra("movie");

        if (movieTwoPane != null)
            return movieTwoPane;

        if (movieOnePane != null)
            return movieOnePane;


        return null;
    }


    @Override
    public void onReviewTaskCompleted(ArrayList<Review> reviews) {

        Log.v(LOG_TAG, "onReviewTaskCompleted()");

        if (reviews != null) {
            Log.v(LOG_TAG, "reviews is not null.");

            this.reviews = reviews;

            LinearLayout mainLinearLayout = (LinearLayout) view.findViewById(R.id.linear_details);

            for (final Review review : reviews) {

                LinearLayout newLayout = (LinearLayout)
                        getActivity().getLayoutInflater().inflate(R.layout.linear_review_item, null);

                TextView author = (TextView) newLayout.findViewById(R.id.textview_review_author);
                author.setText(review.getAuthor());

                TextView content = (TextView) newLayout.findViewById(R.id.textview_review_content);
                content.setText(review.getContent());

                mainLinearLayout.addView(newLayout);
            }

        } else {
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_LONG);
            Log.v(LOG_TAG, "reviews not null!!");

        }
    }

    @Override
    public void onTrailerTaskCompleted(ArrayList<String> listUrls) {

        if (listUrls != null) {
            this.listUrls = listUrls;

            LinearLayout mainLinearLayout = (LinearLayout) view.findViewById(R.id.linear_details);

            for (final String url : listUrls) {

                ImageButton newButton = (ImageButton) getActivity().getLayoutInflater().inflate(R.layout.button_youtube, null);

                mainLinearLayout.addView(newButton);

                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                });
            }

        } else {
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_LONG);
        }


        ((OnTrailerFetchListener) getActivity()).onTrailerTaskCompleted(listUrls);
    }

}
