package com.example.familygames.activities.gamesactivities.level8activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class Level8Activity extends AppCompatActivity {
    List<Player> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level8);
        players = getIntent().getParcelableArrayListExtra("players");

        assert players != null;
        for (Player player : players) {
            player.setScore(1);
        }

        Button level1Button = findViewById(R.id.buttonLevel1);
        Button level2Button = findViewById(R.id.buttonLevel2);
        level1Button.setOnClickListener(v -> launchLevel1());
        level2Button.setOnClickListener(v -> launchLevel2());

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        Glide.with(this)
                .load(R.drawable.level1)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(imageView);
        Glide.with(this)
                .load(R.drawable.level2)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(imageView2);
    }

    public void launchLevel1() {
        Intent intent = new Intent(this, Level8GameActivity.class);
        intent.putExtra("level", 1);
        intent.putExtra("players", (ArrayList<Player>) players);
        finish();
        startActivity(intent);
    }

    public void launchLevel2() {
        Intent intent = new Intent(this, Level8GameActivity.class);
        intent.putExtra("level", 2);
        intent.putExtra("players", (ArrayList<Player>) players);
        finish();
        startActivity(intent);
    }
}