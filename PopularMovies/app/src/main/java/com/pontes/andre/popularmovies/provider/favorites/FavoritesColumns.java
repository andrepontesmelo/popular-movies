package com.pontes.andre.popularmovies.provider.favorites;

import android.net.Uri;
import android.provider.BaseColumns;

import com.pontes.andre.popularmovies.provider.PopularMovieProvider;

/**
 * Columns for the {@code favorites} table.
 */
public class FavoritesColumns implements BaseColumns {
    public static final String TABLE_NAME = "favorites";
    public static final Uri CONTENT_URI = Uri.parse(PopularMovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

}
