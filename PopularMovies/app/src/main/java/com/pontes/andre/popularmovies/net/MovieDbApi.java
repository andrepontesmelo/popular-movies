package com.pontes.andre.popularmovies.net;

import com.pontes.andre.popularmovies.model.OrderEnum;

public class MovieDbApi {

    private String baseUrl = "http://api.themoviedb.org/3/movie/";

    private MovieDbApi() {
    }

    private static MovieDbApi instance = null;

    public static MovieDbApi getInstance() {

        if (instance == null)
            instance = new MovieDbApi();

        return instance;
    }

    public String getKey() {
        String key = "###";

        return key;
    }

    public String getUrl(OrderEnum orderBy) {

        return baseUrl + (orderBy == OrderEnum.HighestRated ? "top_rated" : "popular") + "?api_key=" + getKey();
    }

    public String getTrailerUrl(long movieId) {

        return baseUrl + Long.valueOf(movieId) + "/videos?api_key=" + getKey();
    }

}
