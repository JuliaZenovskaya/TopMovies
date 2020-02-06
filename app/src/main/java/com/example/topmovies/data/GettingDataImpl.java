package com.example.topmovies.data;

import com.example.topmovies.domain.GettingData;
import com.example.topmovies.domain.model.Movie;

import java.io.IOException;
import java.util.ArrayList;


public class GettingDataImpl implements GettingData {
    public ArrayList<Movie> movies;
    private ParsingResponse parsingResponse = new ParsingResponse();

    GettingDataImpl() throws IOException {
        this.movies = parsingResponse.getMovies();
    }

    @Override
    public String getImageUrl(Movie movie) {
        return movie.getPosterUrl();
    }

    @Override
    public String getTitle(Movie movie) {
        return movie.getTitle();
    }

    @Override
    public String getDate(Movie movie) {
        assert movie.getRelease() != null;
        return movie.getRelease().toString();
    }

    @Override
    public String getOverview(Movie movie) {
        return movie.getOverview();
    }

    @Override
    public Integer getVote(Movie movie) {
        return movie.getVote();
    }
}
