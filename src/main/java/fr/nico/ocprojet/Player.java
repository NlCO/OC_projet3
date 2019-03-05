package fr.nico.ocprojet;

import java.util.List;

/**
 * La classe Player est une représentation abstraite d'un joueur soit {@link Human humain} soit {@link Bot machine}
 * <p>
 * Un nom et 3 statuts (vrai/faux) définissent un Player :
 * <ul>
 * <li>"codeur" : joueur générant la combinaison à découvrir</li>
 * <li>"décodeur" : joueur cherchant à découvrir la combinaison</li>
 * <li>"winner" : joueur ayant trouvé la combinaison</li>
 * </ul>
 */
public abstract class Player {
    private String name;
    private boolean codeur = false;
    private boolean decodeur = false;
    private boolean winner = false;

    /**
     * Méthode permettant d'affecter les statuts codeur/décodeur au joueur en fonction du mode de jeu fourni
     *
     * @param mode {@link GameMode mode de jeu}
     */
    public void setRoles(GameMode mode) {
        decodeur = mode.decodeur;
        codeur = mode.codeur;
    }

    /**
     * Retourne une combinaison en fonction du jeu et de la taille attendue
     *
     * @param jeu jeu concerné
     * @param tailleCombinaison longueur de la combinaison sous forme d'un entier
     * @param setDeValeurs liste des valeurs possibles
     * @return la combinaison choisie
     */
    public abstract String genereUneCombinaison(Games jeu, int tailleCombinaison, List<String> setDeValeurs);

    /**
     * Retourne un combinaison en fonction du jeu, de la taille attendue et des précédentes propositions
     *
     * @param jeu jeu concerné
     * @param tailleCombinaison longueur de la combinaison sous forme d'un entier
     * @param setDeValeurs liste des chiffres/couleurs posssibles
     * @param historique Liste de l'ensemble des propositions et de leur resultats
     * @return une combinaison
     */
    public abstract String proposeUneCombinaison(Games jeu, int tailleCombinaison, List<String> setDeValeurs,  List<String[]> historique);


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDecodeur() {
        return decodeur;
    }

    public boolean isCodeur() {
        return codeur;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }


}
