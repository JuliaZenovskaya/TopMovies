package com.example.topmovies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.topmovies.R;
import com.example.topmovies.domain.model.Movie;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (Movie i : movies) {
            final View v = getLayoutInflater().inflate(R.layout.movie, null);
            TextView title = (TextView) v.findViewById(R.id.title);
            TextView release = (TextView) v.findViewById(R.id.release);
            TextView overview = (TextView) v.findViewById(R.id.overview);
            ImageView image = (ImageView) v.findViewById(R.id.image);
            ImageView vote = (ImageView) v.findViewById(R.id.vote);
            Button more = (Button) v.findViewById(R.id.more_info);

            title.setText(i.getTitle());
            assert i.getRelease() != null;
            Uri uri = Uri.parse("http://image.tmdb.org/t/p/w185//" + i.getPosterUrl());
            image.setImageURI(uri);

            release.setText(i.getRelease().toString());
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
