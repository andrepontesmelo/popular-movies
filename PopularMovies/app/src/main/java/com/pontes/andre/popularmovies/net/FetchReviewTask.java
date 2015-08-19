package com.pontes.andre.popularmovies.net;

import android.os.AsyncTask;

import com.pontes.andre.popularmovies.ICompletableTask;
import com.pontes.andre.popularmovies.model.Review;

import java.util.ArrayList;

public class FetchReviewTask extends AsyncTask<Long, Void, ArrayList<Review>> {
    private final String LOG_TAG = FetchReviewTask.class.getSimpleName();

    private ICompletableTask listener;

    public FetchReviewTask(ICompletableTask listener)
    {
        this.listener = listener;
    }

    @Override
    public ArrayList<Review> doInBackground(Long... params) {

        String url = MovieDbApi.getInstance().getTrailerUrl(params[0]);

        String moviesJsonStr = JsonFetcher.getInstance().getJsonStr(url);

        if (moviesJsonStr == null)
            return null;

        try {
            return new ReviewJsonParser().Parse(moviesJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Review> movies) {

        listener.onTaskCompleted(movies);

        super.onPostExecute(movies);
    }
}
