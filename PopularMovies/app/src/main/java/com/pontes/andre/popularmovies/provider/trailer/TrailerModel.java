package com.pontes.andre.popularmovies.provider.trailer;

import android.support.annotation.NonNull;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

public interface TrailerModel extends BaseModel {

    long getMovieId();

    @NonNull
    String getYoutubeKey();
}
