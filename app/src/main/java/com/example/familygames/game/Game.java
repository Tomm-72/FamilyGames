package com.example.familygames.game;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private final String name;
    private final String description;
    private final String rules;
    private final String image;
    private final int maxNbPlayers;
    private int minNbPlayers;

    public Game(String name, String description, String rules, String image, int maxNbPlayers, int minNbPlayers) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.image = image;
        this.maxNbPlayers = maxNbPlayers;
        this.minNbPlayers = minNbPlayers;
    }
    public Game(String name, String description, String rules, String image, int maxNbPlayers) {
        this(name, description, rules, image, maxNbPlayers, 2);
    }
    public Game(String name, String description, String rules, String image) {
        this(name, description, rules, image, -1,2);
    }

    protected Game(Parcel in) {
        name = in.readString();
        description = in.readString();
        rules = in.readString();
        image = in.readString();
        maxNbPlayers = in.readInt();
        minNbPlayers = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(rules);
        dest.writeString(image);
        dest.writeInt(maxNbPlayers);
        dest.writeInt(minNbPlayers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getMaxNbPlayers() {
        return maxNbPlayers;
    }
    public int getMinNbPlayers() {
        return minNbPlayers;
    }

}
