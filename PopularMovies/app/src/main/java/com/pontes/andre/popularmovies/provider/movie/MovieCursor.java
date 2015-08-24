package com.pontes.andre.popularmovies.provider.movie;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pontes.andre.popularmovies.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie} table.
 */
public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code poster_url} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getPosterUrl() {
        String res = getStringOrNull(MovieColumns.POSTER_URL);
        if (res == null)
            throw new NullPointerException("The value of 'poster_url' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code synopsis} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSynopsis() {
        String res = getStringOrNull(MovieColumns.SYNOPSIS);
        if (res == null)
            throw new NullPointerException("The value of 'synopsis' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_avg_in_ten} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getVoteAvgInTen() {
        Float res = getFloatOrNull(MovieColumns.VOTE_AVG_IN_TEN);
        return res;
    }

    /**
     * Get the {@code release_date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getReleaseDate() {
        Date res = getDateOrNull(MovieColumns.RELEASE_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'release_date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public long getMovieId() {
        Long res = getLongOrNull(MovieColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code favorite} value.
     */
    public boolean getFavorite() {
        Boolean res = getBooleanOrNull(MovieColumns.FAVORITE);
        if (res == null)
            throw new NullPointerException("The value of 'favorite' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
