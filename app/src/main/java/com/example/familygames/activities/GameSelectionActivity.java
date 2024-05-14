package com.example.familygames.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.activities.gamesactivities.playersetupactivities.SelectNumberPlayerActivity;
import com.example.familygames.game.Game;

import java.util.ArrayList;

public class GameSelectionActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        LinearLayout gameContainer = findViewById(R.id.gameContainer);
        initMenuButton();

        initGameList(gameContainer);
    }

    private void initGameList(LinearLayout gameContainer) {
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<Game> gameList = intent.getParcelableArrayListExtra("gameList");
            assert gameList != null;
            for (Game game : gameList) {
                createCardViewForGame(game, gameContainer);
            }
        }
    }

    private void createCardViewForGame(Game game, LinearLayout gameContainer) {

        CardView cardView = new CardView(this);
        setCardViewParams(cardView);

        LinearLayout cardLinearLayout = new LinearLayout(this);
        createLayoutForGame(cardLinearLayout, game);

        cardView.addView(cardLinearLayout);

        gameContainer.addView(cardView);
    }

    private void createLayoutForGame(LinearLayout cardLinearLayout, Game game) {

        setLinearLayoutParams(cardLinearLayout);

        addGameTitleAndDescription(game, cardLinearLayout);

        addGameButton(game, cardLinearLayout);

    }

    @SuppressLint("SetTextI18n")
    private void addGameButton(Game game, LinearLayout cardLinearLayout) {
        Button gameButton = new Button(this);
        gameButton.setText("Jouer");
        gameButton.setPadding(0, 16, 0, 0);
        gameButton.setOnClickListener(v -> {
            Intent gameIntent = new Intent(this, SelectNumberPlayerActivity.class);
            gameIntent.putExtra("game", game);
            finish();
            startActivity(gameIntent);
        });
        cardLinearLayout.addView(gameButton);
    }

    private void addGameTitleAndDescription(Game game, LinearLayout cardLinearLayout) {
        addGameTitle(game, cardLinearLayout);
        addGameDescription(game, cardLinearLayout);
    }

    private void addGameDescription(Game game, LinearLayout cardLinearLayout) {
        TextView gameDescription = new TextView(this);
        gameDescription.setText(game.getDescription());
        gameDescription.setPadding(0, 16, 0, 0);
        cardLinearLayout.addView(gameDescription);
    }

    private void addGameTitle(Game game, LinearLayout cardLinearLayout) {
        TextView gameTitle = new TextView(this);
        String gameName = game.getName();
        gameTitle.setText(gameName);
        gameTitle.setTextSize(18);
        gameTitle.setTypeface(null, Typeface.BOLD);
        cardLinearLayout.addView(gameTitle);
    }

    private void setLinearLayoutParams(LinearLayout cardLinearLayout) {
        cardLinearLayout.setOrientation(LinearLayout.VERTICAL);
        cardLinearLayout.setPadding(32, 32, 32, 32);
    }

    private void setCardViewParams(CardView cardView) {
        LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardLayoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(cardLayoutParams);
        cardView.setCardElevation(8);
        cardView.setRadius(16);
    }

    private void initMenuButton() {
        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        });
    }
}