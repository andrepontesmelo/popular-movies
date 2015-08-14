package com.pontes.andre.popularmovies;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {

    private String title;
    private Date releaseDate;
    private String posterUrl;

    private float voteAvgInTen;

    private String synopsis;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

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
}
