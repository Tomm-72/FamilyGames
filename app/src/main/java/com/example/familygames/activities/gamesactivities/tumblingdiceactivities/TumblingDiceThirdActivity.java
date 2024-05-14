package com.example.familygames.activities.gamesactivities.tumblingdiceactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.endgames.TumblingDiceEndGameActivity;
import com.example.familygames.player.Player;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TumblingDiceThirdActivity extends AppCompatActivity {

    private LinearLayout playersContainer;
    private List<Player> players;
    private final List<Player> over100PointsPlayers = new ArrayList<>();
    private final Map<Integer, List<Spinner>> playerMultiplierSpinners = new HashMap<>();

    private final Map<Integer, List<Spinner>> playerDiceValueSpinners = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbling_dice_third);
        players = getIntent().getParcelableArrayListExtra("players");
        playersContainer = findViewById(R.id.playersContainer);
        Button calculateScoresButton = findViewById(R.id.calculateScoresButton);
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

            List<Spinner> multipliersSpinnersForPlayer = playerMultiplierSpinners.get(player.getId());
            List<Spinner> diceValuesSpinnersForPlayer = playerDiceValueSpinners.get(player.getId());

            for (int j = 0; j < 5; j++) {
                assert multipliersSpinnersForPlayer != null;
                Spinner multiplierSpinner = multipliersSpinnersForPlayer.get(j);
                assert diceValuesSpinnersForPlayer != null;
                Spinner diceValueSpinner = diceValuesSpinnersForPlayer.get(j);
                int multiplier = Integer.parseInt(multiplierSpinner.getSelectedItem().toString().replace("x", ""));
                int diceValue = Integer.parseInt(diceValueSpinner.getSelectedItem().toString());

                score += multiplier * diceValue;
            }
            player.addToScore(score);
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
            Intent intent = new Intent(this, TumblingDiceEndGameActivity.class);
            intent.putExtra("players", (ArrayList<Player>) players);
            intent.putExtra("winner", winner);
            intent.putExtra("isGameInProgress", false);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, TumblingDiceScoreActivity.class);
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
            Player player = players.get(i);
            View playerView = LayoutInflater.from(this).inflate(R.layout.player_score_entry, playersContainer, false);

            TextView playerName = playerView.findViewById(R.id.playerName);
            playerName.setText(String.format("%s : ", player.getName()));

            ArrayAdapter<String> multiplierAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, multiplierStrings);
            multiplierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter<String> diceValueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diceValueStrings);
            diceValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Créer les listes pour conserver les références aux Spinner pour ce joueur
            List<Spinner> playerMultipliersSpinners = new ArrayList<>();
            List<Spinner> playerDiceValuesSpinners = new ArrayList<>();

            // Le conteneur de layout pour tous les dés/multiplicateurs de ce joueur
            LinearLayout diceAndMultiplierContainer = playerView.findViewById(R.id.diceAndMultiplierContainer);

            // Assumons que pour chaque dé, nous avons un LinearLayout horizontal avec deux Spinners : un pour la valeur du dé et un pour le multiplicateur
            for (int j = 0; j < 5; j++) {
                // Obtenir le LinearLayout pour le dé spécifique
                LinearLayout diceLayout = (LinearLayout) diceAndMultiplierContainer.getChildAt(j +1);

                // Le Spinner pour la valeur du dé est le premier élément dans ce LinearLayout
                Spinner diceValueSpinner = (Spinner) diceLayout.getChildAt(1);
                diceValueSpinner.setAdapter(diceValueAdapter);
                playerDiceValuesSpinners.add(diceValueSpinner);

                // Le Spinner pour le multiplicateur est le deuxième élément dans ce LinearLayout
                Spinner multiplierSpinner = (Spinner) diceLayout.getChildAt(2);
                multiplierSpinner.setAdapter(multiplierAdapter);
                playerMultipliersSpinners.add(multiplierSpinner);
            }

            // Associer les spinners à ce joueur en utilisant son identifiant
            playerMultiplierSpinners.put(player.getId(), playerMultipliersSpinners);
            playerDiceValueSpinners.put(player.getId(), playerDiceValuesSpinners);

            // Ajouter la vue complète du joueur au conteneur
            playersContainer.addView(playerView);
        }
    }
}