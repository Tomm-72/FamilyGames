package com.example.familygames.game;

public class TumblingDice extends Game {
    public TumblingDice(){
        super("Tumbling Dice",
                "Tumblin-Dice est un jeu d'adresse très tactique consistant à marquer le plus de points possible à l'aide de ses dés, tout en tentant à chaque lancer de réaliser le coup parfait.",
                "A tour de rôle, chaque joueur lance un de ses dés à l’aide d’une pichenette, afin de l’envoyer le plus loin possible sur le plateau de jeu tout en essayant d’éjecter les dés adverses qui se trouvent sur son chemin.\n" +
                        "\n" +
                        "Le but du jeu est de marquer plus de points que ses adversaires une fois lancés les dés de tous les joueurs.\n" +
                        "\n" +
                        "Le coup parfait est réalisé en plaçant un dé de valeur 6 sur le plateau « x4 » : il rapportera 24 points (4×6) à lui seul !\n" +
                        "\n" +
                        "Mais attention, rien n’est acquis car vos adversaires feront tout pour le faire sortir.\n" +
                        "\n" +
                        "Uns fois tous les dés lancés, la manche s’arrête et chacun compte ses points. Le premier joueur (ou la première équipe) à atteindre 301 points remporte la partie.",
                "tumbling_dice.png");
    }
}
