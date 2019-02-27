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
     * Retourne parmi une {@link Games liste de jeux} celui le choisi par le joueur
     *
     * @return jeu choisi
     */
    public abstract Games choixDuJeu();

    /**
     * Retourne parmi une {@link GameMode liste de mode de jeu} celui choisi par le joueur
     *
     * @return mode choisi
     */
    public abstract GameMode choixDuMode();

    /**
     * Retourne une combinaison en fonction du jeu et de la taille attendue
     *
     * @param jeu jeu concerné
     * @param tailleCombinaison longueur de la combinaison sous forme d'un entier
     * @param colors leste des valeurs possibles
     * @return la combinaison choisie
     */
    public abstract String genereUneCombinaison(Games jeu, int tailleCombinaison, List colors);

    /**
     * Retourne un combinaison en fonction du jeu, de la taille attendue et des précédentes propositions
     *
     * @param jeu jeu concerné
     * @param tailleCombinaison longueur de la combinaison sous forme d'un entier
     * @param colors liste des chiffres/couleurs posssibles
     * @param historique Liste de l'ensemble des propositions et de leur resultats
     * @return une combinaison
     */
    public abstract String proposeUneCombinaison(Games jeu, int tailleCombinaison, List colors,  List<String[]> historique);

    /**
     * Retourne le nom du joueur
     *
     * @return nom
     */
    public abstract String getName();

    /**
     * Methode qui permet de demander à un joueur s'il veut rejouer
     *
     * @return vrai s'il veut rejouer
     */
    public abstract boolean demandeRejouerPartie();

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
