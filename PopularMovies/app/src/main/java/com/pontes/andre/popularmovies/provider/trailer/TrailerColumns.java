package com.pontes.andre.popularmovies.provider.trailer;

import android.net.Uri;
import android.provider.BaseColumns;

import com.pontes.andre.popularmovies.provider.PopularMovieProvider;

/**
 * Columns for the {@code trailer} table.
 */
public class TrailerColumns implements BaseColumns {
    public static final String TABLE_NAME = "trailer";
    public static final Uri CONTENT_URI = Uri.parse(PopularMovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String YOUTUBE_KEY = "youtube_key";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            YOUTUBE_KEY
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(YOUTUBE_KEY) || c.contains("." + YOUTUBE_KEY)) return true;
        }
        return false;
    }

}
