package com.example.familygames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.familygames.R;
import com.example.familygames.player.Player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class TumblingDiceThirdActivity extends AppCompatActivity {

    private LinearLayout playersContainer;
    private List<Player> players;
    private List<Player> over100PointsPlayers = new ArrayList<>();
    private Button calculateScoresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbling_dice_third);
        players = getIntent().getParcelableArrayListExtra("players");
        playersContainer = findViewById(R.id.playersContainer);
        calculateScoresButton = findViewById(R.id.calculateScoresButton);
        calculateScoresButton.setOnClickListener(v -> {
            calculateScores();
            isGameOver();
        });
        initializePlayersUI();
    }

    private void calculateScores() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int score = 0;
            for (int j = 1; j <= 5; j++) {
                int multiplierSpinnerId = getResources().getIdentifier("multiplierSpinner" + j, "id", getPackageName());
                Spinner multiplierSpinner = findViewById(multiplierSpinnerId);
                int multiplier = Integer.parseInt(multiplierSpinner.getSelectedItem().toString().replace("x", ""));

                int diceValueSpinnerId = getResources().getIdentifier("diceValue" + j, "id", getPackageName());
                Spinner diceValueSpinner = findViewById(diceValueSpinnerId);
                int diceValue = Integer.parseInt(diceValueSpinner.getSelectedItem().toString());

                score += multiplier * diceValue;
            }
            player.setScore(score);
            if (player.getScore() >= 100) {
                over100PointsPlayers.add(player);
            }
        }
    }

    private void isGameOver() {
        if (over100PointsPlayers.size() > 0) {
            Player winner = over100PointsPlayers.get(0);
            for (Player player : over100PointsPlayers) {
                if (player.getScore() > winner.getScore()) {
                    winner = player;
                }
            }
            TextView winnerTextView = new TextView(this);
            winnerTextView.setText(String.format("Le gagnant est %s avec %d points", winner.getName(), winner.getScore()));
            playersContainer.addView(winnerTextView);
            calculateScoresButton.setEnabled(false);
        } else {
            Intent intent = new Intent(this, TumblingDiceSecondActivity.class);
            intent.putExtra("players", (ArrayList<Player>) players);
            intent.putExtra("isGameInProgress", true);
            startActivity(intent);
        }
    }

    private void initializePlayersUI() {
        int[] multipliers = getResources().getIntArray(R.array.multiplier_array);
        int[] diceValues = getResources().getIntArray(R.array.dice_values_array);
        List<String> multiplierStrings = new ArrayList<>();
        List<String> diceValueStrings = new ArrayList<>();
        for (int multiplier : multipliers) {
            multiplierStrings.add(multiplier + "x");
        }
        for (int diceValue : diceValues) {
            diceValueStrings.add(String.valueOf(diceValue));
        }

        for (int i = 0; i < players.size(); i++) {
            View playerView = LayoutInflater.from(this).inflate(R.layout.player_score_entry, playersContainer, false);

            TextView playerName = playerView.findViewById(R.id.playerName);
            playerName.setText(String.format("%s : ", players.get(i).getName()));

            ArrayAdapter<String> multiplierAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, multiplierStrings);
            multiplierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter<String> diceValueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diceValueStrings);
            diceValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            for (int j = 1; j <= 5; j++) {
                int multiplierSpinnerId = getResources().getIdentifier("multiplierSpinner" + j, "id", getPackageName());
                Spinner multiplierSpinner = playerView.findViewById(multiplierSpinnerId);
                multiplierSpinner.setAdapter(multiplierAdapter);

                int diceValueSpinnerId = getResources().getIdentifier("diceValue" + j, "id", getPackageName());
                Spinner diceValueSpinner = playerView.findViewById(diceValueSpinnerId);
                diceValueSpinner.setAdapter(diceValueAdapter);
            }

            playersContainer.addView(playerView);
        }
    }
}