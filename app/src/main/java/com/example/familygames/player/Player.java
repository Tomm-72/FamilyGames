package com.example.familygames.player;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private final int id;
    private String name;
    private int score;
    private static int  idIncrement = 0;

    public Player(String name) {
        this.id = idIncrement++;
        this.name = name;
        this.score = 0;
    }
    protected Player(Parcel in) {
        name = in.readString();
        score = in.readInt();
        id = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void addToScore(int score) {
        this.score += score;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
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
}