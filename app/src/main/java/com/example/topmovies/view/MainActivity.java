package com.example.topmovies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.topmovies.DiStorage;
import com.example.topmovies.R;
import com.example.topmovies.domain.ParsingResponse;
import com.example.topmovies.domain.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ParsingResponse parsingResponse;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parsingResponse = DiStorage.getInstance().getParsingResponse();

        AsyncContent asyncContent = new AsyncContent();
        asyncContent.execute();
    }

    @SuppressLint("StaticFieldLeak")
    class AsyncContent extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            return parsingResponse.getMovies();
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> result) {
            movies = result;
            if (movies != null) {
                for (final Movie i : movies) {
                    final View v = getLayoutInflater().inflate(R.layout.movie, null);
                    TextView title = v.findViewById(R.id.title);
                    TextView release = v.findViewById(R.id.release);
                    TextView overview = v.findViewById(R.id.overview);
                    ImageView image = v.findViewById(R.id.image);
                    image.setImageBitmap(i.getPoster());
                    TextView vote = v.findViewById(R.id.vote_bar);
                    Button more = v.findViewById(R.id.more_info);

                    title.setText(i.getTitle());
                    vote.setText("\n" + i.getVote().toString() + "%");
                    release.setText(i.getRelease());
                    overview.setText(i.getOverview());
                    more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent startProfileIntent = new Intent(MainActivity.this, ScheduleViewingActivity.class);
                            startProfileIntent.putExtra(ScheduleViewingActivity.MOVIE_KEY, i.getTitle());
                            startActivity(startProfileIntent);
                        }
                    });
                    ((LinearLayout) findViewById(R.id.movies_list)).addView(v);
                }
            }
        }
    }

}
