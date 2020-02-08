package com.example.topmovies.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.topmovies.domain.model.Movie;
import com.google.gson.stream.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ParsingResponse{

    private String apiKey = "d327787b7974ded8a7541baf2274204f";
    private Integer releaseYear = 2019;
    private String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey +
            "&primary_release_year=" + releaseYear;
    ArrayList<Movie> movies = null;

    public synchronized ArrayList<Movie> getMovies() throws IOException {
                ArrayList<Movie> newmovies = new ArrayList<>();
                URL moviesEndpoint = null;
                try {
                    moviesEndpoint = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpsURLConnection myConnection =
                        null;
                try {
                    myConnection = (HttpsURLConnection) moviesEndpoint.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);

                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            switch (key) {
                                case "results":
                                    jsonReader.beginArray();
                                    Movie movie = new Movie();
                                    while (jsonReader.hasNext()) {
                                        jsonReader.beginObject();
                                        while (jsonReader.hasNext()) {
                                            String key2 = jsonReader.nextName();
                                            switch (key2) {
                                                case "poster_path":
                                                    movie.setPosterUrl(jsonReader.nextString());
                                                    break;
                                                case "id":
                                                    movie.setId(Integer.parseInt(jsonReader.nextString()));
                                                    break;
                                                case "title":
                                                    movie.setTitle(jsonReader.nextString());
                                                    break;
                                                case "vote_average":
                                                    movie.setVote(Math.round(Float.parseFloat(jsonReader.nextString()) * 10));
                                                    break;
                                                case "overview":
                                                    movie.setOverview(jsonReader.nextString());
                                                    break;
                                                case "release_date":
                                                    movie.setRelease(jsonReader.nextString());
                                                    try {
                                                        System.out.println(movie.toString());
                                                        newmovies.add(movie);
                                                        movie = new Movie();
                                                        System.out.println(newmovies);
                                                    } catch (Exception e) {
                                                        System.out.println(e.toString());
                                                    }
                                                    break;
                                                default:
                                                    jsonReader.skipValue();
                                                    break;
                                            }


                                        }
                                        jsonReader.endObject();

                                    }
                                    jsonReader.endArray();
                                    break;
                                default:
                                    jsonReader.skipValue();
                                    break;

                            }

                        }
                        jsonReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myConnection.disconnect();

                movies = newmovies;
                System.out.println("*******");
                System.out.println(movies);
        return movies;
    }

    public Bitmap getImage(String poster_url) {
        String url = "http://image.tmdb.org/t/p/w185/" + poster_url;
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
