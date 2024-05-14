package com.example.familygames.activities.gamesactivities.endgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.level8activities.Level8Activity;
import com.example.familygames.player.Player;

import java.util.List;

public class Level8EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);

        TextView playerNames = findViewById(R.id.playerNames);
        TextView congratSentence = findViewById(R.id.congratSentence);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        Button menuButton = findViewById(R.id.menuButton);

        List<Player> winners = getIntent().getParcelableArrayListExtra("winners");

        assert winners != null;
        playerNames.setText(buildPlayerNamesText(winners));
        congratSentence.setText(buildCongratSentence(winners));

        playAgainButton.setOnClickListener(v -> playAgain());
        menuButton.setOnClickListener(v -> goToMenu());

    }

    private void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void playAgain() {
        Intent intent = new Intent(this, Level8Activity.class);
        intent.putExtra("players", getIntent().getParcelableArrayListExtra("players"));
        finish();
        startActivity(intent);
    }

    private String buildCongratSentence(List<Player> winners) {
        if (winners.size() > 1) {
            return "Vous avez gagné!";
        }
        else {
            return "Tu as gagné!";
        }
    }

    private String buildPlayerNamesText(List<Player> winners) {
        if (winners.size() > 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < winners.size(); i++) {
                sb.append(winners.get(i).getName());
                if (i < winners.size() - 1) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }
        else {
            return winners.get(0).getName();
        }
    }
}