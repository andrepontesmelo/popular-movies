package com.pontes.andre.popularmovies;

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

public class MainActivityFragment extends Fragment {

    private ImageAdapter imageAdapter;
    private GridView gridview;

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        updateMovies(getOrder());
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
        FetchMoviesTask task = new FetchMoviesTask(imageAdapter, gridview, getActivity());
        task.execute(order);
    }

}
