package com.example.familygames.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.R;
import com.example.familygames.player.Player;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

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
        List<Player> players = getIntent().getParcelableArrayListExtra("players");
        Collections.shuffle(players);
        playersContainer = findViewById(R.id.playersContainer);
        for (int i = 0; i < players.size(); i++) {
            MaterialCardView cardView = createPlayerCard(players.get(i));
            playersContainer.addView(cardView);
        };
        launchGame = findViewById(R.id.launchGame);
        launchGame.setOnClickListener(v -> {
            Intent intent = new Intent(this, TumblingDiceSecondActivity.class);
            intent.putExtra("players", (ArrayList<Player>) players);
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
        linearLayout.addView(textView);

        // Ajouter le LinearLayout au CardView
        cardView.addView(linearLayout);

        return cardView;
    }
}
