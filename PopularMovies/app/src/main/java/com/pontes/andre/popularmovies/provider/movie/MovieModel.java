package com.pontes.andre.popularmovies.provider.movie;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code movie} table.
 */
public interface MovieModel extends BaseModel {

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTitle();

    /**
     * Get the {@code poster_url} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getPosterUrl();

    /**
     * Get the {@code synopsis} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSynopsis();

    /**
     * Get the {@code vote_avg_in_ten} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getVoteAvgInTen();

    /**
     * Get the {@code release_date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getReleaseDate();

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Get the {@code favorite} value.
     */
    boolean getFavorite();
}
