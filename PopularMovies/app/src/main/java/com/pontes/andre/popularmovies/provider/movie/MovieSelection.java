package com.pontes.andre.popularmovies.provider.movie;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.pontes.andre.popularmovies.provider.base.AbstractSelection;

import java.util.Date;

public class MovieSelection extends AbstractSelection<MovieSelection> {
    @Override
    protected Uri baseUri() {
        return MovieColumns.CONTENT_URI;
    }

    public MovieCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    public MovieCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    public MovieCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    public MovieCursor query(Context context) {
        return query(context, null);
    }


    public MovieSelection id(long... value) {
        addEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection idNot(long... value) {
        addNotEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection orderById(boolean desc) {
        orderBy("movie." + MovieColumns._ID, desc);
        return this;
    }

    public MovieSelection orderById() {
        return orderById(false);
    }

    public MovieSelection title(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection orderByTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public MovieSelection orderByTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public MovieSelection posterUrl(String... value) {
        addEquals(MovieColumns.POSTER_URL, value);
        return this;
    }

    public MovieSelection posterUrlNot(String... value) {
        addNotEquals(MovieColumns.POSTER_URL, value);
        return this;
    }

    public MovieSelection posterUrlLike(String... value) {
        addLike(MovieColumns.POSTER_URL, value);
        return this;
    }

    public MovieSelection posterUrlContains(String... value) {
        addContains(MovieColumns.POSTER_URL, value);
        return this;
    }

    public MovieSelection posterUrlStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_URL, value);
        return this;
    }

    public MovieSelection posterUrlEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_URL, value);
        return this;
    }

    public MovieSelection orderByPosterUrl(boolean desc) {
        orderBy(MovieColumns.POSTER_URL, desc);
        return this;
    }

    public MovieSelection orderByPosterUrl() {
        orderBy(MovieColumns.POSTER_URL, false);
        return this;
    }

    public MovieSelection synopsis(String... value) {
        addEquals(MovieColumns.SYNOPSIS, value);
        return this;
    }

    public MovieSelection synopsisNot(String... value) {
        addNotEquals(MovieColumns.SYNOPSIS, value);
        return this;
    }

    public MovieSelection synopsisLike(String... value) {
        addLike(MovieColumns.SYNOPSIS, value);
        return this;
    }

    public MovieSelection synopsisContains(String... value) {
        addContains(MovieColumns.SYNOPSIS, value);
        return this;
    }

    public MovieSelection synopsisStartsWith(String... value) {
        addStartsWith(MovieColumns.SYNOPSIS, value);
        return this;
    }

    public MovieSelection synopsisEndsWith(String... value) {
        addEndsWith(MovieColumns.SYNOPSIS, value);
        return this;
    }

    public MovieSelection orderBySynopsis(boolean desc) {
        orderBy(MovieColumns.SYNOPSIS, desc);
        return this;
    }

    public MovieSelection orderBySynopsis() {
        orderBy(MovieColumns.SYNOPSIS, false);
        return this;
    }

    public MovieSelection voteAvgInTen(Float... value) {
        addEquals(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieSelection voteAvgInTenNot(Float... value) {
        addNotEquals(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieSelection voteAvgInTenGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieSelection voteAvgInTenGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieSelection voteAvgInTenLt(float value) {
        addLessThan(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieSelection voteAvgInTenLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVG_IN_TEN, value);
        return this;
    }

    public MovieSelection orderByVoteAvgInTen(boolean desc) {
        orderBy(MovieColumns.VOTE_AVG_IN_TEN, desc);
        return this;
    }

    public MovieSelection orderByVoteAvgInTen() {
        orderBy(MovieColumns.VOTE_AVG_IN_TEN, false);
        return this;
    }

    public MovieSelection releaseDate(Date... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateNot(Date... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDate(long... value) {
        addEquals(MovieColumns.RELEASE_DATE, toObjectArray(value));
        return this;
    }

    public MovieSelection releaseDateAfter(Date value) {
        addGreaterThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateAfterEq(Date value) {
        addGreaterThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateBefore(Date value) {
        addLessThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateBeforeEq(Date value) {
        addLessThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection orderByReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public MovieSelection orderByReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public MovieSelection movieId(long... value) {
        addEquals(MovieColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieSelection movieIdNot(long... value) {
        addNotEquals(MovieColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieSelection movieIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieSelection movieIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieSelection movieIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieSelection orderByMovieId(boolean desc) {
        orderBy(MovieColumns.MOVIE_ID, desc);
        return this;
    }

    public MovieSelection orderByMovieId() {
        orderBy(MovieColumns.MOVIE_ID, false);
        return this;
    }

    public MovieSelection favorite(boolean value) {
        addEquals(MovieColumns.FAVORITE, toObjectArray(value));
        return this;
    }

    public MovieSelection orderByFavorite(boolean desc) {
        orderBy(MovieColumns.FAVORITE, desc);
        return this;
    }

    public MovieSelection orderByFavorite() {
        orderBy(MovieColumns.FAVORITE, false);
        return this;
    }
}
