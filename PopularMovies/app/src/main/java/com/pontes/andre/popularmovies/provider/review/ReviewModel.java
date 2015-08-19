package com.pontes.andre.popularmovies.provider.review;

import android.support.annotation.NonNull;

import com.pontes.andre.popularmovies.provider.base.BaseModel;
public interface ReviewModel extends BaseModel {

    long getMovieId();

    @NonNull
    String getAuthor();

    @NonNull
    String getContent();
}
