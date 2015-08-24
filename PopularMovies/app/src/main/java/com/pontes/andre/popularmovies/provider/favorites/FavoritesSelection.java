package com.pontes.andre.popularmovies.provider.favorites;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.pontes.andre.popularmovies.provider.base.AbstractSelection;

/**
 * Selection for the {@code favorites} table.
 */
public class FavoritesSelection extends AbstractSelection<FavoritesSelection> {
    @Override
    protected Uri baseUri() {
        return FavoritesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoritesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoritesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoritesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoritesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoritesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoritesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoritesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoritesCursor query(Context context) {
        return query(context, null);
    }


    public FavoritesSelection id(long... value) {
        addEquals("favorites." + FavoritesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoritesSelection idNot(long... value) {
        addNotEquals("favorites." + FavoritesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoritesSelection orderById(boolean desc) {
        orderBy("favorites." + FavoritesColumns._ID, desc);
        return this;
    }

    public FavoritesSelection orderById() {
        return orderById(false);
    }

    public FavoritesSelection movieId(long... value) {
        addEquals(FavoritesColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public FavoritesSelection movieIdNot(long... value) {
        addNotEquals(FavoritesColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public FavoritesSelection movieIdGt(long value) {
        addGreaterThan(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdLt(long value) {
        addLessThan(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdLtEq(long value) {
        addLessThanOrEquals(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection orderByMovieId(boolean desc) {
        orderBy(FavoritesColumns.MOVIE_ID, desc);
        return this;
    }

    public FavoritesSelection orderByMovieId() {
        orderBy(FavoritesColumns.MOVIE_ID, false);
        return this;
    }
}
