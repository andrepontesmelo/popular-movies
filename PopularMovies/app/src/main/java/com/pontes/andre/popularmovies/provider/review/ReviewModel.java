package com.pontes.andre.popularmovies.provider.review;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

import android.support.annotation.NonNull;

/**
 * Data model for the {@code review} table.
 */
public interface ReviewModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Get the {@code author} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getAuthor();

    /**
     * Get the {@code content} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getContent();
}
