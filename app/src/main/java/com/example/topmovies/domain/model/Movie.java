package com.example.topmovies.domain.model;

import android.graphics.Bitmap;

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
    private String release;

    @Nullable
    private Bitmap poster;

    @Nullable
    private Integer vote;

    public Movie() {
    }

    public Movie(@Nullable Integer id, @Nullable String title, @Nullable String overview, @Nullable String release, @Nullable Bitmap poster, @Nullable Integer vote) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release = release;
        this.poster = poster;
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
    public String getRelease() {
        return release;
    }

    public void setRelease(@Nullable String release) {
        this.release = release;
    }

    @Nullable
    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(@Nullable Bitmap poster) {
        this.poster = poster;
    }

    @Nullable
    public Integer getVote() {
        return vote;
    }

    public void setVote(@Nullable Integer vote) {
        this.vote = vote;
    }
}
