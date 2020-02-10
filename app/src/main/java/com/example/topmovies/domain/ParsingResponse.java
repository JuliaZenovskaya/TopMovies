package com.example.topmovies.domain;

import android.graphics.Bitmap;

import com.example.topmovies.domain.model.Movie;

import java.util.ArrayList;

public interface ParsingResponse {
    ArrayList<Movie> getMovies();
    Bitmap getImage(String url);
}
