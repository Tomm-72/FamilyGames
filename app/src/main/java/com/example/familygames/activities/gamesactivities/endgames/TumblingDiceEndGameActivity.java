package com.example.familygames.activities.gamesactivities.endgames;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.tumblingdiceactivities.TumblingDiceActivity;
import com.example.familygames.player.Player;

import java.util.List;

public class TumblingDiceEndGameActivity extends AppCompatActivity {
    private List<Player> players;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbling_dice_end_game);
        players = getIntent().getParcelableArrayListExtra("players");
        Player winner = getIntent().getParcelableExtra("winner");

        TextView winnerTextView = findViewById(R.id.winnerTextView);
        assert winner != null;
        winnerTextView.setText(winner.getName());

        TextView winnerScore = findViewById(R.id.winnerScore);
        winnerScore.setText(winner.getScore() + " points");

        LinearLayout playersContainer = findViewById(R.id.playersContainer);
        for (Player player : players) {
            LinearLayout playerLayout = new LinearLayout(this);
            playerLayout.setOrientation(LinearLayout.VERTICAL);
            TextView playerLine = new TextView(this);
            playerLine.setText(player.getName() + " est a " + player.getScore() + " points");
            playerLayout.addView(playerLine);
            playersContainer.addView(playerLayout);
        }

        Button replayButton = findViewById(R.id.replayButton);
        Button menuButton = findViewById(R.id.menuButton);

        replayButton.setOnClickListener(v -> replay());
        menuButton.setOnClickListener(v -> goToMenu());
    }

    private void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void replay() {
        for (Player player : players) {
            player.setScore(0);
        }
        Intent intent = new Intent(this, TumblingDiceActivity.class);
        intent.putExtra("players", getIntent().getParcelableArrayListExtra("players"));
        finish();
        startActivity(intent);
    }
}