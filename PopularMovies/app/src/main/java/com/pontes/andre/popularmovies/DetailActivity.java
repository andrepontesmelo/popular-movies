package com.pontes.andre.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView txtMovieName = (TextView) findViewById(R.id.textview_movie_name);
        txtMovieName.setText(getMovie().getTitle());
    }

    private Movie getMovie()
    {
        return (Movie) this.getIntent().getSerializableExtra("movie");
    }
}
