package com.example.familygames.player;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Player implements Parcelable {
    private String name;
    private int score;
    private String imageUri; // Pour stocker l'URI de l'image de profil

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.imageUri = ""; // Initialise sans image
    }

    protected Player(Parcel in) {
        name = in.readString();
        score = in.readInt();
        imageUri = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

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

    public void setName(String string) {
        this.name = string;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeString(imageUri);
    }
}