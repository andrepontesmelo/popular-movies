package com.pontes.andre.popularmovies;

import com.pontes.andre.popularmovies.model.Review;
import com.pontes.andre.popularmovies.net.ReviewJsonParser;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

public class ReviewParserTest {

    String testInput = "{\"id\":76341,\"page\":1,\"results\":[{\"id\":\"55660928c3a3687ad7001db1\",\"author\":\"Phileas Fogg\",\"content\":\"Fabulous action movie. Lots of interesting characters. They don't make many movies like this. The whole movie from start to finish was entertaining I'm looking forward to seeing it again. I definitely recommend seeing it.\",\"url\":\"http://j.mp/1HLTNzT\"},{\"id\":\"55732a53925141456e000639\",\"author\":\"Andres Gomez\",\"content\":\"Good action movie with a decent script for the genre. The photography is really good too but, in the end, it is quite repeating itself from beginning to end and the stormy OST is exhausting.\",\"url\":\"http://j.mp/1dUnvpG\"}],\"total_pages\":1,\"total_results\":2}";

    ReviewJsonParser parser = new ReviewJsonParser();

    @Test
    public void testShouldParseTotalReviews() throws Exception {

        ArrayList<Review> result = parser.Parse(testInput);

        Assert.assertEquals(2, result.size());
    }
}