package com.example.familygames.activities.gamesactivities.petitbacactivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.MainActivity;
import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.List;
import java.util.Random;

public class PetitBacActivity extends AppCompatActivity {

    TextView textViewLetter, textViewTimer;
    Button buttonGenerateLetter, buttonGoToGameMenu, buttonViewScores, buttonGoBack;
    Chronometer chronometer;
    LinearLayout linearLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petit_bac);
        linearLayout = findViewById(R.id.linear_layout);
        textViewLetter = findViewById(R.id.textViewLetter);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonGenerateLetter = findViewById(R.id.buttonGenerateLetter);
        buttonGoToGameMenu = findViewById(R.id.buttonGoToGameMenu);
        buttonViewScores = findViewById(R.id.buttonViewScores);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setVisibility(Chronometer.INVISIBLE);

        buttonViewScores.setOnClickListener(v -> {
            //TODO
        });

        buttonGoToGameMenu.setOnClickListener(v -> {
            Intent intent = new Intent(PetitBacActivity.this, MainActivity.class);
            startActivity(intent);
        });

        buttonGenerateLetter.setOnClickListener(v -> {
            generateLetter();
            startTimer();
            buttonViewScores.setClickable(false);
            buttonGenerateLetter.setClickable(false);
            Button buttonValidate = new Button(this);
            buttonValidate.setText("J'ai fini!");
            linearLayout.addView(buttonValidate);
            buttonValidate.setOnClickListener(v1 -> {
                chronometer.stop();
                playSound();
                buttonViewScores.setClickable(true);
                buttonGenerateLetter.setClickable(true);
                linearLayout.removeView(buttonValidate);
            });

        });

        buttonGoBack.setOnClickListener(v -> finish());
    }

    @SuppressLint("SetTextI18n")
    private void generateLetter() {
        Random random = new Random();
        char letter = (char) (random.nextInt(26) + 'A');
        textViewLetter.setText("Lettre: " + letter);
    }

    private void startTimer() {
        chronometer.setVisibility(Chronometer.VISIBLE);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }
}