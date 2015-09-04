package com.pontes.andre.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private ArrayList<String> listUrls;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = getContext();

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

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

        FetchTrailerTask taskTrailer = new FetchTrailerTask(this);
        taskTrailer.execute(movie.getId());

        FetchReviewTask taskReview = new FetchReviewTask(this);
        taskReview.execute(movie.getId());

        super.onCreateView(inflater, container, savedInstanceState);
        return view;
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

        if (reviews != null) {

            LinearLayout mainLinearLayout = (LinearLayout) getView().findViewById(R.id.linear_details);

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
        }
    }

    @Override
    public void onTrailerTaskCompleted(ArrayList<String> listUrls) {

        if (listUrls != null) {
            this.listUrls = listUrls;

            LinearLayout mainLinearLayout = (LinearLayout) getView().findViewById(R.id.linear_details);

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
