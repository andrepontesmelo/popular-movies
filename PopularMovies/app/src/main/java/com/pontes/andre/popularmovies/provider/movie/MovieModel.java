package com.pontes.andre.popularmovies.provider.movie;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

import java.util.Date;

public interface MovieModel extends BaseModel {

    @NonNull
    String getTitle();

    @NonNull
    String getPosterUrl();

    @NonNull
    String getSynopsis();

    @Nullable
    Float getVoteAvgInTen();

    @NonNull
    Date getReleaseDate();

    long getMovieId();

    boolean getFavorite();
}
