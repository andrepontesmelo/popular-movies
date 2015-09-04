package com.pontes.andre.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.pontes.andre.popularmovies.model.Movie;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity
        implements IObserverFragmentSelect, OnTrailerFetchListener   {

    private boolean mTwoPane;

    private ArrayList<String> listUrls = new ArrayList<>();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTrailerTaskCompleted(ArrayList<String> listUrls) {
        this.listUrls = listUrls;
    }
}
