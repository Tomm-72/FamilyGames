package com.example.familygames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.familygames.R;
import com.example.familygames.game.Game;

public class SelectNumberPlayerActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_player);

        Game selectedGame = getIntent().getParcelableExtra("game");
        int maxNbPlayers = selectedGame != null ? selectedGame.getMaxNbPlayers() : 0;

        LinearLayout layout = findViewById(R.id.playerSelectionLayout);

        if (maxNbPlayers == -1) {
            NumberPicker numberPicker = new NumberPicker(this);
            numberPicker.setMinValue(2);
            numberPicker.setMaxValue(500);
            Button button = new Button(this);
            button.setText("Valider");
            button.setOnClickListener(v -> {
                Intent intent = new Intent(SelectNumberPlayerActivity.this, SetPlayerValuesActivity.class);
                intent.putExtra("game", selectedGame);
                intent.putExtra("nbPlayers", numberPicker.getValue());
                startActivity(intent);
            });
            layout.addView(numberPicker);
            layout.addView(button);
        }

        else{
            assert selectedGame != null;
            for (int i = selectedGame.getMinNbPlayers(); i <= maxNbPlayers; i++) {
                Button button = new Button(this);
                int finalI = i;
                button.setText(i + " Joueurs");
                button.setOnClickListener(v -> {
                    Intent intent = new Intent(SelectNumberPlayerActivity.this, SetPlayerValuesActivity.class);
                    intent.putExtra("game", selectedGame);
                    intent.putExtra("nbPlayers", finalI);
                    startActivity(intent);
                });
                layout.addView(button);
            }
        }
    }
}