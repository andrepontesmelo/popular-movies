package com.pontes.andre.popularmovies.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.pontes.andre.popularmovies.BuildConfig;
import com.pontes.andre.popularmovies.provider.movie.MovieColumns;
import com.pontes.andre.popularmovies.provider.review.ReviewColumns;
import com.pontes.andre.popularmovies.provider.trailer.TrailerColumns;

public class PopularMovieSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PopularMovieSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "popularmovies.db";
    private static final int DATABASE_VERSION = 1;
    private static PopularMovieSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final PopularMovieSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE IF NOT EXISTS "
            + MovieColumns.TABLE_NAME + " ( "
            + MovieColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieColumns.TITLE + " TEXT NOT NULL, "
            + MovieColumns.POSTER_URL + " TEXT NOT NULL, "
            + MovieColumns.SYNOPSIS + " TEXT NOT NULL, "
            + MovieColumns.VOTE_AVG_IN_TEN + " REAL, "
            + MovieColumns.RELEASE_DATE + " INTEGER NOT NULL, "
            + MovieColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + MovieColumns.FAVORITE + " INTEGER NOT NULL "
            + " );";

    public static final String SQL_CREATE_INDEX_MOVIE_MOVIE_ID = "CREATE INDEX IDX_MOVIE_MOVIE_ID "
            + " ON " + MovieColumns.TABLE_NAME + " ( " + MovieColumns.MOVIE_ID + " );";

    public static final String SQL_CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS "
            + ReviewColumns.TABLE_NAME + " ( "
            + ReviewColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ReviewColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + ReviewColumns.AUTHOR + " TEXT NOT NULL, "
            + ReviewColumns.CONTENT + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_TABLE_TRAILER = "CREATE TABLE IF NOT EXISTS "
            + TrailerColumns.TABLE_NAME + " ( "
            + TrailerColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrailerColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + TrailerColumns.YOUTUBE_KEY + " TEXT NOT NULL "
            + " );";

    // @formatter:on

    public static PopularMovieSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PopularMovieSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PopularMovieSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PopularMovieSQLiteOpenHelper(context);
    }

    private PopularMovieSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PopularMovieSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PopularMovieSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PopularMovieSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PopularMovieSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PopularMovieSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_INDEX_MOVIE_MOVIE_ID);
        db.execSQL(SQL_CREATE_TABLE_REVIEW);
        db.execSQL(SQL_CREATE_TABLE_TRAILER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
