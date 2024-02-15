package com.example.familygames.game;

public class PetitBac extends Game {
    public PetitBac() {
        super(
                "Petit Bac",
                "A word game where players have to come up with words that start with a specific letter and fit into different categories.",
                "1. The game is played in rounds. Each round, a letter is chosen and a list of categories is given.\n" +
                        "2. Players have to come up with a word that starts with the chosen letter and fits into each category.\n" +
                        "3. Players get points for each unique word they come up with.\n" +
                        "4. The game ends after a set number of rounds and the player with the most points wins.",
                "petitbac.jpg"
        );
    }
}
