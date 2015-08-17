package com.pontes.andre.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment implements ICompletableTask {

    private ImageAdapter imageAdapter;
    private GridView gridview;

    private ArrayList<Movie> movies;


    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        updateMovies(getOrder());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("movies", movies);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null)
            movies = (ArrayList<Movie>) savedInstanceState.getSerializable("movies");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_main_activity, container, false);

        gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent details = new Intent(getActivity(), DetailActivity.class);

                startActivity(details);
            }
        });

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
        FetchMoviesTask task = new FetchMoviesTask(this);
        task.execute(order);
    }

    @Override
    public void onTaskCompleted(Object result) {
        final ArrayList<Movie> movies = (ArrayList<Movie>) result;

        final Activity context = getActivity();

        this.movies = movies;

        if (movies != null) {

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
            Toast.makeText(context, "Internet not available", Toast.LENGTH_LONG).show();
    }
}
