package com.example.familygames.game;

public class PetitBac extends Game {
    public PetitBac() {
        super(
                "Petit Bac",
                "Le but du jeu est de trouver un mot pour chaque catégorie avant la fin du temps imparti. Les joueurs doivent trouver un mot pour chaque catégorie commençant par la lettre tirée au sort. Le premier joueur à trouver un mot pour chaque catégorie gagne le tour.",
                "Le jeu se joue en plusieurs manches. À chaque manche, un joueur tire une lettre au hasard. Les joueurs doivent trouver un mot pour chaque catégorie commençant par la lettre tirée au sort. Le premier joueur à trouver un mot pour chaque catégorie gagne le tour. Les joueurs marquent des points en fonction du nombre de mots trouvés. Le joueur avec le plus de points à la fin du jeu gagne.",
                "petitbac.jpg"
        );
    }
}
