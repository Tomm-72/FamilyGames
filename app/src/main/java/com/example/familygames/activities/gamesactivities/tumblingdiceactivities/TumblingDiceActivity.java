package com.example.familygames.activities.gamesactivities.tumblingdiceactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.R;
import com.example.familygames.player.Player;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TumblingDiceActivity extends AppCompatActivity {
    LinearLayout playersContainer;
    Button launchGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbling_dice);
        ArrayList<Player> players = getIntent().getParcelableArrayListExtra("players");
        assert players != null;
        Collections.shuffle(players);
        playersContainer = findViewById(R.id.playersContainer);
        for (int i = 0; i < players.size(); i++) {
            MaterialCardView cardView = createPlayerCard(players.get(i));
            //add a padding to the card view
            cardView.setPadding(16, 16, 16, 16);

            playersContainer.addView(cardView);
        }
        launchGame = findViewById(R.id.launchGame);
        launchGame.setOnClickListener(v -> {
            Intent intent = new Intent(this, TumblingDiceSecondActivity.class);
            intent.putExtra("players", players);
            startActivity(intent);
        });
    }

    private MaterialCardView createPlayerCard(Player player) {
        MaterialCardView cardView = new MaterialCardView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(layoutParams);

        // Ajout d'un LinearLayout pour contenir les éléments
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Ajout du nom du joueur
        TextView textView = new TextView(this);
        textView.setText(player.getName());
        textView.setTextSize(24);
        textView.setPadding(20, 16, 20, 16);
        linearLayout.addView(textView);

        // Ajouter le LinearLayout au CardView
        cardView.addView(linearLayout);

        return cardView;
    }
}
