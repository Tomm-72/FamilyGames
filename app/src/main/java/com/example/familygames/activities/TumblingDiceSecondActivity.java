package com.example.familygames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TumblingDiceSecondActivity extends AppCompatActivity {
    TextView playerName;
    Button nextPlayer;
    List<Player> players;
    int currentPlayerIndex = 0;
    int turnsRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbling_dice_second);

        players = getIntent().getParcelableArrayListExtra("players");
        if (getIntent().getBooleanExtra("isGameInProgress", false)) {
            Player lastPlayer = players.get(players.size() - 1);
            players.remove(players.size() - 1);
            players.add(0, lastPlayer);
            System.out.print(players);
        }
        playerName = findViewById(R.id.playerName);
        nextPlayer = findViewById(R.id.nextPlayer);

        assert players != null;
        turnsRemaining = 5 * players.size();

        updateUI();

        nextPlayer.setOnClickListener(v -> {
            advanceGame();
        });
    }

    private void updateUI() {
        // Assurez-vous qu'il reste des tours à jouer
        if (turnsRemaining > 0) {
            Player currentPlayer = players.get(currentPlayerIndex);
            playerName.setText(currentPlayer.getName() + " à toi de jouer !");
        } else {
            launchNextActivity();
        }
    }

    private void advanceGame() {
        currentPlayerIndex++;
        turnsRemaining--;

        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }

        updateUI();
    }

    private void launchNextActivity() {
        Intent intent = new Intent(this, TumblingDiceThirdActivity.class);
        intent.putExtra("players", (ArrayList<Player>) players);
        startActivity(intent);
    }
}