package com.pontes.andre.popularmovies.provider.review;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pontes.andre.popularmovies.provider.base.AbstractContentValues;

public class ReviewContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ReviewColumns.CONTENT_URI;
    }

    public int update(ContentResolver contentResolver, @Nullable ReviewSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public int update(Context context, @Nullable ReviewSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ReviewContentValues putMovieId(long value) {
        mContentValues.put(ReviewColumns.MOVIE_ID, value);
        return this;
    }


    public ReviewContentValues putAuthor(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("author must not be null");
        mContentValues.put(ReviewColumns.AUTHOR, value);
        return this;
    }


    public ReviewContentValues putContent(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("content must not be null");
        mContentValues.put(ReviewColumns.CONTENT, value);
        return this;
    }

}
