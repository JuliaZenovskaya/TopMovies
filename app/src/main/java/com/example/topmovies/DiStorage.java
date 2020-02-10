package com.example.topmovies;

import android.content.Context;

import com.example.topmovies.data.ParsingResponseImpl;
import com.example.topmovies.domain.ParsingResponse;

public class DiStorage {
    private static DiStorage ourInstance = null;

    public static DiStorage getInstance() {
        return ourInstance;
    }

    private final ParsingResponse parsingResponse;

    private DiStorage() {
        parsingResponse = new ParsingResponseImpl();
    }

    static void createInstance() {
        ourInstance = new DiStorage();
    }

    public ParsingResponse getParsingResponse() {
        return parsingResponse;
    }
}
