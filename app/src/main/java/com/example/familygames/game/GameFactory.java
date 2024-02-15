package com.example.familygames.game;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
    private List<Game> gameList;
    public List<Game> generateGameList() {
        gameList = new ArrayList<>();
        Game skyjo = new Skyjo();
        Game tumblingDice = new TumblingDice();
        Game petitBac = new PetitBac();
        Game level8 = new Level8();

        gameList.add(skyjo);
        gameList.add(tumblingDice);
        gameList.add(petitBac);
        gameList.add(level8);

        return gameList;
    }
}
