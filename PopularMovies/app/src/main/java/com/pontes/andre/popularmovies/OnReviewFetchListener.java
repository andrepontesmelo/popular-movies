package com.pontes.andre.popularmovies;

import com.pontes.andre.popularmovies.model.Review;

import java.util.ArrayList;

public interface OnReviewFetchListener {
    void onReviewTaskCompleted(ArrayList<Review> reviews);
}
