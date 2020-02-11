package com.example.topmovies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.topmovies.data.ParsingResponseImpl;
import com.example.topmovies.domain.ParsingResponse;
import com.example.topmovies.domain.model.Movie;

import org.junit.Assert;
import org.junit.Test;

public class ParsingResponseImplTest {
    ParsingResponse parsingResponse = new ParsingResponseImpl();

    @Test
    public void getMovies_first_one(){
        Movie expected = new Movie(419704, "Ad Astra", "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.", "2019-09-17", parsingResponse.getImage("//xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"), 60);
        Movie resulted = parsingResponse.getMovies().get(0);

        Assert.assertEquals(expected,resulted);
    }

    @Test
    public void getMovies_count_movies(){
        int expected = 20;
        int resulted = parsingResponse.getMovies().size();

        Assert.assertEquals(expected, resulted);
    }

    @Test
    public void getImage_AdAstra(){
        Bitmap bitmapSource = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.adastra);
        Bitmap expected = Bitmap.createBitmap(bitmapSource);
        Bitmap resulted = parsingResponse.getImage("//xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg");

        Assert.assertEquals(expected, resulted);
    }
}
