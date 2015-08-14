package com.pontes.andre.popularmovies;

public class MovieDbApi {

    private String key = "YOUR PRIVATE KEY HERE - DO NOT COMMIT TO GIT";

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
        return key;
    }

    public String getUrl(OrderEnum orderBy) {

        StringBuilder builder = new StringBuilder(baseUrl);

        builder.append(orderBy == OrderEnum.MostRated ? "top_rated" : "popular");

        builder.append("?api_key=");

        builder.append(getKey());

        return builder.toString();
    }
}