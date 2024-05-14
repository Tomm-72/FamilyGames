package com.example.familygames.activities.gamesactivities.level8activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.familygames.R;
import com.example.familygames.activities.PlayersAdapter;
import com.example.familygames.activities.gamesactivities.endgames.Level8EndGameActivity;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level8GameActivity extends AppCompatActivity {
    private List<Player> players;
    private RecyclerView playersRecyclerView;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level8_game);

        players = getIntent().getParcelableArrayListExtra("players");
        level = getIntent().getIntExtra("level", 1);
        Button nextTurnButton = findViewById(R.id.nextTurnButton);
        nextTurnButton.setOnClickListener(v -> nextTurn());

        playersRecyclerView = findViewById(R.id.playersRecyclerView);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlayersAdapter adapter = new PlayersAdapter(players, level);
        playersRecyclerView.setAdapter(adapter);
    }

    public void nextTurn() {
        ArrayList<Player> winners = new ArrayList<>();
        for (Player player : players) {
            Integer selection = ((PlayersAdapter) Objects.requireNonNull(playersRecyclerView.getAdapter())).getTemporarySelections().get(player);
            if (selection != null) {
                if (selection % 2 == 0) {
                    player.addToScore(2);
                }
                else {
                    player.addToScore(1);
                }
            }
            if (player.getScore() >= 8) {
                winners.add(player);
            }

        }
        if (winners.size() > 0) {
            Intent intent = new Intent(this, Level8EndGameActivity.class);
            intent.putExtra("winners", winners);
            intent.putExtra("players", (ArrayList<Player>) players);
            finish();
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Level8GameActivity.class);
            intent.putExtra("players", (ArrayList<Player>) players);
            intent.putExtra("level", level);
            startActivity(intent);
        }
    }
}