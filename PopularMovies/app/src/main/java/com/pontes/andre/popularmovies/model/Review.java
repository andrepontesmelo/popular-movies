package com.pontes.andre.popularmovies.model;

import com.pontes.andre.popularmovies.provider.review.ReviewContentValues;

public class Review {
    private String author;
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewContentValues getDbContents() {
        ReviewContentValues contentValues = new ReviewContentValues();

        contentValues.putAuthor(author);
        contentValues.putContent(content);

        return contentValues;
    }
}
