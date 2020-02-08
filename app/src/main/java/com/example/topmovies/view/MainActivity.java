package com.example.topmovies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.topmovies.R;
import com.example.topmovies.data.GettingDataImpl;
import com.example.topmovies.data.ParsingResponse;
import com.example.topmovies.domain.model.Movie;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GettingDataImpl gettingData;
    private ParsingResponse parsingResponse = new ParsingResponse();
    private Bitmap bm;
    ArrayList<Movie> movies;


    public MainActivity() throws IOException, InterruptedException {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncContent asyncContent = new AsyncContent();
        asyncContent.execute();

        System.out.println("///////");
        System.out.println(movies);
    }

    class AsyncContent extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            try {
                System.out.println("))))))))");
                System.out.println(parsingResponse.getMovies());
                return parsingResponse.getMovies();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> result) {
            movies = result;
            System.out.println(result);
            if (movies != null) {
                System.out.println(result.get(0).toString());
                for (final Movie i : movies) {
                    final View v = getLayoutInflater().inflate(R.layout.movie, null);
                    TextView title = (TextView) v.findViewById(R.id.title);
                    TextView release = (TextView) v.findViewById(R.id.release);
                    TextView overview = (TextView) v.findViewById(R.id.overview);

                    ImageView image = (ImageView) v.findViewById(R.id.image);
                    AsyncImage asyncImage = new AsyncImage();
                    asyncImage.execute(i.getPosterUrl());
                    image.setImageBitmap(bm);

                    ImageView vote = (ImageView) v.findViewById(R.id.vote);
                    Button more = (Button) v.findViewById(R.id.more_info);

                    title.setText(i.getTitle());

                    release.setText(i.getRelease());
                    overview.setText(i.getOverview());
                    more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    ((LinearLayout) findViewById(R.id.movies_list)).addView(v);
                }
            }
        }
    }

    class AsyncImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {
            return parsingResponse.getImage(url[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bm = result;
        }
    }
}
