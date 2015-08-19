package com.pontes.andre.popularmovies.provider.movie;

import android.net.Uri;
import android.provider.BaseColumns;

import com.pontes.andre.popularmovies.provider.PopularMovieProvider;

public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(PopularMovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    public static final String _ID = BaseColumns._ID;

    public static final String TITLE = "title";

    public static final String POSTER_URL = "poster_url";

    public static final String SYNOPSIS = "synopsis";

    public static final String VOTE_AVG_IN_TEN = "vote_avg_in_ten";

    public static final String RELEASE_DATE = "release_date";

    public static final String MOVIE_ID = "movie_id";

    public static final String FAVORITE = "favorite";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TITLE,
            POSTER_URL,
            SYNOPSIS,
            VOTE_AVG_IN_TEN,
            RELEASE_DATE,
            MOVIE_ID,
            FAVORITE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(POSTER_URL) || c.contains("." + POSTER_URL)) return true;
            if (c.equals(SYNOPSIS) || c.contains("." + SYNOPSIS)) return true;
            if (c.equals(VOTE_AVG_IN_TEN) || c.contains("." + VOTE_AVG_IN_TEN)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(FAVORITE) || c.contains("." + FAVORITE)) return true;
        }
        return false;
    }

}
