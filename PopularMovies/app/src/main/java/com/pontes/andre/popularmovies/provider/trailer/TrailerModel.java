package com.pontes.andre.popularmovies.provider.trailer;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

import android.support.annotation.NonNull;

/**
 * Data model for the {@code trailer} table.
 */
public interface TrailerModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Get the {@code youtube_key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getYoutubeKey();
}
