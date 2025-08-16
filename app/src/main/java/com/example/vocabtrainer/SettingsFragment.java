package com.example.vocabtrainer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("Settings (Placeholder)");
        textView.setTextSize(24);
        return textView;
    }
}