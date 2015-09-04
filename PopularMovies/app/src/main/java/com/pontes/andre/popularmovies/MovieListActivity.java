package com.pontes.andre.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.pontes.andre.popularmovies.model.Movie;

public class MovieListActivity extends FragmentActivity
        implements IObserverFragmentSelect {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        if (findViewById(R.id.movie_detail_container) != null)
            mTwoPane = true;
    }

    @Override
    public void onItemSelected(Movie movie) {

        if (mTwoPane) {
            Bundle arguments = new Bundle();

            MovieDetailFragment fragment = new MovieDetailFragment();

            arguments.putParcelable("movie", movie);

            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, MovieDetailActivity.class);

            detailIntent.putExtra("movie", movie);

            startActivity(detailIntent);
        }
    }
}
