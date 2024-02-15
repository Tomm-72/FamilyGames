package com.example.familygames.activities;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.R;

public class GameSelectionActivity extends AppCompatActivity {
    ScrollView gameScrollView;
    TextView gameSelectionTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        gameScrollView = findViewById(R.id.gameScrollView);
        gameSelectionTitle = findViewById(R.id.gameSelectionTitle);
    }
}