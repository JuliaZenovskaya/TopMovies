package com.example.topmovies.data;

import com.example.topmovies.domain.model.Movie;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ParsingResponse {

    private String apiKey = "d327787b7974ded8a7541baf2274204f";
    private Integer releaseYear = 2019;
    private String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey +
            "&primary_release_year=" + releaseYear;

    public ArrayList<Movie> getMovies() throws IOException {
        java.net.URL moviesEndpoint = new URL(url);
        HttpsURLConnection myConnection =
                (HttpsURLConnection) moviesEndpoint.openConnection();
        if (myConnection.getResponseCode() == 200) {
            InputStream responseBody = myConnection.getInputStream();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);

            Movie movie = new Movie();
            ArrayList<Movie> movies = null;

            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                switch (key) {
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
                        movie.setVote(Math.round(Float.parseFloat(jsonReader.nextString())*10));
                        break;
                    case "overview":
                        movie.setOverview(jsonReader.nextString());
                        break;
                    case "release_date":
                        movie.setRelease(new Date(jsonReader.nextString()));
                        movies.add(movie);
                        movie = null;
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
                }
            }
            return movies;
        } else {
            return null;
        }



    }
}
