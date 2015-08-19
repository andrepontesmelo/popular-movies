package com.pontes.andre.popularmovies.net;

import com.pontes.andre.popularmovies.model.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class ReviewJsonParser {

    public ArrayList<Review> Parse(String json)
            throws JSONException, ParseException {
        ArrayList<Review> result = new ArrayList<Review>();

        JSONObject root = new JSONObject(json);

        JSONArray reviews = root.getJSONArray("results");

        for (int x = 0; x < reviews.length(); x++) {
            JSONObject currentJson = reviews.getJSONObject(x);

            Review review = getReview(currentJson);

            result.add(review);
        }


        return result;
    }

    private Review getReview(JSONObject jsonObject) throws JSONException {
        Review review = new Review();

        review.setAuthor(jsonObject.getString("author"));
        review.setContent(jsonObject.getString("content"));

        return review;
    }
}