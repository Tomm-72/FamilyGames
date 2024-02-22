package com.example.familygames.game;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private final String name;
    private final String description;
    private final String rules;
    private final String image;

    public Game(String name, String description, String rules, String image) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.image = image;
    }

    protected Game(Parcel in) {
        name = in.readString();
        description = in.readString();
        rules = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(rules);
        dest.writeString(image);
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
    public String getRules() {
        return rules;
    }
    public String getImage() {
        return image;
    }
    public void showRules() {
        System.out.println(rules);
    }
    public void showDescription() {
        System.out.println(description);
    }
    public void showImage() {
        System.out.println(image);
    }

}
