package com.example.familygames.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.familygames.R;
import com.example.familygames.game.Game;
import com.example.familygames.player.Player;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SetPlayerValuesActivity extends AppCompatActivity {

    LinearLayout playersContainer;
    private ImageView currentSelectedImageView;
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
            retrievePlayerNames(this.players, intent);

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
                Intent intent = new Intent(SetPlayerValuesActivity.this, SkyjoActivity.class);
                return intent;
            case "Petit Bac":
                Intent intent1 = new Intent(SetPlayerValuesActivity.this, PetitBacActivity.class);
                return intent1;
            case "Level8":
                Intent intent2 = new Intent(SetPlayerValuesActivity.this, Level8Activity.class);
                return intent2;
            case "Tumbling Dice":
                Intent intent3 = new Intent(SetPlayerValuesActivity.this, TumblingDiceActivity.class);
                return intent3;
            default:
                setTitle("Jeu");
                return null;
        }
    }

    private void retrievePlayerNames(List<Player> players, Intent intent) {
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