package com.pontes.andre.popularmovies.model;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.pontes.andre.popularmovies.provider.movie.MovieColumns;
import com.pontes.andre.popularmovies.provider.movie.MovieContentValues;
import com.pontes.andre.popularmovies.provider.movie.MovieCursor;
import com.pontes.andre.popularmovies.provider.movie.MovieSelection;

import java.util.ArrayList;
import java.util.Date;

public class Movie implements Parcelable {

    private String title;
    private String posterUrl;
    private String synopsis;
    private float voteAvgInTen;
    private Date releaseDate;
    private long id;
    private boolean favorite;

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public float getVoteAvgInTen() { return voteAvgInTen; }
    public float getVoteAvgInFive() { return voteAvgInTen / 2; }
    public void setVoteAvgInTen(float voteAvgInTen) {
        this.voteAvgInTen = voteAvgInTen;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCompletePosterUrl() {
        return "http://image.tmdb.org/t/p/w185" + posterUrl;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite;    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(posterUrl);
        out.writeString(synopsis);
        out.writeFloat(voteAvgInTen);
        out.writeSerializable(releaseDate);
        out.writeLong(id);
        out.writeByte((byte) (favorite ? 1 : 0));
    }

    public Movie()
    {
    }

    private Movie(Parcel in) {
        title = in.readString();
        posterUrl = in.readString();
        synopsis = in.readString();
        voteAvgInTen = in.readFloat();
        releaseDate = (Date) in.readSerializable();
        id = in.readLong();
        favorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public MovieContentValues getDbContents() {
        MovieContentValues content = new MovieContentValues();

        content.putTitle(title);
        content.putPosterUrl(posterUrl);
        content.putSynopsis(synopsis);
        content.putVoteAvgInTen(voteAvgInTen);
        content.putReleaseDate(releaseDate);
        content.putMovieId(id);
        content.putFavorite(favorite);

        return content;
    }

    public long insert(Context context) {
        MovieContentValues values = getDbContents();

        Uri uri = values.insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public static ArrayList<Movie> getAll(Context context) {

        ArrayList<Movie> result = new ArrayList<>();

        MovieSelection where = new MovieSelection();

        Cursor c = context.getContentResolver().query(MovieColumns.CONTENT_URI, null,
                where.sel(), where.args(), null);

        c.moveToFirst();

        while (c.getCount() > 0) {
            MovieCursor mc = new MovieCursor(c);

            Movie movie = getMovie(mc);

            result.add(movie);

            if (c.isLast())
                break;
            else
                c.moveToNext();
        }

        return result;
    }

    @NonNull
    private static Movie getMovie(MovieCursor mc) {

        Movie movie = new Movie();

        movie.setFavorite(mc.getFavorite());
        movie.setVoteAvgInTen(mc.getVoteAvgInTen());
        movie.setReleaseDate(mc.getReleaseDate());
        movie.setSynopsis(mc.getSynopsis());
        movie.setId(mc.getId());
        movie.setPosterUrl(mc.getPosterUrl());
        movie.setTitle(mc.getTitle());
        return movie;
    }
}
