package com.pontes.andre.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pontes.andre.popularmovies.provider.review.ReviewContentValues;

import java.util.Date;

public class Review implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(author);
        out.writeString(content);
    }

    public Review()
    {
    }

    private Review(Parcel in) {
        author = in.readString();
        content = in.readString();
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

}
