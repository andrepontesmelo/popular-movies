package com.pontes.andre.popularmovies.provider.trailer;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pontes.andre.popularmovies.provider.base.AbstractContentValues;

public class TrailerContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TrailerColumns.CONTENT_URI;
    }

    public int update(ContentResolver contentResolver, @Nullable TrailerSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public int update(Context context, @Nullable TrailerSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TrailerContentValues putMovieId(long value) {
        mContentValues.put(TrailerColumns.MOVIE_ID, value);
        return this;
    }


    public TrailerContentValues putYoutubeKey(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("youtubeKey must not be null");
        mContentValues.put(TrailerColumns.YOUTUBE_KEY, value);
        return this;
    }

}
