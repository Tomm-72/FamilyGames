package com.example.familygames.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.familygames.R;
import com.example.familygames.game.Game;

import java.util.ArrayList;

public class GameSelectionActivity extends AppCompatActivity {
    ScrollView gameScrollView;
    TextView gameSelectionTitle;
    ArrayList<Game> gameList;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        gameScrollView = findViewById(R.id.gameScrollView);
        LinearLayout gameContainer = findViewById(R.id.gameContainer);
        gameSelectionTitle = findViewById(R.id.gameSelectionTitle);

        Intent intent = getIntent();
        if (intent != null) {
            gameList = intent.getParcelableArrayListExtra("gameList");
            assert gameList != null;
            for (Game game : gameList) {

                // Créer un nouveau CardView
                CardView cardView = new CardView(this);
                LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                cardLayoutParams.setMargins(16, 16, 16, 16);
                cardView.setLayoutParams(cardLayoutParams);
                cardView.setCardElevation(8);
                cardView.setRadius(16);

                // Créer un LinearLayout pour contenir le titre et la description
                LinearLayout cardLinearLayout = new LinearLayout(this);
                cardLinearLayout.setOrientation(LinearLayout.VERTICAL);
                cardLinearLayout.setPadding(32, 32, 32, 32); // Padding interne

                // Créer et ajouter le titre du jeu au LinearLayout
                TextView gameTitle = new TextView(this);
                String gameName = game.getName();
                gameTitle.setText(gameName);
                gameTitle.setTextSize(18); // Taille du texte
                gameTitle.setTypeface(null, Typeface.BOLD); // Style du texte
                cardLinearLayout.addView(gameTitle);

                // Créer et ajouter la description du jeu au LinearLayout
                TextView gameDescription = new TextView(this);
                gameDescription.setText(game.getDescription());
                gameDescription.setPadding(0, 16, 0, 0);
                cardLinearLayout.addView(gameDescription);

                //Créer et ajouter le bouton de lancement du jeu
                Button gameButton = new Button(this);
                gameButton.setText("Jouer");
                gameButton.setPadding(0, 16, 0, 0);
                switch(gameName){
                    case "Tumbling Dice":
                        //gameButton.setBackgroundResource(R.drawable.tumbling_dice);
                        gameButton.setOnClickListener(v -> {
                            Intent gameIntent = new Intent(this, TumblingDiceActivity.class);
                            gameIntent.putExtra("game", game);
                            startActivity(gameIntent);
                        });
                        break;
                    case "Skyjo":
                        //gameButton.setBackgroundResource(R.drawable.skyjo);
                        gameButton.setOnClickListener(v -> {
                            Intent gameIntent = new Intent(this, SkyjoActivity.class);
                            gameIntent.putExtra("game", game);
                            startActivity(gameIntent);
                        });
                        break;
                    case "Level8":
                        //gameButton.setBackgroundResource(R.drawable.uno);
                        gameButton.setOnClickListener(v -> {
                            Intent gameIntent = new Intent(this, Level8Activity.class);
                            gameIntent.putExtra("game", game);
                            startActivity(gameIntent);
                        });
                        break;
                    case "Petit Bac":
                        //gameButton.setBackgroundResource(R.drawable.dobble);
                        gameButton.setOnClickListener(v -> {
                            Intent gameIntent = new Intent(this, PetitBacActivity.class);
                            gameIntent.putExtra("game", game);
                            startActivity(gameIntent);
                        });
                        break;
                    default:
                        //gameButton.setBackgroundResource(R.drawable.skyjo);
                }

                cardLinearLayout.addView(gameButton);


                // Ajouter le LinearLayout au CardView
                cardView.addView(cardLinearLayout);

                // Ajouter le CardView au conteneur principal
                gameContainer.addView(cardView);
            }
        }
    }
}