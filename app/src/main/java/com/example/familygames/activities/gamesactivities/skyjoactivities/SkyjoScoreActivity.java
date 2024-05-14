package com.example.familygames.activities.gamesactivities.skyjoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SkyjoScoreActivity extends AppCompatActivity {
    private List<Player> players;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyjo_score);
        LinearLayout playerContainer = findViewById(R.id.playerContainer);
        Button nextButton = findViewById(R.id.nextButton);
        players = getIntent().getParcelableArrayListExtra("players");
        assert players != null;
        for (Player player : players) {
            TextView playerLine = new TextView(this);
            playerLine.setText(player.getName() + ": " + player.getScore() + " points.");
            playerContainer.addView(playerLine);
        }
        nextButton.setOnClickListener(v -> gotToNextRound());
    }

    private void gotToNextRound() {
        Intent intent = new Intent(this, SkyjoGameActivity.class);
        intent.putParcelableArrayListExtra("players", (ArrayList<Player>) players);
        startActivity(intent);
    }
}