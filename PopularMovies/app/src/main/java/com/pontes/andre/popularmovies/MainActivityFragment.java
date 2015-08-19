package com.pontes.andre.popularmovies;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.pontes.andre.popularmovies.model.Movie;
import com.pontes.andre.popularmovies.model.OrderEnum;
import com.pontes.andre.popularmovies.net.FetchMovieTask;
import com.pontes.andre.popularmovies.provider.movie.MovieColumns;
import com.pontes.andre.popularmovies.provider.movie.MovieContentValues;
import com.pontes.andre.popularmovies.provider.movie.MovieCursor;
import com.pontes.andre.popularmovies.provider.movie.MovieSelection;

import java.util.ArrayList;
import java.util.Date;

public class MainActivityFragment extends Fragment implements ICompletableTask {

    private ImageAdapter imageAdapter;
    private GridView gridview;

    private ArrayList<Movie> movies = null;

    private OrderEnum lastOrder;

    private boolean userHasChangedOrder()
    {
        return lastOrder != null && (lastOrder != getOrder());
    }

    public MainActivityFragment() {
    }

    private long insertMovie(Context context) {
        Movie m = new Movie();
        m.setTitle("My movie");
        m.setPosterUrl("poster");
        m.setSynopsis("");
        m.setReleaseDate(new Date());
        m.setVoteAvgInTen(0);
        m.setFavorite(false);

        m.setId(2);

        MovieContentValues values = m.getDbContents();

        Uri uri = values.insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }
    private void readMovie(Context context) {
        MovieSelection where = new MovieSelection();

        Log.v("A", "BEFORE CURSOR ");
        Cursor c = context.getContentResolver().query(MovieColumns.CONTENT_URI, null, where.sel(), where.args(), null);
        Log.v("A", "will move to first ");
        c.moveToFirst();
        Log.v("A", "move to first ok. ");

        while (true) {
            MovieCursor mc = new MovieCursor(c);
            Log.v("B", mc.getSynopsis());
            Log.v("B", mc.getTitle());
            Log.v("B", String.valueOf(mc.getId()));

            if (c.isLast())
                break;
            else
                c.moveToNext();
        }

        Log.v("A", "new moviecursor() ok ");

        Log.v("A", "log get synopsis ok ");
        Log.v("A", "finish()");
    }

    @Override
    public void onStart() {
        super.onStart();

        if (userHasChangedOrder())
            updateMovies(getOrder());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("movies", movies);
        outState.putSerializable("lastOrder", lastOrder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v("A", "Will read movie");
        //insertMovie(inflater.getContext());
        readMovie(inflater.getContext());

        Log.v("A", "read.");

        View view =  inflater.inflate(R.layout.fragment_main_activity, container, false);

        gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent details = new Intent(getActivity(), DetailActivity.class);

                startActivity(details);
            }
        });

        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList("movies");
            lastOrder = (OrderEnum) savedInstanceState.getSerializable("lastOrder");
            updateAdapter((ArrayList<Movie>) movies);
        } else
            updateMovies(getOrder());

        return view;
    }

    private OrderEnum getOrder()
    {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        String sortBy =  preferences.getString(getString(R.string.pref_sort_key), getString(R.string.pref_sort_default));

        switch (sortBy)
        {
            case ("Popularity"):
                return OrderEnum.Popularity;
            case "Highest Rated":
                return OrderEnum.HighestRated;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void updateMovies(OrderEnum order) {

        FetchMovieTask task = new FetchMovieTask(this);
        task.execute(order);

        lastOrder = order;
    }

    public void updateAdapter(final ArrayList<Movie> movies) {
        {
            final Activity context = getActivity();

            if (movies != null) {

                this.movies = movies;

                imageAdapter = new ImageAdapter(context, movies);

                gridview.setAdapter(imageAdapter);

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                        Intent details = new Intent(context, DetailActivity.class);

                        details.putExtra("movie", movies.get(position));

                        context.startActivity(details);
                    }
                });

            } else
                Toast.makeText(context, getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTaskCompleted(Object movieArray) {
        updateAdapter((ArrayList<Movie>) movieArray);
    }
}
