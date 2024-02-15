package com.example.familygames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.familygames.activities.GameSelectionActivity;
import com.example.familygames.game.Game;
import com.example.familygames.game.GameFactory;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button showGameButton;
    private Button showRankButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Game> gameList = new GameFactory().generateGameList();
        setContentView(R.layout.activity_main);

        showGameButton = findViewById(R.id.showGameButton);
        showGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameSelectionActivity.class);
            intent.putExtra("gameList", (Serializable) gameList);
            startActivity(intent);
        });

        showRankButton = findViewById(R.id.showRankButton);

    }
}