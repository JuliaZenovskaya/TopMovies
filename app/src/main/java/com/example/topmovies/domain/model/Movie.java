package com.example.topmovies.domain.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public class Movie {
    @Nullable
    private Integer id;

    @Nullable
    private String title;

    @Nullable
    private String overview;

    @Nullable
    private Date release;

    @Nullable
    private String posterUrl;

    @Nullable
    private Integer vote;

    public Movie() {
    }

    public Movie(@Nullable Integer id, @Nullable String title, @Nullable String overview, @Nullable Date release, @Nullable String posterUrl, @Nullable Integer vote) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release = release;
        this.posterUrl = posterUrl;
        this.vote = vote;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getOverview() {
        return overview;
    }

    public void setOverview(@Nullable String overview) {
        this.overview = overview;
    }

    @Nullable
    public Date getRelease() {
        return release;
    }

    public void setRelease(@Nullable Date release) {
        this.release = release;
    }

    @Nullable
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(@Nullable String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Nullable
    public Integer getVote() {
        return vote;
    }

    public void setVote(@Nullable Integer vote) {
        this.vote = vote;
    }
}
