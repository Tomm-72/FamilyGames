package com.example.familygames.activities.gamesactivities.tumblingdiceactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TumblingDiceScoreActivity extends AppCompatActivity {
    private LinearLayout playerContainer;
    private List<Player> players;
    private boolean isGameInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbling_dice_score);
        players = getIntent().getParcelableArrayListExtra("players");
        isGameInProgress = getIntent().getBooleanExtra("isGameInProgress", true);

        playerContainer = findViewById(R.id.playerContainer);
        initializePlayerContainer();
        Button menuButton = findViewById(R.id.menuButton);
        Button nextRoundButton = findViewById(R.id.nextRoundButton);

        menuButton.setOnClickListener(v -> goToMenu());
        nextRoundButton.setOnClickListener(v -> nextRound());

    }

    private void nextRound() {
        Intent intent = new Intent(this, TumblingDiceSecondActivity.class);
        intent.putExtra("players", (ArrayList<Player>)players);
        intent.putExtra("isGameInProgress", isGameInProgress);
        finish();
        startActivity(intent);
    }

    private void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    private void initializePlayerContainer() {
        for (Player player : players) {
            LinearLayout playerLayout = new LinearLayout(this);
            playerLayout.setOrientation(LinearLayout.VERTICAL);
            TextView playerLine = new TextView(this);
            playerLine.setText(player.getName() + " est a " + player.getScore() + " points");
            playerLayout.addView(playerLine);
            playerContainer.addView(playerLayout);
        }
    }
}