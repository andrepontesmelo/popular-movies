package com.pontes.andre.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MoviesJsonParser {
    public String getImageUrl (int position)
    {
        switch (position) {
            case 0:
                return "http://image.tmdb.org/t/p/w500/ktyVmIqfoaJ8w0gDSZyjhhOPpD6.jpg";
            case 1:
                return "http://image.tmdb.org/t/p/w500/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg";

            case 2:
                return "http://image.tmdb.org/t/p/w500/9gm3lL8JMTTmc3W4BmNMCuRLdL8.jpg";

            case 3:
                return "http://image.tmdb.org/t/p/w500/saF3HtAduvrP9ytXDxSnQJP3oqx.jpg";

            case 4:
                return "http://image.tmdb.org/t/p/w500/yUlpRbbrac0GTNHZ1l20IHEcWAN.jpg";

            default:
                return "http://image.tmdb.org/t/p/w500/aqNJrAxudMRNo8jg3HOUQqdl2xr.jpg";

        }
    }

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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        movie.setReleaseDate(format.parse(currentJson.getString("release_date")));

        movie.setPosterUrl(currentJson.getString("poster_path"));

        movie.setVoteAvg((float) currentJson.getDouble("vote_average"));
        return movie;
    }
}
