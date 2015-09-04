package com.pontes.andre.popularmovies.provider.trailer;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pontes.andre.popularmovies.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code trailer} table.
 */
public class TrailerCursor extends AbstractCursor implements TrailerModel {
    public TrailerCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TrailerColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public long getMovieId() {
        Long res = getLongOrNull(TrailerColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code youtube_key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getYoutubeKey() {
        String res = getStringOrNull(TrailerColumns.YOUTUBE_KEY);
        if (res == null)
            throw new NullPointerException("The value of 'youtube_key' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
