package com.example.familygames.activities;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familygames.R;

import java.util.Random;

public class PetitBacActivity extends AppCompatActivity {

    TextView textViewLetter, textViewTimer;
    Button buttonGenerateLetter, buttonGoToGameMenu, buttonViewScores;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petit_bac);

        textViewLetter = findViewById(R.id.textViewLetter);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonGenerateLetter = findViewById(R.id.buttonGenerateLetter);
        buttonGoToGameMenu = findViewById(R.id.buttonGoToGameMenu);
        buttonViewScores = findViewById(R.id.buttonViewScores);

        buttonViewScores.setOnClickListener(v -> {
            //TODO
        });

        buttonGoToGameMenu.setOnClickListener(v -> {
            timer.cancel();
            finish();
        });

        buttonGenerateLetter.setOnClickListener(v -> {
            generateLetter();
            startTimer();
            buttonViewScores.setClickable(false);
            buttonGenerateLetter.setClickable(false);
        });
    }

    @SuppressLint("SetTextI18n")
    private void generateLetter() {
        Random random = new Random();
        char letter = (char) (random.nextInt(26) + 'A');
        textViewLetter.setText("Lettre: " + letter);
    }

    private void startTimer() {
        timer = new CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("Temps restant: " + millisUntilFinished / 1000);
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                textViewTimer.setText("C'est fini !");
                playSound();
                buttonGenerateLetter.setClickable(true);
                buttonViewScores.setClickable(true);
            }
        }.start();
    }
    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }
}