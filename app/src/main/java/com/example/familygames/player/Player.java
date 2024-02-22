package com.example.familygames.player;

public class Player {
    private final String name;
    private int score;
    private String imageUri; // Pour stocker l'URI de l'image de profil

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.imageUri = ""; // Initialise sans image
    }

    // Getters et Setters
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}