package com.example.vocabtrainer;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordBankLoader {

    //<WordEntry> is storing the objects from class WordEntry.java
    // this is also considered a static method that returns a list of WordEntry objects
    public static List<WordEntry> loadWords(Context context) {
        // declaring a class variable from an outside class ( declaring the WordEntry List in Word Bank Loader)
        List<WordEntry> wordList = new ArrayList<>();

        try (InputStream is = context.getAssets().open("wordsTester1.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

                if (parts.length >= 5) {
                    WordEntry entry = new WordEntry(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim()
                    );
                    wordList.add(entry);
                } else {
                    Log.w("WordBankLoader", "Skipping malformed line: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordList; // the point of creating a method is to return a value if the method is not "void"
    }

}