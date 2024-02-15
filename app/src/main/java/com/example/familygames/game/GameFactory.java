package com.example.familygames.game;

import java.util.List;

public class GameFactory {
    private List<Game> gameList;
    public List<Game> generateGameList() {
        Game skyjo = new Skyjo("Skyjo", "A fun card game", "The rules are simple", "skyjo.jpg");
        return gameList;
    }
}
