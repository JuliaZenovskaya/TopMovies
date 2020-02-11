package com.example.topmovies.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.topmovies.domain.model.Movie;
import com.google.gson.stream.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ParsingResponseImpl implements com.example.topmovies.domain.ParsingResponse {
    private String apiKey = "d327787b7974ded8a7541baf2274204f";
    private Integer releaseYear = 2019;
    private String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey +
            "&primary_release_year=" + releaseYear;
    private ArrayList<Movie> movies = new ArrayList<>();
    private int counter = 0;

    public ArrayList<Movie> getMovies() {
        URL moviesEndpoint = null;
        try {
            moviesEndpoint = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection myConnection =
                null;
        try {
            assert moviesEndpoint != null;
            myConnection = (HttpsURLConnection) moviesEndpoint.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert myConnection != null;
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, StandardCharsets.UTF_8);
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String key = jsonReader.nextName();
                    if ("results".equals(key)) {
                        jsonReader.beginArray();
                        Movie movie = new Movie();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            while (jsonReader.hasNext()) {
                                String key2 = jsonReader.nextName();
                                switch (key2) {
                                    case "poster_path":
                                        movie.setPoster(getImage(jsonReader.nextString()));
                                        movie = countSettedFields(movie);
                                        break;
                                    case "id":
                                        movie.setId(Integer.parseInt(jsonReader.nextString()));
                                        movie = countSettedFields(movie);
                                        break;
                                    case "title":
                                        movie.setTitle(jsonReader.nextString());
                                        movie = countSettedFields(movie);
                                        break;
                                    case "vote_average":
                                        movie.setVote(Math.round(Float.parseFloat(jsonReader.nextString()) * 10));
                                        movie = countSettedFields(movie);
                                        break;
                                    case "overview":
                                        movie.setOverview(jsonReader.nextString());
                                        movie = countSettedFields(movie);
                                        break;
                                    case "release_date":
                                        movie.setRelease(jsonReader.nextString());
                                        movie = countSettedFields(movie);
                                        break;
                                    default:
                                        jsonReader.skipValue();
                                        break;
                                }
                            }
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        myConnection.disconnect();
        return movies;
    }

    private Movie countSettedFields(Movie movie) {
        counter++;
        if (counter == 6) {
            counter = 0;
            try {
                movies.add(movie);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return new Movie();
        } else {
            return movie;
        }
    }

    public Bitmap getImage(String poster_url) {
        String url = "https://image.tmdb.org/t/p/w185/" + poster_url;
        Bitmap bitmap = null;
        try {
            URL mURL = new URL(url);
            URLConnection conn = mURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
