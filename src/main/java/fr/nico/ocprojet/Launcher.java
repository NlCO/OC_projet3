package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cette class a pour objectif de lancer un jeu avec les paramètres choisis ou configurés
 *
 * <ul>
 *     <li>choix du jeu : recherche +/- ou mastermind</li>
 *     <li>choix du mode de jeu Challenger, Défenseur et Duel @see GameMode</li>
 * </ul>
 */
public class Launcher {
    private List<String> listeDeJeu = new ArrayList<>();

    public void ajouterJeu (String nomDuJeu){
        listeDeJeu.add(nomDuJeu);
    }

    public List<String> jeuxDispos() {
        return listeDeJeu;
    }

    public List<GameMode> gameModes() {
        return Arrays.asList(GameMode.values());
    }

}

