package com.pontes.andre.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MoviesJsonParser {

    public ArrayList<Movie> Parse(String json)
            throws JSONException, ParseException {
        ArrayList<Movie> result = new ArrayList<Movie>();

        JSONObject root = new JSONObject(json);

        JSONArray movies = root.getJSONArray("results");

        for (int x = 0; x < movies.length(); x++) {
            JSONObject currentJson = movies.getJSONObject(x);

            Movie movie = getMovie(currentJson);

            result.add(movie);
        }

        return result;
    }

    private Movie getMovie(JSONObject currentJson) throws JSONException, ParseException {
        Movie movie = new Movie();

        movie.setTitle(currentJson.getString("title"));

        movie.setId(currentJson.getLong("id"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        movie.setReleaseDate(format.parse(currentJson.getString("release_date")));

        movie.setPosterUrl(currentJson.getString("poster_path"));

        movie.setVoteAvgInTen((float) currentJson.getDouble("vote_average"));

        movie.setSynopsis(currentJson.getString("overview"));

        return movie;
    }
}
