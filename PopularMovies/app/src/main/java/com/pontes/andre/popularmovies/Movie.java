package com.pontes.andre.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Movie implements Parcelable {

    private String title;
    private String posterUrl;
    private String synopsis;
    private float voteAvgInTen;
    private Date releaseDate;
    private long id;

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public float getVoteAvgInTen() {
        return voteAvgInTen;
    }
    public float getVoteAvgInFive() {
        return voteAvgInTen / 2;
    }
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
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
