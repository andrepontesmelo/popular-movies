package com.pontes.andre.popularmovies.provider.favorites;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

/**
 * Data model for the {@code favorites} table.
 */
public interface FavoritesModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();
}
