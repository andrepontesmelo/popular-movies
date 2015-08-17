package com.pontes.andre.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FetchMoviesTask extends AsyncTask<OrderEnum, Void, ArrayList<Movie>> {
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private ImageAdapter imageAdapter;
    private Context context;
    private GridView gridView;

    public FetchMoviesTask(ImageAdapter imageAdapter, GridView gridView, Context context)
    {
        this.imageAdapter = imageAdapter;
        this.gridView = gridView;
        this.context = context;
    }

    @Override
    public ArrayList<Movie> doInBackground(OrderEnum... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String moviesJsonStr = null;

        try {
            URL url = new URL(MovieDbApi.getInstance().getUrl(params[0]));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null)
                return null;

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }

            if (buffer.length() == 0)
                return null;

            moviesJsonStr  = buffer.toString();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return new MoviesJsonParser().Parse(moviesJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Movie> movies) {

        if (movies != null) {
            imageAdapter = new ImageAdapter(context, movies);

            gridView.setAdapter(imageAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    Intent details = new Intent(context, DetailActivity.class);

                    details.putExtra("movie", movies.get(position));

                    context.startActivity(details);
                }
            });
        } else
            Toast.makeText(context, "Internet not available", Toast.LENGTH_LONG).show();



        super.onPostExecute(movies);
    }
}
