package com.pontes.andre.popularmovies;

import com.pontes.andre.popularmovies.net.MovieTrailerJsonParser;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

public class MovieTrailerParserTest {

    String testInput = "{\"id\":157336,\"results\":[{\"id\":\"53db3c790e0a26189a000d09\",\"iso_639_1\":\"en\",\"key\":\"ePbKGoIGAXY\",\"name\":\"Trailer 3\",\"site\":\"YouTube\",\"size\":1080,\"type\":\"Trailer\"},{\"id\":\"550df44b9251413554004d43\",\"iso_639_1\":\"en\",\"key\":\"KlyknsTJk0w\",\"name\":\"Own it today\",\"site\":\"YouTube\",\"size\":720,\"type\":\"Trailer\"},{\"id\":\"533ec6fcc3a3685448009ccc\",\"iso_639_1\":\"en\",\"key\":\"nyc6RJEEe0U\",\"name\":\"Teaser\",\"site\":\"YouTube\",\"size\":720,\"type\":\"Trailer\"},{\"id\":\"5376ab510e0a26141c0005a8\",\"iso_639_1\":\"en\",\"key\":\"zSWdZVtXT7E\",\"name\":\"Trailer\",\"site\":\"YouTube\",\"size\":720,\"type\":\"Trailer\"},{\"id\":\"545da247c3a3685362005187\",\"iso_639_1\":\"en\",\"key\":\"Lm8p5rlrSkY\",\"name\":\"Trailer 2\",\"site\":\"YouTube\",\"size\":1080,\"type\":\"Trailer\"}]}";

    MovieTrailerJsonParser parser = new MovieTrailerJsonParser();

    @Test
    public void testShouldParseTrailerUrl() throws Exception {

        ArrayList<String> result = parser.Parse(testInput);

        Assert.assertEquals("https://www.youtube.com/watch?v=ePbKGoIGAXY", result.get(0));
    }
}