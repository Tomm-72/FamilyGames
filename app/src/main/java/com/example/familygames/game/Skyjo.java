package com.example.familygames.game;

public class Skyjo extends Game{
    public Skyjo() {
        super("Skyjo",
                "Anticipez et soyez audacieux...!\n" +
                "\n" +
                "Skyjo est un jeu de cartes simple, subtil et terriblement addictif pour 2 à 8 joueurs.",
                "Au début de la partie les joueurs disposent 12 cartes faces cachées devant eux et en retournent 2 faces visibles. A son tour de jeu le joueur doit piocher une carte dans la pioche ou dans la défausse.\n" +
                        "\n" +
                        "S’il se décide pour la carte visible de la défausse il doit immédiatement échanger cette carte avec l’une de ses douze cartes et la poser face visible.\n" +
                        "\n" +
                        "S’il se décide pour la carte face cachée de la pioche, il peut regarder la carte et choisir s’il veut l’échanger contre l’une de ses cartes (cachées ou visibles).\n" +
                        "\n" +
                        "S’il ne veut pas garder la carte tirée, il la défausse, mais doit révéler une de ses cartes.\n" +
                        "\n" +
                        "Il est possible de défausser 3 cartes d’un coup si toutes les cartes d’une même colonne sont identiques.\n" +
                        "\n" +
                        "Lorsqu’un joueur a révélé toutes ses cartes, le tour se termine puis la manche s’arrête. Les joueurs comptent les points des cartes qui restent devant eux.\n" +
                        "\n" +
                        "Dès qu’un joueur atteint 100 points ou plus, le joueur ayant cumulé le moins de points remporte la partie !",
                "skyjo.jpg",8);
    }


}
