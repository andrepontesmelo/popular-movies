package com.pontes.andre.popularmovies.model;


import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.pontes.andre.popularmovies.provider.favorites.FavoritesColumns;
import com.pontes.andre.popularmovies.provider.favorites.FavoritesContentValues;
import com.pontes.andre.popularmovies.provider.favorites.FavoritesCursor;
import com.pontes.andre.popularmovies.provider.favorites.FavoritesSelection;

import java.util.ArrayList;

public class Favorites {
    private final String LOG_TAG = Favorites.class.getSimpleName();

    private static Favorites ourInstance = new Favorites();

    public static Favorites getInstance(){
        return ourInstance;
        }

    private Favorites() {}

    public long insert(long movieId, Context context) {
        Log.v(LOG_TAG, "insert " + movieId);
        FavoritesContentValues content = new FavoritesContentValues();

        content.putMovieId(movieId);
        Uri uri = content.insert(context.getContentResolver());

        return ContentUris.parseId(uri);
    }

    public ArrayList<Long> getAll(Context context) {
        Log.v(LOG_TAG, "getAll()");
        ArrayList<Long> result = new ArrayList<>();

        FavoritesSelection where = new FavoritesSelection();

        Cursor c = context.getContentResolver().query(FavoritesColumns.CONTENT_URI, null,
                where.sel(), where.args(), null);

        c.moveToFirst();

        while (c.getCount() > 0) {
            FavoritesCursor mc = new FavoritesCursor(c);

            result.add(mc.getMovieId());

            if (c.isLast())
                break;
            else
                c.moveToNext();
        }

        Log.v(LOG_TAG, "returned " + result.size());

        return result;
    }

    public boolean isFavorite(long movieId, Context context) {

        Log.v(LOG_TAG, "is favorite ? " + movieId);
        FavoritesSelection where = new FavoritesSelection();
        where.movieId(movieId);

        Cursor c = context.getContentResolver().query(FavoritesColumns.CONTENT_URI, null,
                where.sel(), where.args(), null);

        return c.getCount() > 0;
    }

    public int remove(long movieId, Context context) {
        Log.v(LOG_TAG, "Remove " + movieId);
        FavoritesSelection where = new FavoritesSelection();
        where.movieId(movieId);

        return where.delete(context.getContentResolver());
    }
}
