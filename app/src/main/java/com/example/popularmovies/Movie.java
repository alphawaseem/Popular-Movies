package com.example.popularmovies;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.popularmovies.data.MoviesContract;
import com.google.gson.annotations.SerializedName;

import org.chalup.microorm.annotations.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peace on 3/10/16.
 */

public class Movie implements Parcelable {

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Column(MoviesContract.MoviesEntry.COL_MOVIE_POSTER_PATH)
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @Column(MoviesContract.MoviesEntry.COL_MOVIE_OVERVIEW)
    @SerializedName("overview")
    private String overview;
    @Column(MoviesContract.MoviesEntry.COL_MOVIE_RELEASE_DATE)
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @Column(MoviesContract.MoviesEntry._ID)
    @SerializedName("id")
    private Integer id;
    @Column(MoviesContract.MoviesEntry.COL_MOVIE_ORIGINAL_TITLE)
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @Column(MoviesContract.MoviesEntry.COL_MOVIE_TITLE)
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @Column(MoviesContract.MoviesEntry.COL_VOTE_AVG)
    @SerializedName("vote_average")
    private String voteAverage;

    public Movie() {
    }

    public Movie(String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreIds, Integer id,
                 String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity,
                 Integer voteCount, Boolean video, String voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    private Movie(Parcel input) {

        posterPath = input.readString();
        overview= input.readString();
        releaseDate = input.readString();
        originalTitle = input.readString();
        title = input.readString();
        voteAverage = input.readString();
        id = input.readInt();

    }

    /**
     * @param cursor cursor object pointing to the row from which movie info is extracted
     * @return
     */
    static public Movie getFromCursor(Cursor cursor) {
        Movie movie = new Movie();

        int id = cursor.getInt(MoviesContract.MoviesEntry.INDEX_ID);
        String title = cursor.getString(MoviesContract.MoviesEntry.INDEX_TITLE);
        String original_title = cursor.getString(MoviesContract.MoviesEntry.INDEX_ORIGINAL_TITLE);
        String overview = cursor.getString(MoviesContract.MoviesEntry.INDEX_OVERVIEW);
        String release_date = cursor.getString(MoviesContract.MoviesEntry.INDEX_RELEASE_DATE);
        String vote_avg = cursor.getString(MoviesContract.MoviesEntry.INDEX_VOTE_AVG);
        String poster_path = cursor.getString(MoviesContract.MoviesEntry.INDEX_POSTER_PATH);

        movie.setId(id);
        movie.setTitle(title);
        movie.setOriginalTitle(original_title);
        movie.setOverview(overview);
        movie.setReleaseDate(release_date);
        movie.setPosterPath(poster_path);
        movie.setVoteAverage(vote_avg);

        return movie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(title);
        dest.writeString(voteAverage);
        dest.writeInt(id);
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

}