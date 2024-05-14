package com.example.familygames.activities.gamesactivities.skyjoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SkyjoActivity extends AppCompatActivity {
    private List<Player> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyjo);
        Button launchGameButton = findViewById(R.id.launchGameButton);
        Button buttonMenu = findViewById(R.id.buttonMenu);
        players = getIntent().getParcelableArrayListExtra("players");

        launchGameButton.setOnClickListener(v -> launchGame());
        buttonMenu.setOnClickListener(v -> goToMenu());

        ImageView imageView3 = findViewById(R.id.imageView3);
        Glide.with(this)
                .load(R.drawable.skyjo_jeu_3)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(imageView3);
    }

    private void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void launchGame() {
        Intent intent = new Intent(this, SkyjoGameActivity.class);
        intent.putParcelableArrayListExtra("players", (ArrayList<Player>) players);
        finish();
        startActivity(intent);
    }
}