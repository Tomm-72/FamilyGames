package com.example.familygames.game;

public class Game {
    private String name;
    private String description;
    private String rules;
    private String image;
    public Game(String name, String description, String rules, String image) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.image = image;
    }
    private String getName() {
        return name;
    }
    private String getDescription() {
        return description;
    }
    private String getRules() {
        return rules;
    }
    private String getImage() {
        return image;
    }
    public void play() {
        System.out.println("Playing " + name);
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
