package com.example.familygames.activities.gamesactivities.playersetupactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.level8activities.Level8Activity;
import com.example.familygames.activities.gamesactivities.petitbacactivities.PetitBacActivity;
import com.example.familygames.activities.gamesactivities.skyjoactivities.SkyjoActivity;
import com.example.familygames.activities.gamesactivities.tumblingdiceactivities.TumblingDiceActivity;
import com.example.familygames.game.Game;
import com.example.familygames.player.Player;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SetPlayerValuesActivity extends AppCompatActivity {

    LinearLayout playersContainer;
    private List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_player_values);

        int nbPlayers = getIntent().getIntExtra("nbPlayers", 2);

        createPlayers(this.players, nbPlayers);

        Game game = getIntent().getParcelableExtra("game");
        assert game != null;
        Intent intent = setGame(game);

        playersContainer = findViewById(R.id.playersContainerLayout);

        for (int i = 0; i < nbPlayers; i++) {
            MaterialCardView cardView = createPlayerCard();
            playersContainer.addView(cardView);
        }

        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(v -> {
            // Récupérer les noms des joueurs
            retrievePlayerNames(this.players);

            assert intent != null;
            intent.putExtra("players", (ArrayList<Player>) this.players);
            startActivity(intent);
        });
    }

    private void createPlayers(List<Player> players, int nbPlayers) {
        for (int i = 0; i < nbPlayers; i++) {
            Player player = new Player("Joueur " + (i + 1));
            players.add(player);
        }
    }

    private Intent setGame(Game game) {
        switch (game.getName()) {
            case "Skyjo":
                return new Intent(this, SkyjoActivity.class);
            case "Petit Bac":
                return new Intent(this, PetitBacActivity.class);
            case "Level8":
                return new Intent(this, Level8Activity.class);
            case "Tumbling Dice":
                return new Intent(this, TumblingDiceActivity.class);
            default:
                setTitle("Jeu");
                return null;
        }
    }

    private void retrievePlayerNames(List<Player> players) {
        int j=0;
        for (int i = 0; i < playersContainer.getChildCount(); i++) {
            if (playersContainer.getChildAt(i) instanceof MaterialCardView) {
                MaterialCardView cardView = (MaterialCardView) playersContainer.getChildAt(i);
                LinearLayout linearLayout = (LinearLayout) cardView.getChildAt(0);
                TextInputLayout textInputLayout = (TextInputLayout) linearLayout.getChildAt(0);
                EditText editText = (EditText) textInputLayout.getEditText();
                if (editText != null && !(editText.getText().toString().isEmpty())) {
                    String playerName = editText.getText().toString();
                    players.get(j).setName(playerName);
                }
                j++;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private MaterialCardView createPlayerCard() {
        // Création du CardView
        MaterialCardView cardView = new MaterialCardView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(layoutParams);

        // Ajout d'un LinearLayout pour contenir les éléments
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Ajout d'un TextInputLayout pour le nom du joueur
        TextInputLayout textInputLayout = new TextInputLayout(this);
        EditText editText = new EditText(this);
        editText.setHint("Nom du joueur");
        textInputLayout.addView(editText);

        // Ajouter les éléments au LinearLayout
        linearLayout.addView(textInputLayout);

        // Ajouter le LinearLayout au CardView
        cardView.addView(linearLayout);

        return cardView;
    }

}