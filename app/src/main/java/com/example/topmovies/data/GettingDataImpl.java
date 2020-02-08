package com.example.topmovies.data;

import com.example.topmovies.domain.GettingData;
import com.example.topmovies.domain.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class GettingDataImpl implements GettingData {
    public ArrayList<Movie> movies;

    public GettingDataImpl() throws IOException, InterruptedException {
        synchronized (this) {
            ParsingResponse parsingResponse = new ParsingResponse();
            movies = parsingResponse.getMovies();

            System.out.println(".........");
            System.out.println(movies);
        }
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
        return movie.getRelease();
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
