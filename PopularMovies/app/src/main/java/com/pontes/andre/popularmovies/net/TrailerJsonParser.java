package com.pontes.andre.popularmovies.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class TrailerJsonParser {

    private final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    public ArrayList<String> Parse(String json)
            throws JSONException, ParseException {
        ArrayList<String> result = new ArrayList<>();

        JSONObject root = new JSONObject(json);

        JSONArray movies = root.getJSONArray("results");

        for (int x = 0; x < movies.length(); x++) {
            JSONObject currentJson = movies.getJSONObject(x);

            String movieUrl = getMovieUrl(currentJson);

            result.add(movieUrl);
        }


        return result;
    }

    private String getMovieUrl(JSONObject currentJson) throws JSONException {

        String videoKey = currentJson.getString("key");

        return YOUTUBE_BASE_URL + videoKey;
    }
}