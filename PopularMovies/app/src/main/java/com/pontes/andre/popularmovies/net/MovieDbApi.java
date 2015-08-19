package com.pontes.andre.popularmovies.net;

import com.pontes.andre.popularmovies.model.OrderEnum;

public class MovieDbApi {

    private String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private String API_KEY_FRAGMENT = "?api_key=";
    private String MOVIE_DB_KEY = "###";

    private MovieDbApi() {
    }

    private static MovieDbApi instance = null;

    public static MovieDbApi getInstance() {

        if (instance == null)
            instance = new MovieDbApi();

        return instance;
    }

    public String getKey() {

        return API_KEY_FRAGMENT + MOVIE_DB_KEY;
    }

    public String getUrl(OrderEnum orderBy) {

        String orderByString = (orderBy == OrderEnum.HighestRated ? "top_rated" : "popular");

        return BASE_URL + orderByString + getKey();
    }

    public String getTrailerUrl(long movieId) {

        return BASE_URL + Long.valueOf(movieId) + "/videos" + getKey();
    }

    public String getReviewUrl(long movieId) {

        return BASE_URL + Long.valueOf(movieId) + "/reviews" + getKey();
    }
}
