package com.pontes.andre.popularmovies;

import java.util.ArrayList;

public interface OnTrailerFetchListener {
    void onTrailerTaskCompleted(ArrayList<String> listUrls);
}
