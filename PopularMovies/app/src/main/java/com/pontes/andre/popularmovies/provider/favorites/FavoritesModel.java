package com.pontes.andre.popularmovies.provider.favorites;

import com.pontes.andre.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code favorites} table.
 */
public interface FavoritesModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();
}
