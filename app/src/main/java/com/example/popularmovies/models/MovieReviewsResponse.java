package com.example.popularmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by peace on 7/10/16.
 */

public class MovieReviewsResponse {
    @SerializedName("results")
    private List<MovieReviews> results;

    public List<MovieReviews> getResults() {
        return results;
    }

    public void setResults(List<MovieReviews> results) {
        this.results = results;
    }

    public class MovieReviews {
        @SerializedName("author")
        private String author;
        @SerializedName("id")
        private String id;
        @SerializedName("content")
        private String content;


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
