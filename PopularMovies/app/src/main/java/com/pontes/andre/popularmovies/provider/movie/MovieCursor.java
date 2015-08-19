package com.pontes.andre.popularmovies.provider.movie;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pontes.andre.popularmovies.provider.base.AbstractCursor;

import java.util.Date;

public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    @NonNull
    public String getTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    @NonNull
    public String getPosterUrl() {
        String res = getStringOrNull(MovieColumns.POSTER_URL);
        if (res == null)
            throw new NullPointerException("The value of 'poster_url' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    @NonNull
    public String getSynopsis() {
        String res = getStringOrNull(MovieColumns.SYNOPSIS);
        if (res == null)
            throw new NullPointerException("The value of 'synopsis' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    @Nullable
    public Float getVoteAvgInTen() {
        Float res = getFloatOrNull(MovieColumns.VOTE_AVG_IN_TEN);
        return res;
    }

    @NonNull
    public Date getReleaseDate() {
        Date res = getDateOrNull(MovieColumns.RELEASE_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'release_date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    public long getMovieId() {
        Long res = getLongOrNull(MovieColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    public boolean getFavorite() {
        Boolean res = getBooleanOrNull(MovieColumns.FAVORITE);
        if (res == null)
            throw new NullPointerException("The value of 'favorite' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
