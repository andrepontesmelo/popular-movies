package com.pontes.andre.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity implements OnTrailerFetchListener   {

    private ArrayList<String> listUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            NavUtils.navigateUpTo(this, new Intent(this, MovieListActivity.class));
            return true;
        }

        if (item.getItemId() == R.id.menu_item_share && listUrls != null) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getAllUrls(listUrls));
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getAllUrls(ArrayList<String> listUrls) {
        StringBuilder str = new StringBuilder();

        boolean first = true;

        for (String s : listUrls) {

            if (!first)
                str.append(" ; ");

            str.append(s);

            first = false;
        }

        return str.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public void onTrailerTaskCompleted(ArrayList<String> listUrls) {
        this.listUrls = listUrls;
    }
}
