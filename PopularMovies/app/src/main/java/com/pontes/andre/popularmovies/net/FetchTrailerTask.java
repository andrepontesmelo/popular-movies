package com.pontes.andre.popularmovies.net;

import android.os.AsyncTask;

import com.pontes.andre.popularmovies.OnTrailerFetchListener;

import java.util.ArrayList;

public class FetchTrailerTask extends AsyncTask<Long, Void, ArrayList<String>> {
    private final String LOG_TAG = FetchTrailerTask.class.getSimpleName();

    private OnTrailerFetchListener listener;

    public FetchTrailerTask(OnTrailerFetchListener listener)
    {
        this.listener = listener;
    }

    @Override
    public ArrayList<String> doInBackground(Long... params) {

        String url = MovieDbApi.getInstance().getTrailerUrl(params[0]);

        String moviesJsonStr = JsonFetcher.getInstance().getJsonStr(url);

        if (moviesJsonStr == null)
            return null;

        try {
            return new TrailerJsonParser().Parse(moviesJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<String> movies) {

        listener.onTrailerTaskCompleted(movies);

        super.onPostExecute(movies);
    }
}
