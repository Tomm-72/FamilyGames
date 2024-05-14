package com.example.familygames.activities.gamesactivities.skyjoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.endgames.SkyjoEndGameActivity;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SkyjoGameActivity extends AppCompatActivity {
    private List<Player> players;
    private final HashMap<Player, EditText> playerScoreFields = new HashMap<>();
    private Player looser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyjo_game);
        players = getIntent().getParcelableArrayListExtra("players");
        LinearLayout scoreContainer = findViewById(R.id.scoreContainer);
        initScoreContainer(scoreContainer, players);
        Button validateButton = findViewById(R.id.validateButton);
        validateButton.setOnClickListener(v -> goToSkyjoScorePage());
    }

    @SuppressLint("SetTextI18n")
    private void initScoreContainer(LinearLayout scoreContainer, List<Player> players) {
        for (Player player : players) {
            LinearLayout playerContainer = new LinearLayout(this);
            playerContainer.setOrientation(LinearLayout.HORIZONTAL);

            TextView playerName = new TextView(this);
            playerName.setText(player.getName() + ": ");
            playerContainer.addView(playerName);

            EditText playerScore = new EditText(this);
            playerScore.setHint("Score");
            playerScore.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
            playerContainer.addView(playerScore);

            playerScoreFields.put(player, playerScore);

            scoreContainer.addView(playerContainer);
        }
    }

    private void goToSkyjoScorePage() {
        for (Player player : players) {
            if (Objects.requireNonNull(playerScoreFields.get(player)).getText().toString().isEmpty()) {
                player.addToScore(0);
            } else {
                player.addToScore(Integer.parseInt(Objects.requireNonNull(playerScoreFields.get(player)).getText().toString()));
            }
            if (player.getScore() >= 100) {
                if (looser == null) {
                    looser = player;
                }
                else if (player.getScore() > looser.getScore()) {
                    looser = player;
                }
            }
        }
        if (looser != null) {
            goToSkyjoEndGamePage();
        } else {
            Intent intent = new Intent(this, SkyjoScoreActivity.class);
            intent.putParcelableArrayListExtra("players", (ArrayList<Player>) players);
            startActivity(intent);
        }

    }

    private void goToSkyjoEndGamePage() {
        Intent intent = new Intent(this, SkyjoEndGameActivity.class);
        intent.putParcelableArrayListExtra("players", (ArrayList<Player>) players);
        intent.putExtra("looser", looser);
        finish();
        startActivity(intent);
    }
}