package com.example.vocabtrainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LearnFragment extends Fragment {

    private TextView wordText, definitionText, phoneticText, speechText, exampleText;
    private MaterialButton learnButton;

    private Random random = new Random();

    private List<WordEntry> wordBank; // declaring the list variable and storing words from Wordbank.java
    private Set<WordEntry> seenWords = new HashSet<>(); // this  stores the seen words

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        // Bind views
        wordText = view.findViewById(R.id.Word);
        definitionText = view.findViewById(R.id.Definition);
        phoneticText = view.findViewById(R.id.Phonetic);
        speechText = view.findViewById(R.id.Speech);
        exampleText = view.findViewById(R.id.Example);
        learnButton = view.findViewById(R.id.squareButton);

        // Load word list from CSV
        wordBank = WordBankLoader.loadWords(requireContext()); // storing a static method

        // Show the first word
        if (!wordBank.isEmpty()) {
            loadNewWord();
        }
        // if wordBank has words -> isEmpty() is false -> !false -> true, then loadNewWord() called


        // Set button action
        learnButton.setOnClickListener(v -> loadNewWord());

        return view;
    }

    private void loadNewWord() {
        // Guard clause
        if (wordBank == null || wordBank.isEmpty())
            return;

        // Filter unseen words
        List<WordEntry> unseenWords = new ArrayList<>();
        for (WordEntry entry : wordBank) {
            if (!seenWords.contains(entry)) {
                unseenWords.add(entry);
            }
        }
        // if seen words DOES NOT contain entry ( temp value for any word ), add it to unseenWords
        // if seenWords DOES contain entry -> true -> !true -> false, do not add
        // if seenWords DOES not contain entry -> false -> !false -> true, add it to the list

        // Check if there are any unseen words left
        if (unseenWords.isEmpty()) {
            // Optionally notify user or reset
            wordText.setText("All words seen!");
            definitionText.setText("");
            phoneticText.setText("");
            speechText.setText("");
            exampleText.setText("");
            return;
        }

        // Pick a random unseen word
        WordEntry entry = unseenWords.get(random.nextInt(unseenWords.size()));

        // Mark it as seen
        seenWords.add(entry);

        // Display the word details
        wordText.setText(entry.getWord());
        definitionText.setText(entry.getDefinition());
        phoneticText.setText(entry.getPhonetic());
        speechText.setText(entry.getPartOfSpeech());
        exampleText.setText(entry.getExample());
    }

}
