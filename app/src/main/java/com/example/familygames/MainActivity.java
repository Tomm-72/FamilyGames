package com.example.familygames;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.activities.GameSelectionActivity;
import com.example.familygames.activities.RankActivity;
import com.example.familygames.game.Game;
import com.example.familygames.game.GameFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Game> gameList = new GameFactory().generateGameList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showGameButton = findViewById(R.id.showGameButton);
        showGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameSelectionActivity.class);
            intent.putParcelableArrayListExtra("gameList", gameList);
            startActivity(intent);
        });

        Button showRankButton = findViewById(R.id.showRankButton);
        showRankButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RankActivity.class);
            //intent.putExtra("gameList", (Serializable) gameList);
            startActivity(intent);
        });

    }
}