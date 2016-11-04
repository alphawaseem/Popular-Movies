package com.example.popularmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by peace on 7/10/16.
 */

public class VideosResponse {

    @SerializedName("results")
    private List<Video> results;

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    public class Video {
        @SerializedName("key")
        private String key;
        @SerializedName("name")
        private String name;
        @SerializedName("type")
        private String type;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
