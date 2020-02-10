package com.example.topmovies;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DiStorage.createInstance();
    }
}
