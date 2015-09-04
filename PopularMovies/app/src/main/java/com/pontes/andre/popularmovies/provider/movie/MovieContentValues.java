package com.pontes.andre.popularmovies.provider.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pontes.andre.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie} table.
 */
public class MovieContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieContentValues putTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("title must not be null");
        mContentValues.put(MovieColumns.TITLE, value);
        return this;
    }


    public MovieContentValues putPosterUrl(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("posterUrl must not be null");
        mContentValues.put(MovieColumns.POSTER_URL, value);
        return this;
    }


    public MovieContentValues putSynopsis(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("synopsis must not be null");
        mContentValues.put(MovieColumns.SYNOPSIS, value);
        return this;
    }


    public MovieContentValues putVoteAvgInTen(@Nullable Float value) {
        mContentValues.put(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieContentValues putVoteAvgInTenNull() {
        mContentValues.putNull(MovieColumns.VOTE_AVG_IN_TEN);
        return this;
    }

    public MovieContentValues putReleaseDate(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("releaseDate must not be null");
        mContentValues.put(MovieColumns.RELEASE_DATE, value.getTime());
        return this;
    }


    public MovieContentValues putReleaseDate(long value) {
        mContentValues.put(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieContentValues putMovieId(long value) {
        mContentValues.put(MovieColumns.MOVIE_ID, value);
        return this;
    }


    public MovieContentValues putFavorite(boolean value) {
        mContentValues.put(MovieColumns.FAVORITE, value);
        return this;
    }

}
