package fr.nico.ocprojet;

import java.util.List;

/**
 * GamePlay est la classe contenant le gameplay commun au jeu
 *
 * Un jeu a besoin d'une liste de joueur ayant été paramétrée @see Launcher @see App
 */
public class GamePlay {
    protected List<Player> players;
    protected Games game;
    protected int tailleCombinaison = 4;
    protected int nombreEssai = 10;
    protected String combinaison;

    protected void initialiserLaPartie(){
        for (Player joueur: players) {
            if (joueur.isCodeur()) {
                joueur.genereUneCombinaison();
            }
        }
    }

}
