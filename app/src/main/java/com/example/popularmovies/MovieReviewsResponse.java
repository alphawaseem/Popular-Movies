package com.example.popularmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by peace on 7/10/16.
 */

public class MovieReviewsResponse {
    @SerializedName("results")
    private List<Reviews> results;

    public List<Reviews> getResults() {
        return results;
    }

    public void setResults(List<Reviews> results) {
        this.results = results;
    }

    public class Reviews {
        @SerializedName("author")
        private String author;
        @SerializedName("id")
        private String id;

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
