package com.example.topmovies.domain;

import com.example.topmovies.domain.model.Movie;

public interface GettingData {
    public String getImageUrl(Movie movie);

    public String getTitle(Movie movie);

    public String getDate(Movie movie);

    public String getOverview(Movie movie);

    public Integer getVote(Movie movie);
}
