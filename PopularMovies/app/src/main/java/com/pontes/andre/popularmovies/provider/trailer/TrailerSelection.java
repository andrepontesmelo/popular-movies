package com.pontes.andre.popularmovies.provider.trailer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.pontes.andre.popularmovies.provider.base.AbstractSelection;

public class TrailerSelection extends AbstractSelection<TrailerSelection> {
    @Override
    protected Uri baseUri() {
        return TrailerColumns.CONTENT_URI;
    }

    public TrailerCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    public TrailerCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    public TrailerCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    public TrailerCursor query(Context context) {
        return query(context, null);
    }


    public TrailerSelection id(long... value) {
        addEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection idNot(long... value) {
        addNotEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection orderById(boolean desc) {
        orderBy("trailer." + TrailerColumns._ID, desc);
        return this;
    }

    public TrailerSelection orderById() {
        return orderById(false);
    }

    public TrailerSelection movieId(long... value) {
        addEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdNot(long... value) {
        addNotEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdGt(long value) {
        addGreaterThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLt(long value) {
        addLessThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLtEq(long value) {
        addLessThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection orderByMovieId(boolean desc) {
        orderBy(TrailerColumns.MOVIE_ID, desc);
        return this;
    }

    public TrailerSelection orderByMovieId() {
        orderBy(TrailerColumns.MOVIE_ID, false);
        return this;
    }

    public TrailerSelection youtubeKey(String... value) {
        addEquals(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

    public TrailerSelection youtubeKeyNot(String... value) {
        addNotEquals(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

    public TrailerSelection youtubeKeyLike(String... value) {
        addLike(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

    public TrailerSelection youtubeKeyContains(String... value) {
        addContains(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

    public TrailerSelection youtubeKeyStartsWith(String... value) {
        addStartsWith(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

    public TrailerSelection youtubeKeyEndsWith(String... value) {
        addEndsWith(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

    public TrailerSelection orderByYoutubeKey(boolean desc) {
        orderBy(TrailerColumns.YOUTUBE_KEY, desc);
        return this;
    }

    public TrailerSelection orderByYoutubeKey() {
        orderBy(TrailerColumns.YOUTUBE_KEY, false);
        return this;
    }
}
