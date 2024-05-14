package com.example.familygames.activities.gamesactivities.endgames;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.skyjoactivities.SkyjoActivity;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SkyjoEndGameActivity extends AppCompatActivity {
    private LinearLayout scoreContainer;
    private List<Player> players;
    private Player winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyjo_end_game);

        players = getIntent().getParcelableArrayListExtra("players");
        Player looser = getIntent().getParcelableExtra("looser");
        determinateWinner();

        TextView looserName = findViewById(R.id.looserName);
        assert looser != null;
        looserName.setText(looser.getName());

        TextView winnerName = findViewById(R.id.winnerName);
        winnerName.setText(winner.getName());

        scoreContainer = findViewById(R.id.scoreContainer);
        initializeScoreContainer();

        Button goToMenuButton = findViewById(R.id.goToMenuButton);
        goToMenuButton.setOnClickListener(v -> goToMenu());

        Button replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(v -> replay());

    }

    @SuppressLint("SetTextI18n")
    private void initializeScoreContainer() {
        LinearLayout playerContainer = new LinearLayout(this);
        playerContainer.setOrientation(LinearLayout.VERTICAL);
        for (Player player : players) {
            TextView playerLine = new TextView(this);
            playerLine.setText(player.getName() + ": " + player.getScore() + " points\n");
            playerContainer.addView(playerLine);
        }
        scoreContainer.addView(playerContainer);
    }

    private void determinateWinner() {
        for (Player player : players) {
            if (winner == null) {
                winner = player;
            }
            if (player.getScore() < winner.getScore()) {
                winner = player;
            }
        }
    }

    private void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void replay() {
        for (Player player : players) {
            player.setScore(0);
        }
        Intent intent = new Intent(this, SkyjoActivity.class);
        intent.putParcelableArrayListExtra("players", (ArrayList<Player>) players);
        finish();
        startActivity(intent);
    }
}