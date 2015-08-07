package com.pontes.andre.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.sql.Date;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_main_activity, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setAdapter(new ImageAdapter(view.getContext()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent details = new Intent(getActivity(), DetailActivity.class);

                details.putExtra("movie", getMovie());

                startActivity(details);
            }
        });

        return view;
    }

    private Movie getMovie()
    {
        Movie movie = new Movie();
        movie.setTitle("Minions");
        movie.setReleaseDate(Date.valueOf("2015-07-10"));
        movie.setPosterUrl("http://image.tmdb.org/t/p/w500/qARJ35IrJNFzFWQGcyWP4r1jyXE.jpg");
        movie.setVoteAvg(6.5f);
        movie.setSynopsis("Minions Stuart, Kevin and Bob are recruited by Scarlet Overkill, a " +
                " super-villain who, alongside her inventor husband Herb, " +
                " hatches a plot to take over the world");

        return movie;
    }
}
