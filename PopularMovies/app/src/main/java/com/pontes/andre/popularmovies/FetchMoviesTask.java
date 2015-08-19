package com.pontes.andre.popularmovies;

import android.os.AsyncTask;

import java.util.ArrayList;

public class FetchMoviesTask extends AsyncTask<OrderEnum, Void, ArrayList<Movie>> {
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private ICompletableTask listener;

    public FetchMoviesTask(ICompletableTask listener)
    {
        this.listener = listener;
    }

    @Override
    public ArrayList<Movie> doInBackground(OrderEnum... params) {

        String url = MovieDbApi.getInstance().getUrl(params[0]);

        String moviesJsonStr = JsonFetcher.getInstance().getJsonStr(url);

        if (moviesJsonStr == null)
            return null;

        try {
            return new MoviesJsonParser().Parse(moviesJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Movie> movies) {

        listener.onTaskCompleted(movies);

        super.onPostExecute(movies);
    }
}
